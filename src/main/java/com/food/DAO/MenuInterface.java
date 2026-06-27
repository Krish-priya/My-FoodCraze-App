package com.food.DAO;

import java.util.List;

import com.food.model.MenuModel;

public interface MenuInterface {
	public void addMenu(MenuModel menu);
	public MenuModel getMenu(int menuId);
	public List<MenuModel> getAllMenus();
	public void updateMenu(MenuModel menu);
	public void deleteMenu(int menuId);
	public List<MenuModel> getMenuByRestaurantId(int RestaurantID);
	List<MenuModel> searchMenu(String keyword);
}
