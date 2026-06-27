package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.CartDAOImpl;
import com.food.model.CartModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        CartDAOImpl dao = new CartDAOImpl();

        List<CartModel> cart = dao.getAllCartItems();

        double grandTotal = 0;

        for (CartModel item : cart) {
            grandTotal += item.getPrice() * item.getQuantity();
        }

        request.setAttribute("cart", cart);
        request.setAttribute("grandTotal", grandTotal);

        request.getRequestDispatcher("checkout.jsp")
               .forward(request, response);
    }
}