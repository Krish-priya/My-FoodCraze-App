package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.WishlistInterface;
import com.food.model.WishlistModel;
import com.food.utility.DBConnection;

public class WishlistDAOImpl implements WishlistInterface {

    @Override
    public void addWishlistItem(WishlistModel wishlist) {

        String sql = "INSERT INTO wishlist(userId, menuId) VALUES(?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, wishlist.getUserId());
            ps.setInt(2, wishlist.getMenuId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeWishlistItem(int wishlistId) {

        String sql = "DELETE FROM wishlist WHERE wishlistId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, wishlistId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<WishlistModel> getWishlistByUser(int userId) {

        List<WishlistModel> list = new ArrayList<>();

        String sql =
                "SELECT w.wishlistId, w.userId, w.menuId, " +
                "m.MenuName, m.Price, m.ImageUrl " +
                "FROM wishlist w " +
                "INNER JOIN menu m ON w.menuId = m.MenuID " +
                "WHERE w.userId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                WishlistModel wishlist = new WishlistModel();

                wishlist.setWishlistId(rs.getInt("wishlistId"));
                wishlist.setUserId(rs.getInt("userId"));
                wishlist.setMenuId(rs.getInt("menuId"));
                
                wishlist.setMenuName(rs.getString("MenuName"));
                wishlist.setPrice(rs.getDouble("Price"));
                wishlist.setImageUrl(rs.getString("ImageUrl"));
                

                list.add(wishlist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean isAlreadyAdded(int userId, int menuId) {

        String sql = "SELECT * FROM wishlist WHERE userId=? AND menuId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, menuId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public void removeByMenu(int userId,int menuId){

    	String sql=
    	"DELETE FROM wishlist WHERE userId=? AND menuId=?";

    	try(Connection con=DBConnection.getConnection();
    	PreparedStatement ps=con.prepareStatement(sql)){

    	ps.setInt(1,userId);
    	ps.setInt(2,menuId);

    	ps.executeUpdate();

    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
    public List<Integer> getWishlistMenuIds(int userId) {

        List<Integer> ids = new ArrayList<>();

        String sql = "SELECT menuId FROM wishlist WHERE userId=?";

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setInt(1,userId);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                ids.add(rs.getInt("menuId"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return ids;
    }
    @Override
    public void clearWishlist(int userId) {

        String sql = "DELETE FROM wishlist WHERE userId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}