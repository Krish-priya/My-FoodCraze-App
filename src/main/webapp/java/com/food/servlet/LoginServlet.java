package com.food.servlet;

import java.io.IOException;


import com.food.DAOImpl.UserDAOImpl;
import com.food.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	String email = request.getParameter("email");
    	String password = request.getParameter("password");

    	UserDAOImpl dao = new UserDAOImpl();

    	UserModel user = dao.getUserByEmailAndPassword(email, password);
    	// or: dao.validateUser(email, password)

    	if (user != null) {
    	    request.getSession().setAttribute("loggedUser", user);
    	    response.sendRedirect("restaurant");
    	} else {
    	    response.sendRedirect("login.jsp?error=Invalid");
    	}
    }
}
