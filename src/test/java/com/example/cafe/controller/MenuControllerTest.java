package com.example.cafe.controller;

import com.example.cafe.dto.reponsedto.MenuResponseDto;
import com.example.cafe.dto.reponsedto.ResponseDto;
import com.example.cafe.dto.requestdto.MenuRequestDto;
import com.example.cafe.service.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @WebMvcTest
 * - JPA 기능은 동작하지 않는다.
 * - 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에만 집중할 수 있는 어노테이션
 * - @Controller, @ControllerAdvice 사용 가능
 * - 단, @Service, @Repository등은 사용할 수 없다.
 * */


@ExtendWith(MockitoExtension.class)
class MenuControllerTest {

    @InjectMocks
    private MenuController menuController;

    @Mock
    private MenuService menuService;


    MockMvc mvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mvc = MockMvcBuilders.standaloneSetup(menuController).build();
    }

    @DisplayName("모든 메뉴 조회 테스트")
    @Test
    void getAllMenu() throws Exception{

        // given

        MenuResponseDto menuResponseDto1 = MenuResponseDto.builder()
                .name("아메리카노")
                .price(4000L)
                .build();

        MenuResponseDto menuResponseDto2 = MenuResponseDto.builder()
                .name("카페라떼")
                .price(4500L)
                .build();

        List<MenuResponseDto> list = Arrays.asList(menuResponseDto1, menuResponseDto2);

        doReturn(list).when(menuService).getAllMenu();

        // when

        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/api/menus")
        );

        // then

        result.andExpect(status().isOk());

    }

    @DisplayName("메뉴 추가 테스트")
    @Test
    void addMenu() throws Exception {

        // given

        MenuRequestDto menuRequestDto = MenuRequestDto.builder()
                .name("차")
                .price(3500L)
                .build();

        ArgumentCaptor<MenuRequestDto> argumentCaptor = ArgumentCaptor.forClass(MenuRequestDto.class);

        // when

        ResultActions result = mvc.perform(post("/api/menus")
                .content(objectMapper.writeValueAsString(menuRequestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(menuService).addMenu(argumentCaptor.capture());
        result.andExpect(status().isOk());
    }

//    @Test
//    void addMenu() {
//    }
}