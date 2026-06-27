<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="com.food.model.OrderItemModel"%>
<%@page import="com.food.model.OrderTableModel"%>
<%@page import="com.food.model.UserModel"%>

<%
UserModel loggedUser =
(UserModel)session.getAttribute("loggedUser");

OrderTableModel order =
(OrderTableModel)request.getAttribute("order");

List<OrderItemModel> items =
(List<OrderItemModel>)request.getAttribute("orderItems");
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

.title{
font-size:42px;
margin-bottom:30px;
}

.card{

background:#1d1d1d;

padding:30px;

border-radius:20px;

margin-bottom:30px;

border:1px solid rgba(255,255,255,.08);

}

.row{

display:flex;

justify-content:space-between;

margin:15px 0;

font-size:18px;

}

.label{

color:#bbb;

}

.value{

font-weight:bold;

}

table{

width:100%;

border-collapse:collapse;

margin-top:20px;

}

th{

background:#222;

padding:18px;

color:#ffb366;

}

td{

padding:18px;

text-align:center;

border-bottom:1px solid rgba(255,255,255,.08);

}

tr:hover{

background:#1f1f1f;

}

.total{

margin-top:30px;

text-align:right;

font-size:28px;

font-weight:bold;

color:#00ff99;

}

.back-btn{

display:inline-block;

margin-top:30px;

padding:14px 28px;

background:linear-gradient(90deg,#ff6b00,#ff004c);

color:white;

text-decoration:none;

border-radius:30px;

font-weight:bold;

transition:.3s;

}

.back-btn:hover{

transform:scale(1.05);

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

</header>

<div class="container">

<h2 class="title">

🧾 Order Details

</h2>

<div class="card">

<div class="row">

<span class="label">

Order ID

</span>

<span class="value">

#<%=order.getOrderId()%>

</span>

</div>

<div class="row">

<span class="label">

Restaurant ID

</span>

<span class="value">

<%=order.getRestaurantId()%>

</span>

</div>

<div class="row">

<span class="label">

Order Date

</span>

<span class="value">

<%=order.getOrderDate()%>

</span>

</div>

<div class="row">

<span class="label">

Payment Method

</span>

<span class="value">

<%=order.getPaymentMethod()%>

</span>

</div>

<div class="row">

<span class="label">

Delivery Address

</span>

<span class="value">

<%=order.getAddress()==null?"Not Available":order.getAddress()%>

</span>

</div>

<div class="row">

<span class="label">

Status

</span>

<span class="value">

<span class="<%=order.getStatus().toLowerCase()%>">

<%=order.getStatus()%>

</span>

</span>

</div>

</div>

<div class="card">

<h2>

Ordered Items

</h2>

<table>

<tr>

<th>Image</th>
<th>Food</th>
<th>Price</th>
<th>Quantity</th>
<th>Total</th>

</tr>

<%

double grandTotal=0;

if(items!=null){

for(OrderItemModel item:items){

grandTotal+=item.getItemTotal();

%>

<tr>

<td>

<img src="<%=item.getImageUrl()%>"
style="width:80px;
height:80px;
border-radius:12px;
object-fit:cover;">

</td>

<td>

<b><%=item.getMenuName()%></b>

</td>

<td>

₹ <%=item.getPrice()%>

</td>

<td>

<%=item.getQuantity()%>

</td>

<td>

₹ <%=item.getItemTotal()%>

</td>

</tr>

<%

}

}

%>

</table>

<div class="total">

Grand Total :

₹ <%=grandTotal%>

</div>

</div>

<a href="orders"

class="back-btn">

<i class="fa-solid fa-arrow-left"></i>

Back to Orders

</a>

</div>

<footer>

© 2026 FoodCraze

</footer>

</body>

</html>