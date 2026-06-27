<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@page import="com.food.model.UserModel"%>

<%
UserModel user=(UserModel)request.getAttribute("user");
%>

<!DOCTYPE html>
<html>

<head>

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
padding:20px 50px;
background:#000;
border-bottom:1px solid #333;
}

.logo{
font-size:38px;
font-weight:bold;
background:linear-gradient(90deg,#ff6b00,#ff004c);
-webkit-background-clip:text;
-webkit-text-fill-color:transparent;
text-decoration:none;
}

.container{

width:100%;
display:flex;
justify-content:center;
padding:60px;

}

.profile-card{

width:700px;
background:#1c1c1c;
border-radius:25px;
padding:40px;
box-shadow:0 0 30px rgba(255,107,0,.3);

}

.profile-top{

text-align:center;

}

.profile-top img{

width:140px;
height:140px;
border-radius:50%;
border:5px solid #ff6b00;
margin-bottom:20px;

}

.profile-top h2{

font-size:35px;

}

.profile-top p{

color:#aaa;
margin-top:8px;

}

hr{

margin:30px 0;
border-color:#333;

}

.info{

display:flex;
justify-content:space-between;
padding:18px 0;
border-bottom:1px solid #333;

}

.label{

font-size:20px;
color:#bbb;

}

.value{

font-size:20px;
font-weight:bold;

}

.buttons{

margin-top:40px;
display:flex;
justify-content:center;
gap:20px;

}

.btn{

padding:14px 30px;
border-radius:30px;
text-decoration:none;
font-size:18px;
font-weight:bold;
color:white;
background:linear-gradient(90deg,#ff6b00,#ff004c);
transition:.3s;

}

.btn:hover{

transform:scale(1.05);

}

</style>

</head>

<body>

<header>

<a href="restaurant" class="logo">

🛒 FoodCraze

</a>

</header>

<div class="container">

<div class="profile-card">

<div class="profile-top">

<img src="images/profile.png">

<h2><%=user.getName()%></h2>

<p>FoodCraze Customer</p>

</div>

<hr>

<div class="info">

<div class="label">

User ID

</div>

<div class="value">

#<%=user.getId()%>

</div>

</div>

<div class="info">

<div class="label">

Name

</div>

<div class="value">

<%=user.getName()%>

</div>

</div>

<div class="info">

<div class="label">

Email

</div>

<div class="value">

<%=user.getEmail()%>

</div>

</div>

<div class="info">

<div class="label">

Phone

</div>

<div class="value">

<%=user.getPhone()%>

</div>

</div>

<div class="info">

<div class="label">

Address

</div>

<div class="value">

<%=user.getAddress()==null?"Not Available":user.getAddress()%>

</div>

</div>

<div class="buttons">

<a href="orders" class="btn">

<i class="fa-solid fa-box"></i>

 My Orders

</a>

<a href="restaurant" class="btn">

<i class="fa-solid fa-house"></i>

 Home

</a>

</div>

</div>

</div>

</body>

</html>