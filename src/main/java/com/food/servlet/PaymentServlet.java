package com.food.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public PaymentServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");
        String grandTotal = request.getParameter("grandTotal");

        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("error", "Delivery address is required.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            return;
        }

        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            request.setAttribute("error", "Please select a payment method.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            return;
        }

        request.setAttribute("address", address);
        request.setAttribute("paymentMethod", paymentMethod);
        request.setAttribute("grandTotal", grandTotal);

        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("checkout");
    }
}