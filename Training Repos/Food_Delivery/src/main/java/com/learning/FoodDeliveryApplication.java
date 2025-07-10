package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.learning.dto.Register;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;
import com.learning.service.UserService;

@SpringBootApplication
public class FoodDeliveryApplication {

	public static void main(String[] args) {
		//cataching the application object
		ConfigurableApplicationContext applicationContext =	SpringApplication.run(FoodDeliveryApplication.class, args);
		
		//------------Testing Code for Service Impls-------------------------------------------
		//UserService userService = applicationContext.getBean(UserService.class);
		//Adding user
//		Register register = new Register();
//		register.setEmail("rushik@gmail.com");
//		register.setName("Rushik");
//		register.setPassword("12345678");
//		register.setAddress("Hyderabad");
//		try {
//			userService.addUser(register);
//		} catch (RecordAlreadyExistsException | IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(Register register2 : userService.getAllUsers())
//			System.out.println(register2);
//		try {
//			System.out.println(userService.getUserById(1));
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			userService.deleteUserById(5);
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//--------Food------------------------------------
//		FoodService foodService = applicationContext.getBean(FoodService.class);
//		Food food = new Food();
//		food.setFoodName("foodName1");
//		food.setFoodCost(123);
//		food.setFoodPic("Location/01");
//		food.setDescription("Desc1");
//		food.setFoodType(FOODTYPE.INDIAN);
//		try {
//			foodService.addFood(food);
//		} catch (RecordAlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(foodService.getFoodById(4));
//		for(Food food2 : foodService.getAllFood())
//			System.out.println(food2);
//		try {
//			foodService.deleteFoodById(4);
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//applicationContext.close();// closing the object
	}

}
