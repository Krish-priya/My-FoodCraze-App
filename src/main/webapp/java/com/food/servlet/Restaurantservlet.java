package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.model.RestaurantModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/restaurant")
public class Restaurantservlet extends HttpServlet {
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
	RestaurantDAOImpl dao = new RestaurantDAOImpl();
	List<RestaurantModel> allRestaurant = dao.getAllRestaurant();
	req.setAttribute("allRestaurant", allRestaurant);
	RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
	rd.forward(req, res);
	System.out.println("Restaurants count: " + allRestaurant.size());
	}
}
