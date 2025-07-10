package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.dto.Food;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;

public interface FoodService {
	public Food addFood(Food food) throws RecordAlreadyExistsException;
	public Food getFoodById(int id) throws IdNotFoundException;
	public Food updateFoodById(int id, Food food) throws IdNotFoundException, RecordAlreadyExistsException;
	public Food[] getAllFood();
	public String deleteFoodById(int id) throws IdNotFoundException;
}
