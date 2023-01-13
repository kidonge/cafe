package com.example.cafe.service;

import com.example.cafe.domain.Menu;
import com.example.cafe.dto.reponsedto.MenuResponseDto;
import com.example.cafe.dto.reponsedto.ResponseDto;
import com.example.cafe.repository.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Mockito 클래스 사용
class MenuServiceTest {

    // InjectMocks가 있으면 @Spy, @Mock 어노테이션이 붙은 객체들을 주입시켜준다.
    @InjectMocks
    private MenuService menuService;

    // Mock 클래스로 생성해야 하는 가짜 객체 지정
    // 가짜 객체는 별다른 동작 기술 없이는 동작 x -> TC마다 이 객체가 어떻게 동작돼야 하는지 명시 필요
    @Mock
    private MenuRepository menuRepository;

    @Test
    @DisplayName("메뉴 조회 테스트")
    public void findAllMenu(){

        /**
         * given
         */

        Menu menu1 = Menu.builder()
                .name("아메리카노")
                .price(5000)
                .build();

        Menu menu2 = Menu.builder()
                .name("카페라떼")
                .price(6000)
                .build();

        List<Menu> menuList = Arrays.asList(menu1, menu2);
        when(menuRepository.findAll()).thenReturn(menuList);

        /**
         * when
         */

        ResponseDto<List<MenuResponseDto>> responseDto = menuService.allMenu();

        /**
         * then
         */

        assertThat(responseDto.getData().size()).isEqualTo(menuList.size());
    }


}