package com.project.shoppingMall.repository;

import com.project.shoppingMall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
