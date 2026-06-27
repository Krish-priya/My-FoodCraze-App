package com.food.DAO;

import com.food.model.UserModel;

public interface UserInterface {
	public void addUser(UserModel user);
	public UserModel getUser(int id);
	public void updateUser(UserModel user);
	public void deleteUser(int id);
	
}
