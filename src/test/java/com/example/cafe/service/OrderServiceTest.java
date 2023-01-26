package com.example.cafe.service;

import com.example.cafe.domain.Member;
import com.example.cafe.domain.Menu;
import com.example.cafe.dto.reponsedto.OrderResponseDto;
import com.example.cafe.dto.requestdto.OrderRequestDto;
import com.example.cafe.repository.MemberRepository;
import com.example.cafe.repository.MenuRepository;
import com.example.cafe.repository.OrderMenuRepository;
import com.example.cafe.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private OrderMenuRepository orderMenuRepository;

    @Mock
    private PointService pointService;

    @DisplayName("주문 테스트")
    @Test
    void orderMenu() {

        // given

        Menu menu1 = Menu.builder()
                .id(1L)
                .name("아메리카노")
                .price(4000L)
                .build();

        Menu menu2 = Menu.builder()
                .id(2L)
                .name("카페라떼")
                .price(4500L)
                .build();

        when(menuRepository.findById(1L)).thenReturn(Optional.ofNullable(menu1));
        when(menuRepository.findById(2L)).thenReturn(Optional.ofNullable(menu2));

        Member member = Member.builder()
                .id(1L)
                .point(50000L)
                .build();

        when(memberRepository.findById(1L)).thenReturn(Optional.ofNullable(member));

        OrderRequestDto orderRequestDto1 = OrderRequestDto.builder()
                .memberId(1L)
                .menuId(1L)
                .amount(3L)
                .build();

        OrderRequestDto orderRequestDto2 = OrderRequestDto.builder()
                .memberId(1L)
                .menuId(2L)
                .amount(3L)
                .build();

        List<OrderRequestDto> list = Arrays.asList(orderRequestDto1, orderRequestDto2);

        // when
        OrderResponseDto response = orderService.orderMenu(list);

        // then

        assertThat(response.getPoint()).isEqualTo(24500L);
        verify(pointService, times(1)).payPointHistory(any(), any());


    }
}