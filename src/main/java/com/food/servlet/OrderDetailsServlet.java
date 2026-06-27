package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.OrderDAOImpl;
import com.food.DAOImpl.OrderItemsDAOImpl;
import com.food.model.OrderItemModel;
import com.food.model.OrderTableModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/orderDetails")
public class OrderDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(request.getParameter("orderId"));

        OrderDAOImpl orderDAO = new OrderDAOImpl();

        OrderItemsDAOImpl itemDAO = new OrderItemsDAOImpl();

        OrderTableModel order =
                orderDAO.getOrder(orderId);

        if(order == null){
            response.sendRedirect("orders");
            return;
        }

        List<OrderItemModel> items =
                itemDAO.getOrderItemsByOrderId(orderId);

        request.setAttribute("order", order);

        request.setAttribute("orderItems", items);

        request.getRequestDispatcher("orderDetails.jsp")
               .forward(request, response);
    }
}