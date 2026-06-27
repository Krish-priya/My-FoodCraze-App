package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.OrderDAOImpl;
import com.food.model.OrderTableModel;
import com.food.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
 
        String action = request.getParameter("action");

        if ("delete".equals(action)) {

            int orderId = Integer.parseInt(request.getParameter("orderId"));

            OrderDAOImpl dao = new OrderDAOImpl();

            dao.deleteOrder(orderId);

            response.sendRedirect("orders");

            return;
        }
        UserModel user =
                (UserModel) session.getAttribute("loggedUser");

        OrderDAOImpl dao = new OrderDAOImpl();

        List<OrderTableModel> orders =
                dao.getOrdersByUser(user.getId());

        request.setAttribute("orders", orders);
        String success = request.getParameter("success");

        if(success != null){
            request.setAttribute("successMessage",
                    "🎉 Thank you for your purchase! Your order has been placed successfully.");
        }

        request.getRequestDispatcher("orders.jsp")
               .forward(request, response);
    }
}