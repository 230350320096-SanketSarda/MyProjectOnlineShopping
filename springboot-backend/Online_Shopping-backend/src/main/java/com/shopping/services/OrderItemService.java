package com.shopping.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.daos.OrderItemDao;
import com.shopping.entities.OrderItem;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemDao orderItemDao;
	
	public List<OrderItem> findAllOrderItems() {
		return orderItemDao.findAll();
	}
}
