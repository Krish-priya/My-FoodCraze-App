package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.CartInterface;
import com.food.model.CartModel;
import com.food.utility.DBConnection;

public class CartDAOImpl implements CartInterface {

    @Override
    public void addCartItem(CartModel cart) {

    	String sql =
    		    "INSERT INTO cart(menuId, menuName, price, quantity) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cart.getMenuId());
            ps.setString(2, cart.getMenuName());
            ps.setDouble(3, cart.getPrice());
            ps.setInt(4, cart.getQuantity());


            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartModel> getAllCartItems() {

        List<CartModel> list = new ArrayList<>();

        String sql = "SELECT * FROM cart";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                CartModel cart = new CartModel();

                cart.setMenuId(rs.getInt("menuId"));
                cart.setMenuName(rs.getString("menuName"));
                cart.setPrice(rs.getDouble("price"));
                cart.setQuantity(rs.getInt("quantity"));

                list.add(cart);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void updateCartItem(int menuId, int quantity) {

        String sql = "UPDATE cart SET quantity=? WHERE menuId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, menuId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCartItem(int menuId) {

        String sql = "DELETE FROM cart WHERE menuId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menuId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearCart() {

        String sql = "DELETE FROM cart";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void increaseQuantity(int menuId) {

        String sql =
            "UPDATE cart SET quantity = quantity + 1 WHERE menuId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menuId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void decreaseQuantity(int menuId) {
    	String selectSql = "SELECT quantity FROM cart WHERE menuId = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(selectSql)){
            // Check current quantity
            ps.setInt(1, menuId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");

                if (quantity > 1) {
                    String updateSql =
                        "UPDATE cart SET quantity = quantity - 1 WHERE menuId = ?";
                    PreparedStatement updatePs =
                        con.prepareStatement(updateSql);
                    updatePs.setInt(1, menuId);
                    updatePs.executeUpdate();
                } else {
                    // If quantity is 1, remove the item
                    deleteCartItem(menuId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}