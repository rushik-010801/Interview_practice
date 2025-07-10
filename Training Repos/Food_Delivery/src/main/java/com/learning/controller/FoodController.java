package com.learning.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Food;
import com.learning.dto.Register;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;
import com.learning.service.FoodService;

@RestController
public class FoodController {
	
	@Autowired
	FoodService foodService;
	
	//this method is mapped to addFood in FoodserviceImpl
	@PostMapping("/food")
	public ResponseEntity<?> addFood(@Valid @RequestBody Food food) throws RecordAlreadyExistsException, IdNotFoundException {
			Food food2 = foodService.addFood(food);
			System.out.println(food2);
			return ResponseEntity.status(201).body(food2);
	}
	
	//this method is mapped to getAllFood in FoodserviceImpl
	@GetMapping("/food/")
	public ResponseEntity<?> getAllFood(){
		Food[] food = foodService.getAllFood();
		if(food.length == 0) {
			System.out.println("FOOD NULL");
			Map<String, String> map = new HashMap<>();
			map.put("Message" , "No record found.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(food);
	}
	
	//this method is mapped to getFoodById in FoodserviceImpl
	@GetMapping("/food/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) throws IdNotFoundException{
		Food food = foodService.getFoodById(id);
		return ResponseEntity.status(200).body(food);
	}
	
	//this method is mapped to deleteFoodById in FoodserviceImpl
	@DeleteMapping("/food/{id}")
	public ResponseEntity<?> deletefood(@PathVariable("id") int id) throws IdNotFoundException {
		String res = foodService.deleteFoodById(id);
		Map<String, String> map = new HashMap<>();
		if(res.equals("success")) {
			map.put("Message" , "Food Item Deleted Successfully.");
			return ResponseEntity.status(201).body(map);
		}
		else {
			map.put("Message" , "Food Item Not Deleted.");
			return ResponseEntity.status(404).body(map);
		}
	}
	
	//this method is mapped to updateFoodById in FoodserviceImpl
	@PutMapping("/food/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody Food food) throws IdNotFoundException, RecordAlreadyExistsException {
		Food food2 = foodService.updateFoodById(id, food);
		if(food2 != null)
			return ResponseEntity.status(201).body(food2);
		else {
			Map<String, String> map = new HashMap<>();
			map.put("Message" , "User this ID not found.");
			return ResponseEntity.status(403).body(map);
		}
			
	}
}
