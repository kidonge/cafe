package com.example.cafe.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 메뉴 이름
    @Column(nullable = false)
    private String name;

    // 메뉴 가격
    @Column(nullable = false)
    private Integer price;
}
