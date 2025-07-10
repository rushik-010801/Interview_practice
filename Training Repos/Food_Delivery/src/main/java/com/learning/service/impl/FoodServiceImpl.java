package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;
import com.learning.repo.FoodRepository;
import com.learning.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	FoodRepository foodRepository;
	
	//this method is used for adding the food details
	@Override
	public Food addFood(Food food) throws RecordAlreadyExistsException {
		// TODO Auto-generated method stub
		if(foodRepository.existsByFoodName(food.getFoodName())) {
			throw new RecordAlreadyExistsException("This record Already Exists.");
		}
		Food food2 = foodRepository.save(food);
		if(food2 != null) {
			return food2;
		}
		else
			return null;
	}

	//this method is used for getting the food by ID
	@Override
	public Food getFoodById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Food> optional = foodRepository.findById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException(String.valueOf(id));
		}
		else {
			return optional.get();
		}
	}

	//this method is used for updating food details
	@Override
	public Food updateFoodById(int id, Food food) throws IdNotFoundException, RecordAlreadyExistsException {
		// TODO Auto-generated method stub
		if(foodRepository.findById(id) == null) {
			throw new IdNotFoundException(String.valueOf(id));
		}
		else {
			food.setFoodId(id);
			return addFood(food);
		}
	}

	//this method is to get all the food details
	@Override
	public Food[] getAllFood() {
		// TODO Auto-generated method stub
		List<Food> list = foodRepository.findAll();
		Food[] food = new Food[list.size()];
		return food;
	}

	//this method is used for deleting the food records
	@Override
	public String deleteFoodById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Food food = getFoodById(id);
		if(food == null) {
			throw new IdNotFoundException(String.valueOf(id));
		}
		else {
			foodRepository.deleteById(id);
			return "success";
		}
	}

}
