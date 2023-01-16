package com.example.cafe.repository;

import com.example.cafe.domain.Menu;
import com.example.cafe.domain.OrderMenu;
import com.example.cafe.domain.Orders;
import com.example.cafe.domain.PopularMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer> {

    @Query(value = "select menu_id menu, sum(amount) as total " +
            "from order_menu " +
            "where created_at between DATE_ADD(NOW(),INTERVAL -1 WEEK ) AND NOW() " +
            "group by menu_id " +
            "order by total desc " +
            "limit 3", nativeQuery = true)
    List<PopularMenu> findPopularMenu();



}

/*
@Query(value = "select b.menu_id menu, sum(amount) a " +
            "from " +
            "(SELECT amount, menu_id FROM order_menu WHERE created_at BETWEEN DATE_ADD(NOW(),INTERVAL -1 WEEK ) AND NOW()) b " +
            "group by menu_id " +
            "order by a desc " +
            "limit 3", nativeQuery = true)
 */
