package com.food.DAO;

import java.util.List;

import com.food.model.RestaurantModel;

public interface RestaurantInterface {
    void addRestaurant(RestaurantModel restaurant);
    RestaurantModel getRestaurant(int restaurantId);
    void updateRestaurant(RestaurantModel restaurant);
    void deleteRestaurant(int restaurantId);
	List<RestaurantModel> getAllRestaurant();
}
