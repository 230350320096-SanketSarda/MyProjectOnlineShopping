package com.shopping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entities.Customer;
import com.shopping.entities.DeliveryPerson;
import com.shopping.entities.FoodItem;
import com.shopping.entities.FoodType;
import com.shopping.entities.OrderItem;
import com.shopping.entities.Orders;
import com.shopping.entities.Payment;
import com.shopping.entities.Restaurant;
import com.shopping.entities.RestaurantManager;
import com.shopping.services.CustomerService;
import com.shopping.services.DeliveryPersonService;
import com.shopping.services.FoodItemService;
import com.shopping.services.FoodTypeService;
import com.shopping.services.OrderItemService;
import com.shopping.services.OrdersService;
import com.shopping.services.PaymentService;
import com.shopping.services.RestaurantManagerService;
import com.shopping.services.RestaurantService;

@RestController
@RequestMapping("/api/test/")
public class TestController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private FoodTypeService foodTypeService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestaurantManagerService restaurantManagerService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/customers")
	public List<Customer> findAllCustomers() {
		return customerService.findAllCustomers();
	}
	
	@GetMapping("/deliverypersons")
	public List<DeliveryPerson> findDeliveryPerson() {
		return deliveryPersonService.findAllDeliveryPerson();
	}
	
	@GetMapping("/fooditems")
	public List<FoodItem> findAllFoodItems() {
		return foodItemService.findAllFoodItems();
	}
	
//	@GetMapping("/foodtypes")
//	public List<FoodType> findAllFoodTypes() {
//		return foodTypeService.findAllFoodTypes();
//	}
	
	@GetMapping("/orderitems")
	public List<OrderItem> findAllOrderItems() {
		return orderItemService.findAllOrderItems();
	}
	
	@GetMapping("/orders")
	public List<Orders> findAllOrders() {
		return ordersService.findAllOders();
	}

	@GetMapping("/payments")
	public List<Payment> findAllPayments() {
		return paymentService.findAllPayments();
	}
	
	@GetMapping("/restaurantmanagers")
	public List<RestaurantManager> findAllRestaurantManagers() {
		return restaurantManagerService.findAllRestaurantManagers();
	}
	
	@GetMapping("/restaurants")
	public List<Restaurant> findAllRestaurants() {
		return restaurantService.findAllRestaurants();
	}
}
