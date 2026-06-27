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

@SuppressWarnings("serial")
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	CartDAOImpl dao = new CartDAOImpl();

    	String action = request.getParameter("action");

    	if (action != null) {
    	    int menuId = Integer.parseInt(request.getParameter("menuId"));

    	    if ("remove".equals(action)) {
    	        dao.deleteCartItem(menuId);

    	    } else if ("increase".equals(action)) {
    	        dao.increaseQuantity(menuId);

    	    } else if ("decrease".equals(action)) {
    	        dao.decreaseQuantity(menuId);
    	    }
    	}
    	List<CartModel> cartList = dao.getAllCartItems();
    	request.setAttribute("cartList", cartList);
    	request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int menuId = Integer.parseInt(request.getParameter("menuId"));
        String menuName = request.getParameter("menuName");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String imageUrl = request.getParameter("imageUrl");

        CartModel cart = new CartModel();
        cart.setMenuId(menuId);
        cart.setMenuName(menuName);
        cart.setPrice(price);
        cart.setQuantity(quantity);
        cart.setImageUrl(imageUrl);

        CartDAOImpl dao = new CartDAOImpl();
        dao.addCartItem(cart);

        response.sendRedirect("cart");
    }
}
