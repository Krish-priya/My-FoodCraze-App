package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.WishlistDAOImpl;
import com.food.model.UserModel;
import com.food.model.WishlistModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    WishlistDAOImpl dao = new WishlistDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        UserModel user =
        		(UserModel)request.getSession().getAttribute("loggedUser");

        		if(user==null){
        		    response.sendRedirect("login.jsp");
        		    return;
        		}

        		int userId=user.getId();

        if("add".equals(action)) {

            int menuId =
                    Integer.parseInt(request.getParameter("menuId"));

            if (!dao.isAlreadyAdded(userId, menuId)) {

                WishlistModel wishlist = new WishlistModel();

                wishlist.setUserId(userId);
                wishlist.setMenuId(menuId);

                dao.addWishlistItem(wishlist);
            }

            Integer restaurantId =
                    (Integer) request.getSession().getAttribute("restaurantId");

            response.sendRedirect("menu?restaurantId=" + restaurantId);

        }
        else if("removeByMenu".equals(action)){

        	int menuId=
        	Integer.parseInt(request.getParameter("menuId"));

        	dao.removeByMenu(userId,menuId);

        	response.sendRedirect(
        	"menu?restaurantId="+
        	session.getAttribute("restaurantId")
        	);

        	}

        else if("remove".equals(action)){

            int wishlistId =
                    Integer.parseInt(request.getParameter("wishlistId"));

            dao.removeWishlistItem(wishlistId);

            response.sendRedirect("wishlist");
        }

        else {

            List<WishlistModel> wishlist =
                    dao.getWishlistByUser(userId);

            request.setAttribute("wishlist", wishlist);

            request.getRequestDispatcher("wishlist.jsp")
                    .forward(request, response);
        }

    }
}