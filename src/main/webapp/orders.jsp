<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="com.food.model.OrderTableModel"%>
<%@page import="com.food.model.UserModel"%>

<%
UserModel loggedUser=(UserModel)session.getAttribute("loggedUser");

List<OrderTableModel> orders=
(List<OrderTableModel>)request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<meta charset="UTF-8">

<link rel="icon" type="image/png"
      href="<%=request.getContextPath()%>/images/logo.png">
<title>Eat Better with FoodCraze</title>

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:Poppins,sans-serif;
}

body{
background:#111;
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
font-size:38px;
font-weight:bold;
text-decoration:none;
background:linear-gradient(90deg,#ff6b00,#ff004c);
-webkit-background-clip:text;
-webkit-text-fill-color:transparent;
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
.container{
width:90%;
margin:auto;
padding:40px 0;
}

.page-title{
font-size:40px;
margin-bottom:30px;
}

.order-card{

background:#1b1b1b;

border-radius:20px;

padding:25px;

margin-bottom:25px;

border:1px solid rgba(255,255,255,.08);

transition:.3s;

}

.order-card:hover{

transform:translateY(-5px);

box-shadow:0 0 20px rgba(255,107,0,.35);

}

.row{

display:flex;

justify-content:space-between;

margin:12px 0;

font-size:18px;

}

.label{

color:#bbb;

}

.value{

font-weight:bold;

}

.pending{

color:orange;

}

.delivered{

color:#00ff99;

}

.cancelled{

color:red;

}

.btn{

display:inline-block;

margin-top:20px;

padding:12px 25px;

border-radius:30px;

background:linear-gradient(90deg,#ff6b00,#ff004c);

color:white;

text-decoration:none;

font-weight:bold;

transition:.3s;

}

.btn:hover{

transform:scale(1.05);

}
.delete-icon{

display:inline-flex;

align-items:center;

justify-content:center;

width:45px;

height:45px;

margin-left:15px;

border-radius:50%;

background:#ff3b30;

color:white;

text-decoration:none;

font-size:18px;

transition:.3s;

}

.delete-icon:hover{

background:#c1121f;

transform:scale(1.1);

box-shadow:0 0 18px rgba(255,59,48,.5);

}

.empty{

text-align:center;

font-size:30px;

padding:80px;

color:#999;

}
.success-box{

width:90%;
margin:30px auto;

padding:25px;

background:#e8fff0;

border-left:8px solid #28a745;

border-radius:15px;

text-align:center;

animation:fade .6s ease;

}

.success-box h2{

color:#28a745;

font-size:34px;

margin-bottom:10px;

}

.success-box p{

font-size:20px;

color:#333;

margin-bottom:10px;

}

.success-box small{

font-size:18px;

color:#666;

}

@keyframes fade{

from{

opacity:0;

transform:translateY(-20px);

}

to{

opacity:1;

transform:translateY(0);

}

}
footer{

text-align:center;

padding:25px;

margin-top:40px;

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

<a href="cart">Cart</a>

<a href="wishlist">❤️ Wishlist</a>

<%
if(loggedUser!=null){
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
<%
String msg = (String)request.getAttribute("successMessage");

if(msg != null){
%>

<div class="success-box">

    <h2>✅ Thank You!</h2>

    <p><%=msg%></p>

    <small>
        Estimated Delivery Time :
        <b>25 - 35 mins</b>
    </small>

</div>

<%
}
%>

<div class="container">

<h2 class="page-title">

📦 My Orders

</h2>


<%

if(orders!=null && !orders.isEmpty()){

for(OrderTableModel order:orders){

%>
<div class="order-card">

    <!-- Top Row -->
    <div style="display:flex;justify-content:space-between;align-items:center;">

        <h2 style="font-size:28px;color:#fff;">
            🍽 Order #<%=order.getOrderId()%>
        </h2>

        <span style="font-size:18px;
                     color:#00ff99;
                     font-weight:bold;">
            <%=order.getStatus()%> ✅
        </span>

    </div>

    <hr style="margin:18px 0;border-color:#333;">

    <div style="font-size:20px;line-height:40px;">

        <p>
            <b>Restaurant :</b>
            <%= order.getRestaurant().getRestaurantName() %>
        </p>

        <p>
            <b>Date :</b>
            <%=order.getOrderDate()%>
        </p>

        <p>
            <b>Total :</b>
            ₹ <%=order.getTotalAmount()%>
        </p>

        <p>
            <b>Payment :</b>
            <%=order.getPaymentMethod()%>
        </p>

        <p>
            <b>Status :</b>

            <span style="color:#ff9900;font-weight:bold;">
                <%=order.getStatus()%>
            </span>
        </p>

    </div>

    <div style="text-align:right;margin-top:25px;">

    <a href="orderDetails?orderId=<%=order.getOrderId()%>"
       class="btn">
        View Details
    </a>

    <a href="orders?action=delete&orderId=<%=order.getOrderId()%>"
       class="delete-icon"
       onclick="return confirm('Delete this order?')">

        <i class="fa-solid fa-trash"></i>

    </a>

</div>

</div>
<%
}
}
else{
%>

<div class="empty">

    No Orders Found

</div>

<%
}
%>


</div>

<footer>

© 2026 FoodCraze

</footer>

</body>

</html>