package com.food.DAOImpl;

import com.food.DAO.RestaurantInterface;
import com.food.model.RestaurantModel;
import com.food.utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAOImpl implements RestaurantInterface {

    // ─── CREATE ──────────────────────────────────────────────────────────────
	public List<RestaurantModel> getAllRestaurant() {

        List<RestaurantModel> restaurantList =
                new ArrayList<>();

        String query =
            "SELECT * FROM food_delivery_app.restaurant";

        try(
                Connection con = DBConnection.getConnection();    			
        		PreparedStatement pstmt =
                        con.prepareStatement(query);

                ResultSet rs = pstmt.executeQuery();
) {
        	System.out.println("Connection = " + con);

            while (rs.next()) {

                RestaurantModel restaurant =
                    new RestaurantModel(
                        rs.getInt("restaurant_id"),
                        rs.getString("restaurant_name"),
                        rs.getString("cuisine_type"),
                        rs.getString("address"),
                        rs.getInt("deliveryTime"),
                        rs.getInt("adminUserId"),
                        rs.getDouble("rating"),
                        rs.getBoolean("isActive"),
                        rs.getString("imageUrl"));
                  

                restaurantList.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantList;
    }
	
    @Override
    public void addRestaurant(RestaurantModel restaurant) {
        String sql = "INSERT INTO food_delivery_app.restaurant (restaurant_name, address, cuisine_type, " +
                     "deliverytime, adminUserId, rating, isactive) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, restaurant.getRestaurantName());
            ps.setString(2, restaurant.getAddress());
            ps.setString(3, restaurant.getCuisineType());
            ps.setInt(4, restaurant.getDeliveryTime());
            ps.setInt(5,restaurant.getAdminUserId());
            ps.setDouble(6, restaurant.getRating());
            ps.setBoolean(7, restaurant.isActive());

            int rows = ps.executeUpdate();
            System.out.println(rows + " restaurant added: " + restaurant.getRestaurantName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─── READ ─────────────────────────────────────────────────────────────────

    @Override
    public RestaurantModel getRestaurant(int restaurantId) {
        String sql = "SELECT * FROM food_delivery_app.restaurant WHERE restaurant_id = ?";
        RestaurantModel restaurant = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                restaurant = new RestaurantModel(
                    rs.getInt("restaurant_id"),
                    rs.getString("restaurant_name"),
                    rs.getString("address"),
                    rs.getString("cuisine_type"),
                    rs.getInt("deliveryTime"),
                    rs.getInt("adminUserId"),
                    rs.getDouble("rating"),
                    rs.getBoolean("isActive"),
                    rs.getString("imageUrl"));
                System.out.println("Found: " + restaurant);
            } else {
                System.out.println("No restaurant found with ID: " + restaurantId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    // ─── UPDATE ───────────────────────────────────────────────────────────────

    @Override
    public void updateRestaurant(RestaurantModel restaurant) {
        String sql = "UPDATE food_delivery_app.restaurant SET restaurant_name = ?, address = ?, " +
                     "cuisine_type = ?, deliverytime = ?,adminUser_id= ? , rating = ? ,isActive= ? " +
                     "WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, restaurant.getRestaurantName());
            ps.setString(2, restaurant.getAddress());
            ps.setString(3, restaurant.getCuisineType());
            ps.setInt(4, restaurant.getDeliveryTime());
            ps.setInt(5,restaurant.getAdminUserId());
            ps.setDouble(6, restaurant.getRating());
            ps.setBoolean(7, restaurant.isActive());
            ps.setInt(8, restaurant.getRestaurantId());
            

            int rows = ps.executeUpdate();
            System.out.println(rows + " restaurant updated: ID " + restaurant.getRestaurantId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─── DELETE ───────────────────────────────────────────────────────────────

    @Override
    public void deleteRestaurant(int restaurantId) {
        String sql = "DELETE FROM food_delivery_app.restaurant WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            int rows = ps.executeUpdate();
            System.out.println(rows + " restaurant deleted: ID " + restaurantId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}