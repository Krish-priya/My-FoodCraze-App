package com.food.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.food.DAO.RestaurantInterface;
import com.food.DAOImpl.RestaurantDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchRestaurant")
public class SearchRestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    RestaurantInterface restaurantDAO = new RestaurantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        List<String> restaurants =
                restaurantDAO.searchRestaurantNames(keyword);

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        out.print("[");

        for(int i=0;i<restaurants.size();i++){

            out.print("\""+restaurants.get(i)+"\"");

            if(i!=restaurants.size()-1){

                out.print(",");

            }

        }

        out.print("]");
    }

}