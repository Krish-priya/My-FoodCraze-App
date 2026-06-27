package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.CartDAOImpl;
import com.food.DAOImpl.MenuDAOImpl;
import com.food.model.CartModel;
import com.food.model.MenuModel;

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

        try {

            MenuDAOImpl menuDAO = new MenuDAOImpl();

            MenuModel menu = menuDAO.getMenu(menuId);

            CartDAOImpl cartDAO = new CartDAOImpl();

            List<CartModel> cartList = cartDAO.getAllCartItems();

            boolean found = false;

            for (CartModel item : cartList) {

                if (item.getMenuId() == menuId) {

                    cartDAO.increaseQuantity(menuId);

                    found = true;

                    break;
                }
            }

            if (!found) {

                CartModel cart = new CartModel();

                cart.setMenuId(menu.getMenuId());
                cart.setRestaurantId(menu.getRestaurantId());
                cart.setMenuName(menu.getMenuName());
                cart.setPrice(menu.getPrice());
                cart.setQuantity(1);
                cart.setImageUrl(menu.getImageUrl());

                cartDAO.addCartItem(cart);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        response.sendRedirect("cart");
    }
}