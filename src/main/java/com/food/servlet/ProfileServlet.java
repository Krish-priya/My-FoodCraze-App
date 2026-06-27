package com.food.servlet;

import java.io.IOException;

import com.food.DAOImpl.UserDAOImpl;
import com.food.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session==null || session.getAttribute("loggedUser")==null){

            response.sendRedirect("login.jsp");
            return;
        }

        UserModel loginUser =
                (UserModel)session.getAttribute("loggedUser");

        UserDAOImpl dao=new UserDAOImpl();

        UserModel user=dao.getUser(loginUser.getId());

        request.setAttribute("user", user);

        request.getRequestDispatcher("profile.jsp")
               .forward(request,response);
    }
}