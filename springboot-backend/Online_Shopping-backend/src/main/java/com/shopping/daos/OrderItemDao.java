package com.shopping.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entities.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer>{

}
