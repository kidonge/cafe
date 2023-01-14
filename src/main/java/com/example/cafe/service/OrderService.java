package com.example.cafe.service;

import com.example.cafe.domain.Member;
import com.example.cafe.domain.Menu;
import com.example.cafe.domain.Orders;
import com.example.cafe.domain.OrderMenu;
import com.example.cafe.dto.reponsedto.OrderResponseDto;
import com.example.cafe.dto.reponsedto.ResponseDto;
import com.example.cafe.dto.requestdto.OrderRequestDto;
import com.example.cafe.exception.CustomError;
import com.example.cafe.exception.CustomException;
import com.example.cafe.repository.MemberRepository;
import com.example.cafe.repository.MenuRepository;
import com.example.cafe.repository.OrderMenuRepository;
import com.example.cafe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
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


    /*
    총 돈 구해서 유저 돈 보다 적으면 예외처리
    order 저장
    유저 돈 업데이트
    ordermenu 저장
     */
    //돈 부족 예외처리 필요
    @Transactional
    public ResponseDto<OrderResponseDto> orderMenu(List<OrderRequestDto> list){

        int totalPrice = 0;
        for(OrderRequestDto requestDto : list){
            Menu menu = menuRepository.findById(requestDto.getMenuId()).get();
            totalPrice += requestDto.getAmount() * menu.getPrice();
        }

        Member member = memberRepository.findById(list.get(0).getMemberId()).get();

        if(member.getPoint() < totalPrice){
            throw new CustomException(CustomError.NOT_ENOUGH_POINT);
        }

        // 사용자 point update
        member.update(member.getPoint() - totalPrice);

        Orders orders = Orders.builder()
                .totalPrice(totalPrice)
                .member(member)
                .build();

        orderRepository.save(orders);

        for(OrderRequestDto request : list){
            Menu menu = menuRepository.findById(request.getMenuId()).get();
            OrderMenu orderMenu = OrderMenu.builder()
                    .price(menu.getPrice())
                    .amount(request.getAmount())
                    .menu(menu)
                    .orders(orders)
                    .build();

            orderMenuRepository.save(orderMenu);
        }

        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .memberId(member.getId())
                .point(member.getPoint())
                .build();

        return ResponseDto.success(orderResponseDto);
    }
}
