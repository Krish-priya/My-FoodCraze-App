package com.food.model;

public class CartModel {

    private int menuId;
    private int restaurantId;
    private String menuName;
    private double price;
    private int quantity;
    private String imageUrl;

    public CartModel() {
    }

    public CartModel(int menuId,
            int restaurantId,
            String menuName,
            double price,
            int quantity) {

    	this.menuId = menuId;
    	this.restaurantId = restaurantId;
    	this.menuName = menuName;
    	this.price = price;
    	this.quantity = quantity;
}

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

}