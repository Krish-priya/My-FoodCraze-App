package com.food.DAO;

import java.util.List;
import com.food.model.OrderItemModel;

public interface OrderItemsInterface {

    int addOrderItem(OrderItemModel orderItem);

    List<OrderItemModel> getOrderItemsByOrderId(int orderId);

    boolean updateOrderItem(OrderItemModel orderItem);

    boolean deleteOrderItem(int orderItemId);

}