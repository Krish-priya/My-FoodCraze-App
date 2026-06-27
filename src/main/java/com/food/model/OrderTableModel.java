package com.food.model;

import java.sql.Timestamp;
import com.food.model.RestaurantModel;

public class OrderTableModel  {
	
	    private int order_id;
	    private int id;
	    private int restaurant_id;
	    private Timestamp order_date;
	    private double total_amount;
	    private String status;
	    private String payment_method;
	    private String address;
	    private RestaurantModel restaurant;
	    
	    public String getRestaurantName() {
	        if (restaurant != null) {
	            return restaurant.getRestaurantName();
	        }
	        return "";
	    }
	    public RestaurantModel getRestaurant() {
	        return restaurant;
	    }

	    public void setRestaurant(RestaurantModel restaurant) {
	        this.restaurant = restaurant;
	    }

    public OrderTableModel() {
    }

    public OrderTableModel(int order_id, int id,
            int restaurant_id, Timestamp order_date,
            double total_amount,
            String status,
            String payment_method) {

        this.order_id = order_id;
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
        this.payment_method = payment_method;
    }

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public int getUserId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurant_id;
    }

    public void setRestaurantId(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public double getTotalAmount() {
        return total_amount;
    }

    public void setTotalAmount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getOrderDate() {
		return order_date;
	}

    public void setOrderDate(Timestamp order_date) {
        this.order_date = order_date;
    }

	public String getPaymentMethod() {
		return payment_method;
	}

	public void setPaymentMethod(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}