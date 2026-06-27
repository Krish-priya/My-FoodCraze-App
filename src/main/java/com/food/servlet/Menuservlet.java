package com.food.servlet;
import com.food.DAOImpl.MenuDAOImpl;
import com.food.DAOImpl.WishlistDAOImpl;
import com.food.model.MenuModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/menu")
public class Menuservlet extends HttpServlet {

    	protected void doGet(HttpServletRequest request,
    	        HttpServletResponse response)
    	        throws ServletException, IOException {

    	    HttpSession session = request.getSession();

    	    String restaurantParam = request.getParameter("restaurantId");

    	    Integer restaurantId = null;

    	    if (restaurantParam != null &&
    	            !restaurantParam.equals("null") &&
    	            !restaurantParam.isEmpty()) {

    	        restaurantId = Integer.parseInt(restaurantParam);
    	        session.setAttribute("restaurantId", restaurantId);

    	    } else {

    	        restaurantId = (Integer) session.getAttribute("restaurantId");

    	        if (restaurantId == null) {
    	            response.sendRedirect("restaurant");
    	            return;
    	        }
    	    }

    	    // -------- THIS PART MUST BE OUTSIDE IF-ELSE --------

    	    MenuDAOImpl menuDAO = new MenuDAOImpl();

			List<MenuModel> menuList =
			        menuDAO.getMenuByRestaurantId(restaurantId);

			WishlistDAOImpl wishDao = new WishlistDAOImpl();

			int userId = 1; // later replace with logged-in user

			Map<Integer, Boolean> wishlistMap = new HashMap<>();

			for (MenuModel menu : menuList) {

			    wishlistMap.put(
			            menu.getMenuId(),
			            wishDao.isAlreadyAdded(userId, menu.getMenuId()));
			}

			request.setAttribute("menuList", menuList);
			request.setAttribute("wishlistMap", wishlistMap);

			request.getRequestDispatcher("menu.jsp")
			        .forward(request, response);
    	}
    }

