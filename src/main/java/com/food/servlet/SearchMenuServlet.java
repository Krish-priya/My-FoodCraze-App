package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAO.MenuInterface;
import com.food.DAOImpl.MenuDAOImpl;
import com.food.model.MenuModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchMenu")
public class SearchMenuServlet extends HttpServlet {

    MenuInterface menuDAO = new MenuDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("search");

        List<MenuModel> menuList =
                menuDAO.searchMenu(keyword);

        request.setAttribute("menuList", menuList);

        request.getRequestDispatcher("searchResult.jsp")
               .forward(request, response);
    }
}