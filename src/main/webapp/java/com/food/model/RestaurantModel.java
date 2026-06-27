package com.food.model;

public class RestaurantModel {
	private int restaurant_id;
	private String restaurant_name;
	private String cuisine_type;
	private int deliveryTime;
	private String address;
	private int adminUserId;
	private double rating;
	private boolean isActive;
	private String ImageUrl;

	public String getImageUrl() {
	    return ImageUrl;
	}

	public void setImageUrl(String ImageUrl) {
	    this.ImageUrl = ImageUrl;
	}

    public RestaurantModel() {}

    public RestaurantModel(int restaurantId, String restaurantName, String address,
                      String cuisineType,int deliveryTime,int adminUserId, double rating,boolean isActive,String ImageUrl) {
        this.restaurant_id   = restaurantId;
        this.restaurant_name = restaurantName;
        this.address        = address;
        this.cuisine_type    = cuisineType;
        this.setDeliveryTime(deliveryTime);
        this.setAdminUserId(adminUserId);
        this.rating         = rating;
        this.setActive(isActive);
        this.ImageUrl=ImageUrl;
    }

    public int getRestaurantId()                          { return restaurant_id; }
    public void setRestaurantId(int restaurant_id)         { this.restaurant_id = restaurant_id; }
    public String getRestaurantName()                     { return restaurant_name; }
    public void setRestaurantName(String restaurant_name)  { this.restaurant_name = restaurant_name; }
    public String getAddress()                            { return address; }
    public void setAddress(String address)                { this.address = address; }
    public String getCuisineType()                        { return cuisine_type; }
    public void setCuisineType(String cuisine_type)        { this.cuisine_type = cuisine_type; }
    public double getRating()                             { return rating; }
    public void setRating(double rating)                  { this.rating = rating; }
    public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


    @Override
    public String toString() {
        return "Restaurant{id=" + restaurant_id + ", name='" + restaurant_name +
               "', address='" + address + "', cuisine='" + cuisine_type +
               "', deliveryTime='" + deliveryTime + "',UserId= '"+adminUserId+", rating=" + rating +",Active="+ isActive;}

	public void setImagePath(String string) {
		// TODO Auto-generated method stub
		
	};
    }
