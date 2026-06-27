package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAO.RestaurantInterface;
import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.model.RestaurantModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    RestaurantInterface restaurantDAO = new RestaurantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String action = request.getParameter("action");

    	if(action == null){
    	    action = "list";
    	}

    	switch(action){

    	case "list":

    	    String search = request.getParameter("search");

    	    List<RestaurantModel> restaurantList;

    	    if(search != null && !search.trim().isEmpty()){

    	        restaurantList = restaurantDAO.searchRestaurant(search);

    	    }else{

    	        restaurantList = restaurantDAO.getAllRestaurant();

    	    }

    	    request.setAttribute("allRestaurant", restaurantList);

    	    request.getRequestDispatcher("restaurant.jsp").forward(request,response);

    	    break;

    	case "edit":

    	    int restaurantId =
    	    Integer.parseInt(request.getParameter("id"));

    	    RestaurantModel restaurant =
    	    restaurantDAO.getRestaurant(restaurantId);

    	    request.setAttribute("restaurant", restaurant);

    	    request.getRequestDispatcher("restaurant.jsp").forward(request,response);

    	    break;

    	case "delete":

    	    int deleteId =
    	    Integer.parseInt(request.getParameter("id"));

    	    restaurantDAO.deleteRestaurant(deleteId);

    	    response.sendRedirect("restaurant");

    	    break;
        default:

        	response.sendRedirect("restaurant");
            break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {

            RestaurantModel restaurant = new RestaurantModel();

            restaurant.setRestaurantName(request.getParameter("restaurantName"));
            restaurant.setAddress(request.getParameter("address"));
            restaurant.setCuisineType(request.getParameter("cuisineType"));
            restaurant.setDeliveryTime(Integer.parseInt(request.getParameter("deliveryTime")));
            restaurant.setAdminUserId(Integer.parseInt(request.getParameter("adminUserId")));
            restaurant.setRating(Double.parseDouble(request.getParameter("rating")));
            restaurant.setActive(Boolean.parseBoolean(request.getParameter("isActive")));
            restaurant.setImageUrl(request.getParameter("imageUrl"));

            restaurantDAO.addRestaurant(restaurant);

            response.sendRedirect("restaurant");
        }

        else if ("update".equals(action)) {

            RestaurantModel restaurant = new RestaurantModel();

            restaurant.setRestaurantId(Integer.parseInt(request.getParameter("restaurantId")));
            restaurant.setRestaurantName(request.getParameter("restaurantName"));
            restaurant.setAddress(request.getParameter("address"));
            restaurant.setCuisineType(request.getParameter("cuisineType"));
            restaurant.setDeliveryTime(Integer.parseInt(request.getParameter("deliveryTime")));
            restaurant.setAdminUserId(Integer.parseInt(request.getParameter("adminUserId")));
            restaurant.setRating(Double.parseDouble(request.getParameter("rating")));
            restaurant.setActive(Boolean.parseBoolean(request.getParameter("isActive")));
            restaurant.setImageUrl(request.getParameter("imageUrl"));

            restaurantDAO.updateRestaurant(restaurant);

            response.sendRedirect("restaurant");
        }

    }
}