package com.food.DAO;

import java.util.List;

import com.food.model.RestaurantModel;

public interface RestaurantInterface {
	public List<RestaurantModel> getAllRestaurant();
	public void addRestaurant(RestaurantModel restaurant);
	public RestaurantModel getRestaurant(int restaurantId);
	 public void updateRestaurant(RestaurantModel restaurant);
	 public void deleteRestaurant(int restaurantId);
	 List<RestaurantModel> searchRestaurant(String keyword);
	 List<String> searchRestaurantNames(String keyword);
}
