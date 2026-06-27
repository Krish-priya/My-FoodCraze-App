package com.food.DAO;
import java.util.List;

import com.food.model.MenuModel;

public interface MenuInterface {
	public void addMenu(MenuModel model);
	public MenuModel getMenu(int menuId);
	public void updateMenu(MenuModel model);
	public void deleteMenu(int menuId);
	public List<MenuModel> getAllMenus();
	
}
