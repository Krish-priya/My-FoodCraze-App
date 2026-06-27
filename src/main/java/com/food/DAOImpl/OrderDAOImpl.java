package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import com.food.DAO.OrderInterface;
import com.food.utility.DBConnection;
import com.food.model.OrderTableModel;

public class OrderDAOImpl implements OrderInterface {

    private static final String INSERT_ORDER =
            "INSERT INTO orders(id, restaurant_id, order_date, total_amount, status, payment_method) VALUES(?,?,?,?,?,?)";

    private static final String GET_ORDER =
            "SELECT * FROM orders WHERE order_id=?";

    private static final String GET_ALL_ORDERS =
            "SELECT * FROM orders";

    private static final String GET_ORDERS_BY_USER =
            "SELECT * FROM orders WHERE id=? ORDER BY order_date DESC";

    private static final String UPDATE_STATUS =
            "UPDATE orders SET status=? WHERE order_id=?";

    private static final String DELETE_ORDER =
            "DELETE FROM orders WHERE order_id=?";

    @Override
    public int addOrder(OrderTableModel order) {

        int orderId = 0;

        try (Connection con = DBConnection.getConnection();

             PreparedStatement pstmt =
             con.prepareStatement(
                     INSERT_ORDER,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setTimestamp(3, order.getOrderDate());
            pstmt.setDouble(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMethod());

            int rows = pstmt.executeUpdate();

            if(rows > 0){

                ResultSet rs = pstmt.getGeneratedKeys();

                if(rs.next()){

                    orderId = rs.getInt(1);

                }

            }

        }
        catch(Exception e){

            e.printStackTrace();

        }

        return orderId;
    }
    @Override
    public OrderTableModel getOrder(int orderId) {

        OrderTableModel order = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(GET_ORDER)) {

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                order = new OrderTableModel(
                        rs.getInt("order_id"),
                        rs.getInt("id"),
                        rs.getInt("restaurant_id"),
                        rs.getTimestamp("order_date"),
                        rs.getDouble("total_amount"),
                        rs.getString("status"),
                        rs.getString("payment_method"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<OrderTableModel> getAllOrders() {

        List<OrderTableModel> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(GET_ALL_ORDERS)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                OrderTableModel order = new OrderTableModel(
                        rs.getInt("order_id"),
                        rs.getInt("id"),
                        rs.getInt("restaurant_id"),
                        rs.getTimestamp("order_date"),
                        rs.getDouble("total_amount"),
                        rs.getString("status"),
                        rs.getString("payment_method"));

                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<OrderTableModel> getOrdersByUser(int userId) {

        List<OrderTableModel> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(GET_ORDERS_BY_USER)) {

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();

            while (rs.next()) {

                OrderTableModel order = new OrderTableModel(

                        rs.getInt("order_id"),
                        rs.getInt("id"),
                        rs.getInt("restaurant_id"),
                        rs.getTimestamp("order_date"),
                        rs.getDouble("total_amount"),
                        rs.getString("status"),
                        rs.getString("payment_method"));
                int restaurantId = rs.getInt("restaurant_id");

                order.setRestaurant(
                        restaurantDAO.getRestaurant(restaurantId)
                );

                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {

        boolean flag = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(UPDATE_STATUS)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);

            flag = pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public boolean deleteOrder(int orderId) {

        boolean flag = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(DELETE_ORDER)) {

            pstmt.setInt(1, orderId);

            flag = pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

}