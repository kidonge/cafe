package com.example.cafe.service;

import com.example.cafe.domain.*;
import com.example.cafe.dto.reponsedto.OrderResponseDto;
import com.example.cafe.dto.reponsedto.PopularMenuDto;
import com.example.cafe.dto.requestdto.OrderRequestDto;
import com.example.cafe.exception.CustomError;
import com.example.cafe.exception.CustomException;
import com.example.cafe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final PointService pointService;


    /*
    총 돈 구해서 유저 돈 보다 적으면 예외처리
    order 저장
    유저 돈 업데이트
    ordermenu 저장
     */
    //돈 부족 예외처리 필요
    @Transactional
    public OrderResponseDto orderMenu(List<OrderRequestDto> list){

        long totalPrice = 0;
        for(OrderRequestDto requestDto : list){
            Menu menu = menuRepository.findById(requestDto.getMenuId()).get();
            totalPrice += requestDto.getAmount() * menu.getPrice();
        }
        Member member = memberRepository.findById(list.get(0).getMemberId()).get();

        if(member.getPoint() < totalPrice){
            throw new CustomException(CustomError.NOT_ENOUGH_POINT);
        }

        // 사용자 point update
        member.updatePoint(member.getPoint() - totalPrice);

        // 포인트 사용 이력 저장
        pointService.payPointHistory(member.toDto(), totalPrice);

        // orders 저장
       Order order = Order.builder()
               .totalPrice(totalPrice)
               .member(member)
               .build();

        orderRepository.save(order);

        for(OrderRequestDto request : list){
            Menu menu = menuRepository.findById(request.getMenuId()).get();
            OrderItem orderItem = OrderItem.builder()
                    .price(menu.getPrice())
                    .amount(request.getAmount())
                    .menuId(list.get(0).getMenuId())
                    .order(order)
                    .build();

            orderMenuRepository.save(orderItem);
        }

        return OrderResponseDto.builder()
                .memberId(member.getId())
                .point(member.getPoint())
                .build();
    }

    @Cacheable(value = "menu")
    @Transactional(readOnly = true)
    public List<PopularMenuDto> getPopularMenus() {

        //return orderMenuRepository.findPopularMenu();
        return menuRepository.popularMenus();
    }
}
