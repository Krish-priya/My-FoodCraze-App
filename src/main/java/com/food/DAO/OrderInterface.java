package com.food.DAO;

import java.util.List;

import com.food.model.OrderTableModel;

public interface OrderInterface {

    int addOrder(OrderTableModel order);

    OrderTableModel getOrder(int orderId);

    List<OrderTableModel> getAllOrders();

    List<OrderTableModel> getOrdersByUser(int userId);

    boolean updateOrderStatus(int orderId, String status);

    boolean deleteOrder(int orderId);
}
