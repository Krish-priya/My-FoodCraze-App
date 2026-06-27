package com.food.DAO;

import com.food.model.OrderTableModel;

public interface OrderTableInterface {
	@SuppressWarnings("rawtypes")
	public void addOrderTable(OrderTableModel ordertable);
	@SuppressWarnings("rawtypes")
	public OrderTableModel getOrderTable(int ordertableid);
	@SuppressWarnings("rawtypes")
	public void updateOrderTable(OrderTableModel ordertable);
	public void deleteOrderTable(int ordertableid);
}

