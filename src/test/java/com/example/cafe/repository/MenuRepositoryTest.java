package com.example.cafe.repository;

import com.example.cafe.domain.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩(컨트롤러와 서비스는 로딩되지 않음) 술러아스 테스트
class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @DisplayName("save() 테스트")
    public void saveTest(){

        /**
         * given(데이터 준비)
         */

        String name = "아메리카노";
        Integer price = 5000;

        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .build();

        /**
         * when(테스트 실행)
         */

        Menu menu1 = menuRepository.save(menu);


        /**
         *  then(검증)
         */

        assertThat(menu1.getName()).isEqualTo(name);
        assertThat(menu1.getPrice()).isEqualTo(price);

    }

    @DisplayName("findAll() 테스트")
    @Test
    public void findTest(){

        /**
         * given(데이터 준비)
         */

        String name = "아메리카노";
        Integer price = 5000;

        Menu menu = Menu.builder()
                .id(1)
                .name(name)
                .price(price)
                .build();

        /**
         * when(테스트 실행)
         */

        menuRepository.save(menu);
        List<Menu> menuList = menuRepository.findAll();


        /**
         *  then(검증)
         */

        assertThat(menuList.size()).isEqualTo(1);

    }

}