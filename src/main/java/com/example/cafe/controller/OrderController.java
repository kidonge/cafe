package com.example.cafe.controller;

import com.example.cafe.dto.reponsedto.OrderResponseDto;
import com.example.cafe.dto.reponsedto.ResponseDto;
import com.example.cafe.dto.requestdto.OrderRequestDto;
import com.example.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseDto<OrderResponseDto> order(@RequestBody List<OrderRequestDto> orderList){
        return orderService.orderMenu(orderList);
    }
}
