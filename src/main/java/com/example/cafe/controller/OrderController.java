package com.example.cafe.controller;

import com.example.cafe.domain.PopularMenu;
import com.example.cafe.dto.reponsedto.OrderResponseDto;
import com.example.cafe.dto.reponsedto.PopularMenuDto;
import com.example.cafe.dto.reponsedto.ResponseDto;
import com.example.cafe.dto.requestdto.OrderRequestDto;
import com.example.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseDto<OrderResponseDto> order(@RequestBody List<OrderRequestDto> orderList){
        return ResponseDto.success(orderService.orderMenu(orderList));
    }

    @GetMapping("/popular-menus")
    public ResponseDto<List<PopularMenuDto>> getPopularMenus(){
        return ResponseDto.success(orderService.getPopularMenus());
    }
}
