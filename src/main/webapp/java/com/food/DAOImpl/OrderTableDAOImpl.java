package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.food.DAO.OrderTableInterface;
import com.food.model.OrderTableModel;
import com.food.utility.DBConnection;

public class OrderTableDAOImpl implements OrderTableInterface {

    private Connection con;

    public OrderTableDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }

    @SuppressWarnings("rawtypes")
	@Override
    public void addOrderTable(OrderTableModel orderTable) {

        String query =
                "INSERT INTO order_table\r\n"
                + "(user_id,restaurant_id,order_date,total_amount,status,payment_method)\r\n"
                + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt =
                    con.prepareStatement(query);

            pstmt.setInt(1, orderTable.getUserId());
            pstmt.setInt(2, orderTable.getRestaurantId());
            pstmt.setTimestamp(3, orderTable.getOrderDate());
            pstmt.setDouble(4, orderTable.getTotalAmount());
            pstmt.setString(5, orderTable.getStatus());
            pstmt.setString(6, orderTable.getPaymentMethod());

            pstmt.executeUpdate();

            System.out.println("Order Added Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public OrderTableModel getOrderTable(int orderTableId) {

        OrderTableModel order = null;

        String query =
                "SELECT * FROM order_table WHERE order_id=?";

        try {
            PreparedStatement pstmt =
                    con.prepareStatement(query);

            pstmt.setInt(1, orderTableId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
            	order = new OrderTableModel(
            		    rs.getInt("OrderID"),
            		    rs.getInt("UserID"),
            		    rs.getInt("RestaurantID"),
            		    rs.getTimestamp("OrderDate"),
            		    rs.getDouble("TotalAmount"),
            		    rs.getString("Status"),
            		    rs.getString("PaymentMethod")
            		);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public void updateOrderTable(OrderTableModel orderTable) {

        String query =
                "UPDATE order_table\r\n"
                + "SET total_amount=?,\r\n"
                + "status=?,\r\n"
                + "payment_method=?\r\n"
                + "WHERE order_id=?";

        try {
            PreparedStatement pstmt =
                    con.prepareStatement(query);

            pstmt.setDouble(1, orderTable.getTotalAmount());
            pstmt.setString(2, orderTable.getStatus());
            pstmt.setString(3, orderTable.getPaymentMethod());
            pstmt.setInt(4, orderTable.getOrderId());
            pstmt.executeUpdate();

            System.out.println("Order Updated Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderTable(int orderTableId) {

        String query =
                "DELETE FROM order_table WHERE order_id=?";

        try {
            PreparedStatement pstmt =
                    con.prepareStatement(query);

            pstmt.setInt(1, orderTableId);

            pstmt.executeUpdate();

            System.out.println("Order Deleted Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}