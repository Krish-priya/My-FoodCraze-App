package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.food.DAO.OrderItemInterface;
import com.food.model.OrderItemModel;
import com.food.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemInterface {

    private Connection con;

    public OrderItemDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }

    @Override
    public void addOrderItem(OrderItemModel orderItem) {

        String query =
            "INSERT INTO order_item\r\n"
            + "(OrderID,MenuID,Quantity,ItemTotal)\r\n"
            + "VALUES(?,?,?,?)";

        try {
            PreparedStatement pstmt =
                    con.prepareStatement(query);

            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getItemTotal());

            pstmt.executeUpdate();

            System.out.println("Order Item Added Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItemModel getOrderItem(int orderItemId) {

        OrderItemModel item = null;

        String query =
            "SELECT * FROM order_item WHERE order_item_id=?";

        try {
            PreparedStatement pstmt =
                    con.prepareStatement(query);

            pstmt.setInt(1, orderItemId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                item = new OrderItemModel(
                        rs.getInt("order_item_id"),
                        rs.getInt("order_id"),
                        rs.getInt("menu_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public void updateOrderItem(OrderItemModel orderItem) {

        String query =
            "UPDATE order_item SET quantity=?, total_price=? WHERE order_item_id=?";

        try {
            PreparedStatement pstmt =
                    con.prepareStatement(query);

            pstmt.setInt(1, orderItem.getQuantity());
            pstmt.setDouble(2, orderItem.getItemTotal());
            pstmt.setInt(3, orderItem.getOrderItemId());

            pstmt.executeUpdate();

            System.out.println("Order Item Updated Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {

        String query =
            "DELETE FROM order_item WHERE order_item_id=?";

        try {
            PreparedStatement pstmt =
                    con.prepareStatement(query);

            pstmt.setInt(1, orderItemId);

            pstmt.executeUpdate();

            System.out.println("Order Item Deleted Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}