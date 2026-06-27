package com.food.DAO;

import com.food.model.OrderItemModel;

public interface OrderItemInterface {
	public void addOrderItem(OrderItemModel orderitem);
	public OrderItemModel getOrderItem(int orderitemid);
	public void updateOrderItem(OrderItemModel orderitem);
	public void deleteOrderItem(int orderitemid);
}
