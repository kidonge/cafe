package com.example.cafe.service;

import com.example.cafe.domain.Menu;
import com.example.cafe.dto.reponsedto.MenuResponseDto;
import com.example.cafe.dto.reponsedto.ResponseDto;
import com.example.cafe.dto.requestdto.MenuRequestDto;
import com.example.cafe.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public List<MenuResponseDto> getAllMenu(){
        List<Menu> menuList = menuRepository.findAll();
        List<MenuResponseDto> menuResponseDtoList = new ArrayList<>();
        for(Menu menu : menuList){
            MenuResponseDto menuResponseDto = MenuResponseDto.builder()
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .build();
            menuResponseDtoList.add(menuResponseDto);
        }

        return menuResponseDtoList;
    }

    @Transactional
    public String addMenu(MenuRequestDto requestDto){
        Menu menu = Menu.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .build();

        menuRepository.save(menu);

        return "메뉴 추가 완료";
    }

}
