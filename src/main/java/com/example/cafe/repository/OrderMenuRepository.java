package com.example.cafe.repository;

import com.example.cafe.domain.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer> {
}
