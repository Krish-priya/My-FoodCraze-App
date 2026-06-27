package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.MenuInterface;
import com.food.model.MenuModel;
import com.food.utility.DBConnection;

public class MenuDAOImpl implements MenuInterface {

    private Connection con;

    public MenuDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }

    @Override
    public void addMenu(MenuModel menu) {

        String query =
                "INSERT INTO menu " +
                "(restaurantId, ItemName, Description, Price, IsAvailable, Category, CreatedAt, UpdatedAt, DeletedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getCategory());
            pstmt.setTimestamp(7, menu.getCreatedAt());
            pstmt.setTimestamp(8, menu.getUpdatedAt());
            pstmt.setTimestamp(9, menu.getDeletedAt());
            

            pstmt.executeUpdate();

            System.out.println("Menu Added Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MenuModel getMenu(int menuId) {

        MenuModel menu = null;

        String query =
                "SELECT * FROM menu WHERE MenuID=?";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, menuId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                menu = new MenuModel(
                        rs.getInt("MenuID"),
                        rs.getInt("restaurantId"),
                        rs.getString("ItemName"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getBoolean("IsAvailable"),
                        rs.getString("Category"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getTimestamp("UpdatedAt"),
                        rs.getTimestamp("DeletedAt"),
                        rs.getString("ImageUrl"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menu;
    }

    @Override
    public List<MenuModel> getAllMenus() {

        List<MenuModel> menuList = new ArrayList<>();

        String query = "SELECT * FROM menu";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                MenuModel menu = new MenuModel(
                        rs.getInt("MenuID"),
                        rs.getInt("restaurantId"),
                        rs.getString("ItemName"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getBoolean("IsAvailable"),
                        rs.getString("Category"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getTimestamp("UpdatedAt"),
                        rs.getTimestamp("DeletedAt"),
                        rs.getString("ImageUrl"));

                menuList.add(menu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList;
    }

    @Override
    public void updateMenu(MenuModel menu) {

        String query =
                "UPDATE menu SET " +
                "restaurantId=?, " +
                "ItemName=?, " +
                "Description=?, " +
                "Price=?, " +
                "IsAvailable=?, " +
                "Category=?, " +
                "UpdatedAt=? " +
                "WHERE MenuID=?";

        try {

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getCategory());
            pstmt.setTimestamp(7, menu.getUpdatedAt());
            pstmt.setInt(8, menu.getMenuId());

            pstmt.executeUpdate();

            System.out.println("Menu Updated Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {

        String query =
                "DELETE FROM menu WHERE MenuID=?";

        try {

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, menuId);

            pstmt.executeUpdate();

            System.out.println("Menu Deleted Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<MenuModel> getMenuByRestaurantId(int RestaurantID) {

        List<MenuModel> menuList = new ArrayList<>();

        String sql = "SELECT * FROM menu WHERE restaurantId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, RestaurantID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MenuModel menu = new MenuModel();

                menu.setMenuId(rs.getInt("MenuID"));
                menu.setRestaurantId(rs.getInt("restaurantId"));
                menu.setMenuName(rs.getString("MenuName"));
                menu.setDescription(rs.getString("Description"));
                menu.setPrice(rs.getDouble("Price"));
                menu.setAvailable(rs.getBoolean("IsAvailable"));
                menu.setCategory(rs.getString("Category"));
                menu.setImageUrl(rs.getString("imageUrl"));

                menuList.add(menu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menuList;
    }
}