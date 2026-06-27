package com.food.DAOImpl;

import com.food.DAO.UserInterface;
import com.food.model.UserModel;
import com.food.utility.DBConnection;

import java.sql.*;

public class UserDAOImpl implements UserInterface {

    @Override
    public void addUser(UserModel user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            System.out.println("User added: " + user.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public UserModel getUserByEmailAndPassword(String email, String password) {

        UserModel user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    @Override
    public UserModel getUser(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        UserModel user = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("password")
                   );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public void updateUser(UserModel user) {
        String sql = "UPDATE users SET name = ?, email = ?, phone = ?, address = ?, password = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
            System.out.println("User updated: " + user.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("User deleted: " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}