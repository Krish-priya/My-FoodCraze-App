<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.food.model.MenuModel"%>
<%@ page import="com.food.model.UserModel"%>

<%
UserModel loggedUser =
(UserModel)session.getAttribute("loggedUser");

List<MenuModel> menuList =
(List<MenuModel>)request.getAttribute("menuList");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<link rel="icon" type="image/png"
      href="<%=request.getContextPath()%>/images/logo.png">
<title>Eat Better with FoodCraze</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

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

/* HEADER */

header{

width:100%;

padding:20px 50px;

background:rgba(0,0,0,.9);

backdrop-filter:blur(20px);

border-bottom:1px solid rgba(255,255,255,.1);

position:sticky;

top:0;

z-index:999;

}

.navbar{

display:flex;

justify-content:space-between;

align-items:center;

}

.logo{

font-size:38px;

font-weight:bold;

text-decoration:none;

background:linear-gradient(90deg,#ff6b00,#ff004c);

-webkit-background-clip:text;

-webkit-text-fill-color:transparent;

}

.nav-links{

display:flex;

gap:25px;

align-items:center;

}

.nav-links a{

color:white;

text-decoration:none;

font-size:18px;

transition:.3s;

}

.nav-links a:hover{

color:#ff6b00;

}

/* TITLE */

.container{

width:90%;

margin:40px auto;

}

.title{

font-size:40px;

margin-bottom:40px;

}

/* GRID */

.grid{

display:grid;

grid-template-columns:repeat(auto-fit,minmax(320px,1fr));

gap:30px;

}

/* CARD */

.card{

background:rgba(255,255,255,.05);

border-radius:25px;

overflow:hidden;

border:1px solid rgba(255,255,255,.1);

backdrop-filter:blur(20px);

transition:.4s;

}

.card:hover{

transform:translateY(-10px);

box-shadow:0 0 30px rgba(255,107,0,.4);

}

.card img{

width:100%;

height:230px;

object-fit:cover;

}

.content{

padding:20px;

}

.menu-name{

font-size:26px;

font-weight:bold;

margin-bottom:10px;

}

.description{

color:#bbb;

line-height:1.6;

height:55px;

overflow:hidden;

}

.category{

margin-top:12px;

display:inline-block;

background:#333;

padding:6px 15px;

border-radius:20px;

}

.price{

margin-top:18px;

font-size:28px;

font-weight:bold;

color:#00ff99;

}

.btn{

display:inline-block;

margin-top:20px;

padding:12px 30px;

text-decoration:none;

color:white;

border-radius:30px;

background:linear-gradient(90deg,#ff6b00,#ff004c);

transition:.3s;

}

.btn:hover{

transform:scale(1.05);

box-shadow:0 0 20px rgba(255,107,0,.4);

}

.empty{

text-align:center;

font-size:30px;

padding:100px;

color:#ff6b00;

}

footer{

margin-top:70px;

background:#000;

padding:25px;

text-align:center;

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

<a href="restaurant#restaurants">Restaurants</a>

<a href="cart">🛒 Cart</a>

<a href="wishlist">❤️ Wishlist</a>

</div>

</div>

</header>

<div class="container">

<h2 class="title">
🔍 Search Results
</h2>

<%

if(menuList==null || menuList.isEmpty()){

%>

<div class="empty">

😔 No menu items found.

</div>

<%

}else{

%>

<div class="grid">

<%

for(MenuModel menu : menuList){

%>

<div class="card">

<img src="<%=menu.getImageUrl()%>" alt="Food Image">

<div class="content">

<div class="menu-name">

<%=menu.getMenuName()%>

</div>

<div class="description">

<%=menu.getDescription()%>

</div>

<div class="category">

<%=menu.getCategory()%>

</div>

<div class="price">

₹ <%=menu.getPrice()%>

</div>

<a href="cart?action=add&menuId=<%=menu.getMenuId()%>"
class="btn">

Add to Cart

</a>

</div>

</div>

<%

}

%>

</div>

<%

}

%>

</div>

<footer>

© 2026 FoodCraze | Search Results

</footer>

</body>

</html>