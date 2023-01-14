package com.example.cafe.controller;

import com.example.cafe.dto.reponsedto.MenuResponseDto;
import com.example.cafe.dto.reponsedto.ResponseDto;
import com.example.cafe.dto.requestdto.MenuRequestDto;
import com.example.cafe.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menus")
    public ResponseDto<List<MenuResponseDto>> getMenu(){
        return menuService.allMenu();
    }

    @PostMapping("/menus")
    public ResponseDto<String> addMenu(@RequestBody MenuRequestDto requestDto){
        return menuService.addMenu(requestDto);
    }
}
