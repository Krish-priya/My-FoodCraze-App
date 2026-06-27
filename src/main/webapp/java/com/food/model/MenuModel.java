package com.food.model;

import java.sql.Timestamp;

public class MenuModel {
	private int menuId;
	private int restaurantId;
	private String menuName;
	private String description;
	private double price;
	private boolean IsAvailable;
	private String category;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;
	private String ImageUrl;

    public MenuModel() {}

    public MenuModel(int menuId,int restaurantId, String menuName,String description,double price, boolean IsAvailable,String category,Timestamp createdAt, Timestamp updatedAt,Timestamp deletedAt,String ImageUrl) {
        this.menuId    = menuId;
        this.setRestaurantId(restaurantId);
        this.menuName  = menuName;
        this.setDescription(description);
        this.price     = price;
        this.IsAvailable = IsAvailable;
        this.category=category;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
        this.setDeletedAt(deletedAt);
        this.ImageUrl=ImageUrl;
    }

    public int getMenuId()                       { return menuId; }
    public void setMenuId(int menuId)            { this.menuId = menuId; }
    public String getMenuName()                  { return menuName; }
    public void setMenuName(String menuName)     { this.menuName = menuName; }
    public String getCategory()                  { return category; }
    public void setCategory(String category)     { this.category = category; }
    public double getPrice()                     { return price; }
    public void setPrice(double price)           { this.price = price; }
    public boolean isAvailable()                 { return IsAvailable; }
    public void setAvailable(boolean available)  { this.IsAvailable = available; }

    @Override
    public String toString() {
        return "Menu{id=" + menuId + ", name='" + menuName + "', category='" 
               + category + "', price=" + price + ", available=" + IsAvailable + "}";
    }

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	public void setImageUrl(String ImageUrl) {
		// TODO Auto-generated method stub
		this.ImageUrl=ImageUrl;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
}