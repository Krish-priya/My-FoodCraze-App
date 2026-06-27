<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.food.model.MenuModel,java.util.Map"%>
<%@ page import="com.food.model.UserModel" %>

<%
UserModel loggedUser =
(UserModel) session.getAttribute("loggedUser");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png"
      href="<%=request.getContextPath()%>/images/logo.png">
<title>Eat Better with FoodCraze</title>
<style>
*{
    margin:0;
    padding:0;
    box-sizing:border-box;
   font-family:'Poppins',sans-serif;
}

body{
    background:#0d0d0d;
    color:white;
    overflow-x:hidden;
}
header{
    width:100%;
    padding:20px 50px;
    background:rgba(0,0,0,.9);
    backdrop-filter:blur(20px);
    border-bottom:1px solid rgba(255,255,255,.1);

    position:sticky;
    top:0;
    z-index:99999;
}

.logo{
    font-size:40px;
    font-weight:bold;
    background:linear-gradient(90deg,#ff6b00,#ff004c);
    -webkit-background-clip:text;
    -webkit-text-fill-color:transparent;
    text-decoration: none;
}

.container{
    width:90%;
    margin:auto;
    padding-top:50px;
}

.section-title{
    font-size:40px;
    margin-bottom:30px;
}

.menu-grid{
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(320px,1fr));
    gap:30px;
}

.card{
    background:rgba(255,255,255,0.05);
    border-radius:25px;
    overflow:hidden;
    border:1px solid rgba(255,255,255,0.1);
    transition:0.4s;
    position:relative;
}

.card:hover{
    transform:translateY(-10px);
    box-shadow:0 0 30px rgba(255,107,0,0.4);
}

.card img{
    width:100%;
    height:220px;
    object-fit:cover;
}

.card-content{
    padding:20px;
}

.menu-name{
    font-size:28px;
    font-weight:bold;
    transition:0.3s;
}

.menu-name:hover{
    color:#ff7b00;
}

.description{
    color:#ccc;
    margin-top:10px;
}

.price{
    margin-top:12px;
    font-size:22px;
    color:#00ff99;
    font-weight:bold;
}

.info{
    margin-top:15px;
    line-height:2;
}

.available{
    color:#00ff99;
    font-weight:bold;
}

