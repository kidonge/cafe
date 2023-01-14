package com.example.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderMenu extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 해당 메뉴의 가격
    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "order_id")
    private Orders orders;
}
