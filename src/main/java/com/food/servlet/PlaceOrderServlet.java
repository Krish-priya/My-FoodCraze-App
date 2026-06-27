package com.food.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.food.DAOImpl.CartDAOImpl;
import com.food.DAOImpl.OrderDAOImpl;
import com.food.DAOImpl.OrderItemsDAOImpl;
import com.food.model.CartModel;
import com.food.model.OrderItemModel;
import com.food.model.OrderTableModel;
import com.food.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UserModel user =
                (UserModel) session.getAttribute("loggedUser");

        String address =
                request.getParameter("address");

        String paymentMethod =
                request.getParameter("paymentMethod");

        CartDAOImpl cartDAO = new CartDAOImpl();

        List<CartModel> cartList =
                cartDAO.getAllCartItems();

        if (cartList == null || cartList.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        double grandTotal = 0;

        for (CartModel item : cartList) {
            grandTotal += item.getPrice() * item.getQuantity();
        }

        int restaurantId =
                cartList.get(0).getRestaurantId();

        OrderTableModel order = new OrderTableModel();

        order.setUserId(user.getId());
        order.setRestaurantId(restaurantId);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(grandTotal);
        order.setStatus("Placed");
        order.setPaymentMethod(paymentMethod);
        order.setAddress(address);

        OrderDAOImpl orderDAO = new OrderDAOImpl();

        int orderId = orderDAO.addOrder(order);

        OrderItemsDAOImpl orderItemDAO =
                new OrderItemsDAOImpl();

        for (CartModel cart : cartList) {

            OrderItemModel item =
                    new OrderItemModel();

            item.setOrderId(orderId);
            item.setMenuId(cart.getMenuId());
            item.setQuantity(cart.getQuantity());
            item.setItemTotal(cart.getPrice());

            orderItemDAO.addOrderItem(item);
        }

        cartDAO.clearCart();

        response.sendRedirect("orders?success=true");
    }
}