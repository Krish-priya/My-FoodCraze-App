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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	String name = request.getParameter("name");
    	String email = request.getParameter("email");
    	String phone = request.getParameter("phone");
    	String address = request.getParameter("address");
    	String password = request.getParameter("password");

    	UserModel user = new UserModel();
    	user.setName(name);
    	user.setEmail(email);
    	user.setPhone(phone);
    	user.setAddress(address);
    	user.setPassword(password);

    	UserDAOImpl dao = new UserDAOImpl();
    	dao.addUser(user);      // or dao.insertUser(user)

    	response.sendRedirect("login.jsp");
    }
}