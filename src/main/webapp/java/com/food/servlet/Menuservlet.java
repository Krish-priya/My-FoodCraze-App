package com.food.servlet;
import com.food.DAOImpl.MenuDAOImpl;
import com.food.model.MenuModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/menu")
public class Menuservlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId = Integer.parseInt(
            request.getParameter("restaurantId")
        );
        String id = request.getParameter("restaurantId");

        if (id == null || id.equals("null") || id.isEmpty()) {
            response.sendRedirect("restaurant");
            return;
        }
        MenuDAOImpl menuDAO;
        HttpSession session = request.getSession();
        session.setAttribute("restaurantId", restaurantId);
		try {
			menuDAO = new MenuDAOImpl();
			 List<MenuModel> menuList =
			            menuDAO.getMenuByRestaurantId(restaurantId);

			        request.setAttribute("menuList", menuList);

			        request.getRequestDispatcher("menu.jsp")
			               .forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
    }
}
