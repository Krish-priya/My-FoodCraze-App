package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.OrderItemsInterface;
import com.food.utility.DBConnection;
import com.food.model.OrderItemModel;

public class OrderItemsDAOImpl implements OrderItemsInterface {

	private static final String INSERT =
	        "INSERT INTO order_item(orderId, menuId, quantity, itemTotal) VALUES(?,?,?,?)";

	private static final String GET_BY_ORDER =
			"SELECT oi.orderItemId, " +
			"oi.orderId, " +
			"oi.menuId, " +
			"oi.quantity, " +
			"oi.itemTotal, " +
			"m.MenuName, " +
			"m.Price, " +
			"m.ImageUrl " +
			"FROM order_item oi " +
			"INNER JOIN menu m ON oi.menuId = m.MenuID " +
			"WHERE oi.orderId=?";

	private static final String UPDATE =
	        "UPDATE order_item SET quantity=?, itemTotal=? WHERE orderItemId=?";

	private static final String DELETE =
	        "DELETE FROM order_item WHERE orderItemId=?";

    @Override
    public int addOrderItem(OrderItemModel item) {

        int result = 0;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(INSERT)) {

            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getItemTotal());

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<OrderItemModel> getOrderItemsByOrderId(int orderId) {

        List<OrderItemModel> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(GET_BY_ORDER)) {

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){

                OrderItemModel item=new OrderItemModel();

                item.setOrderItemId(rs.getInt("orderItemId"));
                item.setOrderId(rs.getInt("orderId"));
                item.setMenuId(rs.getInt("menuId"));

                item.setQuantity(rs.getInt("quantity"));

                item.setItemTotal(rs.getDouble("itemTotal"));

                item.setMenuName(rs.getString("MenuName"));

                item.setPrice(rs.getDouble("Price"));

                item.setImageUrl(rs.getString("ImageUrl"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateOrderItem(OrderItemModel item) {

        boolean status = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(UPDATE)) {

            pstmt.setInt(1, item.getQuantity());
            pstmt.setDouble(2, item.getItemTotal());
            pstmt.setInt(3, item.getOrderItemId());

            status = pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) {

        boolean status = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(DELETE)) {

            pstmt.setInt(1, orderItemId);

            status = pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

}