.notavailable{
    color:red;
    font-weight:bold;
}
.add-cart-btn{
    display:inline-block;
    margin-top:15px;
    padding:12px 25px;
    border:none;
    border-radius:30px;
    cursor:pointer;
    color:white;
    font-size:16px;
    font-weight:600;
    background:linear-gradient(90deg,#ff6b00,#ff004c);
    transition:0.3s;
}

.add-cart-btn:hover{
    transform:scale(1.05);
    box-shadow:0 0 20px rgba(255,107,0,0.5);
}
.card{
    position:relative;
}

.wishlist-btn{
    position:absolute;
    top:18px;
    right:18px;
    text-decoration:none;
    font-size:28px;
    transition:.3s ease;
}

/* Empty Heart */
.wishlist-btn i{
    color:#9a9a9a;
    transition:.3s ease;
}

/* Hover */
.wishlist-btn:hover i{
    transform:scale(1.15);
}

/* Filled Heart */
.wishlist-btn.active i{
    color:#ff2d75;               /* Pink */
    text-shadow:
        0 0 8px #ff2d75,
        0 0 18px #ff2d75,
        0 0 30px #ff2d75;
}

/* Click Animation */
.wishlist-btn:active i{
    transform:scale(1.3);
}
.navbar{
    display:flex;
    justify-content:space-between;
    align-items:center;
}

.nav-links{
    display:flex;
    align-items:center;
    gap:28px;
}

.nav-links>a{

    color:white;
    text-decoration:none;
    font-size:18px;
    transition:.3s;
}

.nav-links>a:hover{

    color:#ff6b00;

}
.profile-menu{
    position:relative;
    display:flex;
    align-items:center;
    gap:10px;
    cursor:pointer;
}

.profile-pic{
    width:42px;
    height:42px;
    border-radius:50%;
    border:2px solid #ff6b00;
}

.dropdown{

    position:absolute;

    top:100%;

    right:0;

    margin-top:8px;

    width:220px;

    background:#1d1d1d;

    border-radius:12px;

    box-shadow:0 10px 25px rgba(0,0,0,.5);

    opacity:0;

    visibility:hidden;

    transform:translateY(10px);

    transition:.25s;

    z-index:999999;
}

.profile-menu:hover .dropdown{

    opacity:1;

    visibility:visible;

    transform:translateY(0);

}

.dropdown a{

    display:block;

    padding:15px 18px;

    color:#fff;

    text-decoration:none;

    font-size:17px;
}

.dropdown a:hover{

    background:#ff6b00;

    color:#fff;
}

footer{
    margin-top:60px;
    text-align:center;
    padding:20px;
    color:#999;
}
</style>

</head>
<body>
<header>

<div class="navbar">
<a href="restaurant" class="logo">
    🛒 FoodCraze
</a>

<div class="nav-links">

    <a href="restaurant">Home</a>
    <a href="restaurant">Restaurants</a>
    <a href="cart">🛒 Cart</a>
    <a href="wishlist">❤️ Wishlist</a>

    <%
    if(loggedUser==null){
    %>

        <a href="login.jsp">Login</a>

    <%
    }else{
    %>

        <div class="profile-menu">
            <img src="images/profile.png" class="profile-pic">

            <span>
                <%=loggedUser.getName()%>
            </span>

            <div class="dropdown">

                <a href="profile">My Profile</a>

                <a href="orders">My Orders</a>

                <a href="logout">Logout</a>

            </div>

        </div>

    <%
    }
    %>

</div>
</header>
<div class="container">

    <h2 class="section-title">🛒 Available Menu Items</h2>

    <div class="menu-grid">

    <%
        List<MenuModel> menuList =
            (List<MenuModel>) request.getAttribute("menuList");
    Map<Integer, Boolean> wishlistMap =
    		(Map<Integer, Boolean>) request.getAttribute("wishlistMap");

        if (menuList != null) {
            for (MenuModel menu : menuList) {
   
    %>

        <div class="card">
            <img src="<%= menu.getImageUrl() %>" alt="<%= menu.getMenuName() %>">

            <div class="card-content">

                <div class="menu-name">
                    <%= menu.getMenuName() %>
                </div>

                <div class="description">
                    <%= menu.getDescription() %>
                </div>

                <div class="price">
                    ₹ <%= menu.getPrice() %>
                </div>

                <div class="info">
                    <p>
                        <strong>Available:</strong>
                        <span class="<%= menu.isAvailable() ? "available" : "notavailable" %>">
                            <%= menu.isAvailable() ? "Yes" : "No" %>
                        </span>
                    </p>

                    <p>
                        <strong>Category:</strong>
                        <%= menu.getCategory() %>
                    </p>

                </div>
   

<form action="cart" method="post">
    <input type="hidden" name="menuId"
           value="<%=menu.getMenuId()%>">

    <button type="submit" class="add-cart-btn">
        Add To Cart
    </button>
</form>
<%
boolean added =
wishlistMap!=null &&
Boolean.TRUE.equals(wishlistMap.get(menu.getMenuId()));
%>

<a href="wishlist?action=<%=added ? "removeByMenu" : "add"%>&menuId=<%=menu.getMenuId()%>"
   class="wishlist-btn <%= added ? "active" : "" %>">

<i class="<%=added?"fa-solid":"fa-regular"%> fa-heart"></i>

</a>

            </div>
        </div>

    <%
            }
        } else {
    %>

        <h3>No menu items available.</h3>

    <%
        }
    %>

    </div>

</div>


<footer>
    © 2026 FoodCraze | Restaurant Menu
</footer>

</body>
</html>