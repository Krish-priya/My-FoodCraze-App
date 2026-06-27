<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.food.model.WishlistModel"%>
<%@ page import="com.food.model.UserModel" %>

<%
UserModel loggedUser =
(UserModel) session.getAttribute("loggedUser");
%>
<%
List<WishlistModel> wishlist =
(List<WishlistModel>)request.getAttribute("wishlist");
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
background:#111;
font-family:Poppins;
color:white;
}

.container{
width:90%;
margin:auto;
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


.grid{
display:grid;
grid-template-columns:repeat(auto-fit,minmax(300px,1fr));
gap:25px;
}

.card{
background:#222;
border-radius:20px;
overflow:hidden;
padding:20px;
}

.card img{
width:100%;
height:220px;
object-fit:cover;
border-radius:15px;
}

.name{
font-size:24px;
margin-top:15px;
font-weight:bold;
}

.price{
color:#00ff99;
margin-top:10px;
font-size:20px;
}

.remove{
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
}\
.remove:hover{
transform:scale(1.05);
    box-shadow:0 0 20px rgba(255,107,0,0.5);
}
.cart-btn{
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

.cart-btn:hover{
    transform:scale(1.05);
    box-shadow:0 0 20px rgba(255,107,0,0.5);
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
</header>

<div class="container">

<h1>❤️ My Wishlist</h1>

<div class="grid">

<%
for(WishlistModel item:wishlist){
%>

<div class="card">

<img src="<%=item.getImageUrl()%>">

<div class="name">

<%=item.getMenuName()%>

</div>

<div class="price">

₹ <%=item.getPrice()%>

</div>
<form action="cart" method="post">

    <input type="hidden"
           name="menuId"
           value="<%=item.getMenuId()%>">

    <button type="submit" class="cart-btn">
        Add To Cart
    </button>

</form>
<a class="remove"
href="wishlist?action=remove&wishlistId=<%=item.getWishlistId()%>">

Remove

</a>

</div>

<%
}
%>

</div>

</div>

</body>

</html>