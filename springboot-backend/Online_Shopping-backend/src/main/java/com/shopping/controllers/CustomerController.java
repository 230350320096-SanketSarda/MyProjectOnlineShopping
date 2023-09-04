package com.shopping.controllers;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dtos.Credentials;
import com.shopping.dtos.CustomerDto;
import com.shopping.dtos.CustomerSignUpDto;
import com.shopping.dtos.DaoToEntityConverter;
import com.shopping.dtos.FoodItemHomePageDto;
import com.shopping.dtos.HungerBuzzResponse;
import com.shopping.dtos.ListOfFoodItemIds;
import com.shopping.dtos.OrdersDto;
import com.shopping.dtos.PlaceOrderDto;
import com.shopping.dtos.RestaurantHomePageDto;
import com.shopping.entities.Customer;
import com.shopping.entities.Orders;
import com.shopping.services.CustomerService;
import com.shopping.services.FoodItemService;
import com.shopping.services.OrdersService;
import com.shopping.services.RestaurantService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private OrdersService ordersService;
	
	/*
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return customerService.findAllCustomers();
	}
	
	// HungerBuzzResponse - demo
	@GetMapping("/customertest")
	public ResponseEntity<HungerBuzzResponse> getAllCustomersTest() {
//		return customerService.findAllCustomers();
		return HungerBuzzResponse.success(customerService.findAllCustomers());
	}
	
	// getCustomerDtoById - demo
	@GetMapping("/customertest/{id}")
	public ResponseEntity<HungerBuzzResponse> getAllCustomersTest(@PathVariable("id") int id) {
		CustomerDto c = customerService.getCustomerDtoById(id);
		if (c == null)
			return HungerBuzzResponse.error("Could not fing customer by that id");
		
		return HungerBuzzResponse.success(c);
	}
	
	// demo
	@GetMapping("/customers/{id}")
	public Optional<Customer> getCustomerById(@PathVariable("id") int id) {
		return customerService.getCustomerById(id);
	}
	*/
	
	
	// Method to add customer to database
	@PostMapping("/customers/signup")
	public ResponseEntity<HungerBuzzResponse> signUp(@RequestBody CustomerSignUpDto customerSignUpDto) {
		Customer cust = DaoToEntityConverter.customerSignUpDtoToCustomerEntity(customerSignUpDto);
		customerService.saveCustomer(cust);
		return HungerBuzzResponse.success("Customer added!");
	}
	
	@PostMapping("/customers/signin")
	public ResponseEntity<HungerBuzzResponse> signIn(@RequestBody Credentials cred) {
		CustomerDto customerDto = customerService.findCustomerByEmailAndPassword(cred);
		if(customerDto == null)
			return HungerBuzzResponse.error("Couldn't find Customer with that credentials");
		return HungerBuzzResponse.success(customerDto);
	}
	
	@GetMapping("/restaurants")
	public ResponseEntity<HungerBuzzResponse> findAllRestaurants() {
		List<RestaurantHomePageDto> restDtoList = restaurantService.findAllRestaurantHomePageDtos();
		return HungerBuzzResponse.success(restDtoList);
	}
	
	@GetMapping("/fooditems/restaurant/{id}")
	public ResponseEntity<HungerBuzzResponse> findFoodItemsByRestaurantId(@PathVariable("id") int restaurantId) {
		List<FoodItemHomePageDto> foodItemsDtos = foodItemService.findAllFoodItemsFromRestaurant(restaurantId);
		if (foodItemsDtos == null) {
			return HungerBuzzResponse.error("Could not find food items with that restaurant id");
		}
		return HungerBuzzResponse.success(foodItemsDtos);
	}
	
	@PostMapping("/fooditems/cart")
	public ResponseEntity<HungerBuzzResponse> getCartItems(@RequestBody ListOfFoodItemIds listOfFoodItemIds) {
		List<FoodItemHomePageDto> foodItemsDtos = foodItemService.findAllFoodItemsByIds(listOfFoodItemIds.getItemIds());
		return HungerBuzzResponse.success(foodItemsDtos);
	}
	
	@PutMapping("/customers/{id}/address")
	public ResponseEntity<HungerBuzzResponse> updateAddress(@PathVariable("id") int id, @RequestBody CustomerDto customerDto) {
		boolean status = customerService.updateAddressByCustomerId(id, customerDto.getAddressText(), customerDto.getPinCode());
		if(!status)
			return HungerBuzzResponse.error("Couldn't update address");
		return HungerBuzzResponse.success("Ok");
	}
	
	@PostMapping("/orders/place")
	public ResponseEntity<HungerBuzzResponse> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
		System.out.println(placeOrderDto);
		OrdersDto ordersDto = ordersService.addOrder(placeOrderDto);
		if(ordersDto == null)
			return HungerBuzzResponse.error("Couldn't add order");
		return HungerBuzzResponse.success(ordersDto);
	}
	
	@GetMapping("/orders/customer/{id}")
	public ResponseEntity<HungerBuzzResponse> getAllOrdersbyCustomerId(@PathVariable("id") int customerId) {
		List<OrdersDto> ordersDtoList = ordersService.findAllOrdersByCustomerId(customerId);
		if(ordersDtoList == null || ordersDtoList.isEmpty())
			return HungerBuzzResponse.error("List empty!");
		return HungerBuzzResponse.success(ordersDtoList);
	}
}
