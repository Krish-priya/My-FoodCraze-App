<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.food.model.CartModel" %>
<%@ page import="com.food.model.UserModel" %>

<%
UserModel loggedUser =
(UserModel) session.getAttribute("loggedUser");
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<meta charset="UTF-8">
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

    margin:40px auto;

    position:relative;

}
h2{
    margin-bottom:25px;
    font-size:40px;
}
.food-image{
    width:80px;
    height:80px;
    object-fit:cover;
    border-radius:12px;
    box-shadow:0 4px 12px rgba(0,0,0,0.3);
    transition:0.3s;
}

.food-image:hover{
    transform:scale(1.05);
}

table{
    width:100%;
    border-collapse:collapse;
    background:rgba(255,255,255,0.05);
    border-radius:20px;
    overflow:hidden;
    box-shadow:
        0 0 25px rgba(255,107,0,0.18),
        0 10px 35px rgba(0,0,0,0.4);
}
th{
    background:#1b1b1b;
    padding:18px;
    font-size:18px;
    color:#ffb366;
    text-transform:uppercase;
    letter-spacing:1px;
}

td{
    padding:18px;
    text-align:center;
    border-bottom:1px solid rgba(255,255,255,0.08);
}

tr{
    transition:0.3s ease;
}

tr:hover{
    background:rgba(255,107,0,0.08);
    box-shadow:inset 0 0 15px rgba(255,107,0,0.2);
}

.total{
    text-align:right;
    margin-top:25px;
    font-size:24px;
    font-weight:bold;
}

.btn{
    display:inline-block;
    margin-top:30px;
    margin-right:15px;
    padding:14px 30px;
    text-decoration:none;
    color:white;
    border:none;
    border-radius:30px;
    cursor:pointer;
    background:linear-gradient(90deg,#ff6b00,#ff004c);
    font-size:18px;
    transition:all 0.3s ease;
    box-shadow:0 0 18px rgba(255,107,0,0.35);
}

.btn:hover{
    transform:translateY(-3px) scale(1.04);
    box-shadow:0 0 28px rgba(255,107,0,0.6);
}

.btn:hover{
    transform:scale(1.05);
}
.qty-btn{
    display:inline-block;
    width:36px;
    height:36px;
    line-height:36px;
    text-align:center;
    background:#ff6b00;
    color:white;
    text-decoration:none;
    border-radius:50%;
    font-weight:bold;
    margin:0 8px;
    transition:0.3s;
    box-shadow:0 0 12px rgba(255,107,0,0.45);
}

.qty-btn:hover{
    transform:scale(1.1);
    box-shadow:0 0 20px rgba(255,107,0,0.7);
}

.qty-value{
    font-size:18px;
    font-weight:bold;
}
.remove-btn{
    display:inline-block;
    padding:10px 18px;
    background:linear-gradient(90deg,#ff6b00,#ff004c);
    color:white;
    text-decoration:none;
    border-radius:20px;
    font-weight:600;
    transition:0.3s;
    box-shadow:0 0 15px rgba(255,0,76,0.3);
}

.remove-btn:hover{
    transform:scale(1.05);
    box-shadow:0 0 22px rgba(255,0,76,0.6);
}
h2{
    margin-bottom:25px;
    font-size:40px;
    color:white;
    text-shadow:0 0 15px rgba(255,107,0,0.5);
}
.user-name{

    color:white;

    font-size:18px;

    font-weight:500;

}
.remove-btn:hover{
    background:#b91c1c;
    transform:scale(1.05);
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
    color:#999;
    padding:25px;
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

    <span class="user-name">
        <%=loggedUser.getName()%>
    </span>

    <div class="dropdown">

        <a href="profile">👤 My Profile</a>

        <a href="orders">📦 My Orders</a>

        <a href="logout">🚪 Logout</a>

    </div>

</div>

    <%
    }
    %>
</div>
</div>
</header>
<div class="container">

<h2>My Cart</h2>

<table>

<tr>
    <th>Menu ID</th>
    <th>Menu Name</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Total</th>
    <th>Action</th>
</tr>

<%
double grandTotal = 0;

List<CartModel> cartList =
(List<CartModel>)request.getAttribute("cartList");

if(cartList != null){

    for(CartModel cart : cartList){

        double total = cart.getPrice() * cart.getQuantity();
        grandTotal += total;
%>

<tr>
    <td><%= cart.getMenuId() %></td>
    <td><%= cart.getMenuName() %></td>
    <td>₹ <%= cart.getPrice() %></td>
    <td>

    <a href="cart?action=decrease&menuId=<%= cart.getMenuId() %>" class="qty-btn">-</a>

    <span class="qty-value">
        <%= cart.getQuantity() %>
    </span>

    <a href="cart?action=increase&menuId=<%= cart.getMenuId() %>" class="qty-btn">+</a>

</td>
    <td>₹ <%= total %></td>
    <td>
    <a href="cart?action=remove&menuId=<%= cart.getMenuId() %>"
       class="remove-btn">
        Remove
    </a>
</td>
</tr>

<%
    }
}
%>


</table>

<%
Integer restaurantId = (Integer) session.getAttribute("restaurantId");
if (restaurantId != null) {
%>
    <a href="menu?restaurantId=<%= restaurantId %>" class="btn">
        + Add More Items
    </a>
<%
} else {
%>
    <a href="restaurant" class="btn">
        + Add More Items
    </a>
<%
}
%>
<form action="checkout" method="get">
    <button type="submit" class="btn">
        Proceed to Checkout
    </button>
</form>
</div>

<footer>
    © 2026 FoodCraze | Shopping Cart
</footer>

</body>
</html>