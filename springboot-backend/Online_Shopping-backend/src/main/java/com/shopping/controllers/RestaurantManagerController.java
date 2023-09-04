package com.shopping.controllers;

import java.util.ArrayList;


import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.daos.FoodTypeDao;
import com.shopping.daos.OrdersDao;
import com.shopping.daos.RestaurantDao;
import com.shopping.dtos.Credentials;
import com.shopping.dtos.CustomerDto;
import com.shopping.dtos.CustomerSignUpDto;
import com.shopping.dtos.DaoToEntityConverter;
import com.shopping.dtos.DeliveryPersonDto;
import com.shopping.dtos.FoodItemHomePageDto;
import com.shopping.dtos.FoodTypeDto;
import com.shopping.dtos.HungerBuzzResponse;
import com.shopping.dtos.OrdersDto;
import com.shopping.dtos.RestManAndRestSignUpDto;
import com.shopping.dtos.RestaurantManagerDto;
import com.shopping.entities.Customer;
import com.shopping.entities.DeliveryPerson;
import com.shopping.entities.FoodItem;
import com.shopping.entities.FoodType;
import com.shopping.entities.Orders;
import com.shopping.entities.RestaurantManager;
import com.shopping.services.DeliveryPersonService;
import com.shopping.services.FoodItemService;
import com.shopping.services.FoodTypeService;
import com.shopping.services.OrdersService;
import com.shopping.services.RestaurantManagerService;
import com.shopping.services.RestaurantService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/")
public class RestaurantManagerController {
	
	@Autowired
	private RestaurantManagerService restaurantManagerService;
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private FoodTypeService foodTypeService;
	
	@Autowired
	private RestaurantService restaurantService;

	
	@GetMapping("/restaurantmanager/{id}")
	public ResponseEntity<HungerBuzzResponse> getRestaurantManagerById(@PathVariable("id") int id)
	{
		Optional<RestaurantManager> r = restaurantManagerService.getRestaurantManagerById(id);
		if(r==null)
			return HungerBuzzResponse.error("not found");
		
		return HungerBuzzResponse.success(r);

	}
	@PostMapping("/restaurantmanager/signin")
	public ResponseEntity<HungerBuzzResponse> signIn(@RequestBody Credentials cred)
	{
		RestaurantManagerDto restauarantManagerDto =restaurantManagerService.findRestaurantManagerByEmailAndPassword(cred);
		if(restauarantManagerDto==null)
			return HungerBuzzResponse.error("not found");
		
		return HungerBuzzResponse.success(restauarantManagerDto);
		
	}
	



	@GetMapping("/restaurantmanager/availabledeliveryperson/{status}")
	public ResponseEntity<HungerBuzzResponse> getDeliveryPersonByAvailable(@PathVariable("status") boolean status)
	{
		List<DeliveryPersonDto> dto = deliveryPersonService.findDeliveryPersonByIsAvailable(status);
		if(dto == null)
			return HungerBuzzResponse.error("not available");
		
		return HungerBuzzResponse.success(dto);

	}
	

	@PostMapping("/restaurantmanager/arrivedorders/{restaurantId}")
	public ResponseEntity<HungerBuzzResponse> getArrivedOrders(@PathVariable("restaurantId") int restaurantId) {
		
		String status = "arrived";
		List<Orders> orders = ordersService.findArrivedOrdersByRestaurantIdAndStatus(restaurantId, status);
		if(orders == null || orders.isEmpty())
			return HungerBuzzResponse.error("List Empty!");
		
		// orders is full
		List<OrdersDto> ordersDtoList = DaoToEntityConverter.ordersToOrdersDto(orders);
		
//		System.out.println(ordersDtoList);
		return HungerBuzzResponse.success(ordersDtoList);
	}
	
	@PostMapping("/restaurantmanager/allorders/{restaurantId}")
	public ResponseEntity<HungerBuzzResponse> getAllOrdersByRestaurant(@PathVariable("restaurantId") int restaurantId) {
		List<Orders> orders = ordersService.findAllOrdersByRestaurantid(restaurantId);
		if(orders == null || orders.isEmpty())
			return HungerBuzzResponse.error("List Empty!");
		List<OrdersDto> ordersDtoList = DaoToEntityConverter.ordersToOrdersDto(orders);
		return HungerBuzzResponse.success(ordersDtoList);
	}
	

	@PostMapping("/restaurantmanager/addfooditem")
	public ResponseEntity<HungerBuzzResponse> addFoodItem(@RequestBody FoodItemHomePageDto foodItemHomePageDto) {

		boolean status = foodItemService.saveFoodItemDto(foodItemHomePageDto);
		if(!status)
			return HungerBuzzResponse.error("Couldn't add food item");
		
		return HungerBuzzResponse.success("Food item added");
	}

	@GetMapping("/foodtypes")
	public ResponseEntity<HungerBuzzResponse> getAllFoodTypes() {
		List<FoodTypeDto> foodTypes = foodTypeService.findAllFoodTypes();
		return HungerBuzzResponse.success(foodTypes);
	}
	
	@GetMapping("/foodTypes/edit/{foodItemId}")
	public ResponseEntity<HungerBuzzResponse> getFoodItemDetails(@PathVariable("foodItemId") int foodItemId) {
		FoodItemHomePageDto foodItemHomePageDto = foodItemService.getDtoById(foodItemId);
		List<FoodTypeDto> foodTypes = foodTypeService.findAllFoodTypes();
		
		List<Object> resultData = new ArrayList<Object>();
		resultData.add(foodItemHomePageDto);
		resultData.add(foodTypes);
		
		return HungerBuzzResponse.success(resultData);
	}
	
	@PostMapping("/foodTypes/edit/{foodItemId}")
	public ResponseEntity<HungerBuzzResponse> updateFoodItemDetails(@RequestBody FoodItemHomePageDto foodItemHomePageDto) {
		boolean status = foodItemService.updateFoodItem(foodItemHomePageDto);
		if(!status)
			return HungerBuzzResponse.error("Couldn't update food item");
		
		return HungerBuzzResponse.success("Food item updated");
	}
	
	@PostMapping("/orders/assign/{orderId}/{deliveryPersonId}")
	public ResponseEntity<HungerBuzzResponse> assignDeliveryPersonToOrder
		(@PathVariable("orderId") int orderId, @PathVariable("deliveryPersonId") int deliveryPersonId) {
		boolean status = ordersService.assignDeliveryPersonToOrder(orderId, deliveryPersonId);
		if(status == false)
			HungerBuzzResponse.error("Order not assigned");
		
		return HungerBuzzResponse.success("Order assigned successfully");
	}
	
	@GetMapping("/fooditem/restaurant/{restaurantId}")
	public ResponseEntity<HungerBuzzResponse> getAllFoodItemsByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
		List<FoodItemHomePageDto> foodItemDtos = foodItemService.findAllFoodItemsFromRestaurant(restaurantId);
		if(foodItemDtos == null || foodItemDtos.isEmpty())
			return HungerBuzzResponse.error("No food items found, please add food items.");
		return HungerBuzzResponse.success(foodItemDtos);
	}
	
	@PostMapping("/restaurantmanager/signup")
	public ResponseEntity<HungerBuzzResponse> restManagerAndRestSignUp(@RequestBody RestManAndRestSignUpDto dto) {
		boolean status = restaurantService.restManagerAndRestSignUp(dto);
		if(status)
			return HungerBuzzResponse.success("Added Restaurant and Restaurant Manager");
		return HungerBuzzResponse.error("Could not Restaurant and Restaurant Manager");
	}

}
