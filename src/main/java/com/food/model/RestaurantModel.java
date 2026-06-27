package com.food.model;

public class RestaurantModel {

    private int restaurantId;
    private String restaurantName;
    private String cuisineType;
    private String address;
    private int deliveryTime;
    private int adminUserId;
    private double rating;
    private boolean active;
    private String imageUrl;

    // Default Constructor
    public RestaurantModel() {
    }

    // Constructor without Image URL
    public RestaurantModel(int restaurantId,
                           String restaurantName,
                           String cuisineType,
                           String address,
                           int deliveryTime,
                           int adminUserId,
                           double rating,
                           boolean active) {

        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.cuisineType = cuisineType;
        this.address = address;
        this.deliveryTime = deliveryTime;
        this.adminUserId = adminUserId;
        this.rating = rating;
        this.active = active;
    }

    // Constructor with Image URL
    public RestaurantModel(int restaurantId,
                           String restaurantName,
                           String cuisineType,
                           String address,
                           int deliveryTime,
                           int adminUserId,
                           double rating,
                           boolean active,
                           String imageUrl) {

        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.cuisineType = cuisineType;
        this.address = address;
        this.deliveryTime = deliveryTime;
        this.adminUserId = adminUserId;
        this.rating = rating;
        this.active = active;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "RestaurantModel{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                ", address='" + address + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", adminUserId=" + adminUserId +
                ", rating=" + rating +
                ", active=" + active +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}