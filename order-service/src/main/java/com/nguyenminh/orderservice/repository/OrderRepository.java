package com.nguyenminh.orderservice.repository;

import com.nguyenminh.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order , Long> {
}
