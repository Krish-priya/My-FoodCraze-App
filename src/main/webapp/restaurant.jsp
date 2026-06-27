<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.food.model.RestaurantModel,com.food.model.UserModel" %>
<%
UserModel loggedUser =
(UserModel)session.getAttribute("loggedUser");
%>
<%
List<RestaurantModel> allRestaurant =
(List<RestaurantModel>)request.getAttribute("allRestaurant");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
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

html{
    scroll-behavior:smooth;
}

body{
    background:#0d0d0d;
    color:white;
    overflow-x:hidden;
}

/* HEADER */


.card:hover .restaurant-name{
    color:#ff7b00;
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


.hero{

    width:90%;

    max-width:1600px;

    height:500px;

    margin:auto;

    position:relative;

    overflow:hidden;

    border-radius:25px;
    position:relative;
    z-index:1;

}

.slide{

    width:100%;

    height:100%;

    object-fit:fill;

    display:none;

}

.slide:first-child{

    display:block;

}
.slider{
    width:100%;
    height:100%;
    position:relative;
    overflow:hidden;
    z-index:1;
}
.hero-content{

    position:absolute;

    top:50%;

    left:50%;

    transform:translate(-50%,-50%);

    z-index:1;

    text-align:center;

    width:100%;

}


.top-search{

    display:flex;
    flex-direction:column;
    align-items:center;
    justify-content:center;

    padding:45px 0 35px;
}

.top-search h1{

    font-size:58px;
    font-weight:700;
    text-align:center;
    margin-bottom:12px;
}

.top-search p{

    font-size:22px;
    color:#bbb;
    margin-bottom:35px;
}

.search-box{

    width:720px;
    max-width:90%;
    position:relative;
}

.search-form{

    display:flex;
    align-items:center;

    background:linear-gradient(135deg,#1b1b1b,#2a2a2a);

    border:2px solid #ff6b00;

    border-radius:60px;

    overflow:hidden;

    box-shadow:0 8px 30px rgba(255,107,0,.35);

    transition:.3s;
}
.search-form:focus-within{

    border-color:#ff8c1a;

    box-shadow:
        0 0 12px rgba(255,140,26,.8),
        0 0 35px rgba(255,107,0,.35);
}
.search-form input{

    flex:1;

    border:none;

    outline:none;

    padding:20px 28px;

    font-size:18px;

    color:white;

    background:transparent;
}

.search-form input::placeholder{

    color:#bdbdbd;
}
.search-btn{

    border:none;

    padding:20px 35px;

    background:linear-gradient(90deg,#ff6b00,#ff004c);

    color:white;

    font-size:18px;

    font-weight:600;

    cursor:pointer;

    transition:.3s;
}

.search-btn:hover{

    opacity:.9;
}

.search-btn:active{
    transform:scale(.97);
}

.prev,
.next{

position:absolute;

top:50%;

transform:translateY(-50%);

width:55px;

height:55px;

border:none;

font-size:30px;

cursor:pointer;

background:rgba(0,0,0,.45);

color:white;

border-radius:50%;

z-index:20;

transition:.3s;

}

.prev:hover,
.next:hover{

background:#ff6b00;

}

.prev{

left:20px;

}

.next{

right:20px;

}
.dots{

    position:absolute;

    bottom:20px;

    left:50%;

    transform:translateX(-50%);

    display:flex;

    gap:12px;

    z-index:30;

}

.dot{

    width:14px;

    height:14px;

    border-radius:50%;

    background:white;

    opacity:.45;

    cursor:pointer;

    transition:.3s;

}

.dot.active{

    background:#ff6b00;

    opacity:1;

    transform:scale(1.2);

}
.container{
    width:90%;
    margin:auto;
    padding-top:80px;
}

.section-title{
    font-size:42px;
    margin-bottom:40px;
    color:white;
}

/* GRID */

.restaurant-grid{
    display:grid;
    grid-template-columns:
    repeat(auto-fit,minmax(320px,1fr));
    gap:30px;
}

/* CARD */

.card{
    background:rgba(255,255,255,0.05);
    border-radius:25px;
    overflow:hidden;
    backdrop-filter:blur(20px);

    border:1px solid rgba(255,255,255,0.1);

    transition:0.4s;
}

.card:hover{
    transform:translateY(-15px);
    box-shadow:
    0 0 30px rgba(255,107,0,0.4);
}

.card img{
    width:100%;
    height:230px;
    object-fit:cover;
    display: block;
}

.card-content{
    padding:20px;
}

.restaurant-name{
    font-size:26px;
    font-weight:bold;
    color:white;
}

.cuisine{
    margin-top:8px;
    color:#bbb;
}

.details{
    margin-top:15px;
    line-height:2;
    color:#ddd;
}

.rating{
    background:#16a34a;
    padding:6px 12px;
    border-radius:20px;
    color:white;
}

.active{
    background:#22c55e;
    color:white;
    padding:5px 14px;
    border-radius:20px;
    font-weight:600;
}

.closed{
    background:#ef4444;
    color:white;
    padding:5px 14px;
    border-radius:20px;
    font-weight:600;
}

.btn{
    display:inline-block;
    margin-top:15px;
    padding:12px 25px;
    text-decoration:none;
    color:white;

    background:
    linear-gradient(
        90deg,
        #ff6b00,
        #ff004c
    );

    border-radius:30px;
    transition:0.4s;
}

.btn:hover{
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
#suggestions{

    position:absolute;

    top:72px;

    left:0;

    width:100%;

    background:white;

    border-radius:18px;

    overflow:hidden;

    box-shadow:0 12px 35px rgba(0,0,0,.2);

    display:none;

    z-index:999;
}
.suggestion{

    padding:16px 25px;

    cursor:pointer;

    font-size:17px;

    color:#444;

    transition:.25s;

    border-bottom:1px solid #eee;
}

.suggestion:hover{

    background:#fff4ed;

    color:#ff6b00;

    padding-left:35px;
}
/* FOOTER */

footer{
    margin-top:80px;
    background:#000;
    text-align:center;
    padding:30px;
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

            <a href="#">Home</a>

           <a href="#restaurants">Restaurants</a>

            <a href="cart">🛒 Cart</a>

            <a href="wishlist">❤️ Wishlist</a>

            <% if(loggedUser==null){ %>

                <a href="login.jsp">Login</a>

            <% }else{ %>

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

            <% } %>

        </div>

    </div>

</header>

<div class="top-search">

    <h1>Discover the Best Food Near You</h1>

    <p>Order from top-rated restaurants instantly</p>

    <div class="search-box">

    <form action="searchMenu" method="get" class="search-form">
		<span class="icon">🔍</span>
    <input
        type="text"
        name="search"
        id="search"
        placeholder="Search restaurants..."
        >

    <button type="submit" class="search-btn">
        Search
    </button>

</form>
    <div id="suggestions"></div>
</div>

</div>

<div class="hero">

    <div class="slider">

        <a href="searchMenu?search=pizza">
    		<img class="slide active" src="images/banner1.jpg">
		</a>

		<a href="searchMenu?search=burger">
    		<img class="slide" src="images/banner2.jpg">
		</a>

		<a href="searchMenu?search=noodles">
    		<img class="slide" src="images/banner3.jpg">
		</a>

        <img class="slide"
             src="images/banner4.jpg">

        <button class="prev">&#10094;</button>
        <button class="next">&#10095;</button>
        <div class="dots">

    <span class="dot active"></span>

    <span class="dot"></span>

    <span class="dot"></span>

    <span class="dot"></span>

</div>

    </div>

    
</div>

<div class="container" id="restaurants">

<h2 class="section-title">
✨ Featured Restaurants
</h2>

<div class="restaurant-grid">
<%
if (allRestaurant != null) {
    for (RestaurantModel restaurant : allRestaurant) {
%>
<!-- Card 1 -->
<div class="card">
<img src="<%= request.getContextPath() %>/<%= restaurant.getImageUrl() %>"
     alt="Restaurant Image">
<div class="card-content">
<div class="restaurant-name"><%=restaurant.getRestaurantName() %></div>
<div class="cuisine"><%=restaurant.getCuisineType() %></div>

<div class="details">
⭐ <span class="rating"><%=restaurant.getRating() %></span><br>
⏱ <%=restaurant.getDeliveryTime() %><br>
📍 <%=restaurant.getAddress() %><br>
Status:
<span class="<%= restaurant.isActive() ? "active" : "closed" %>">
    <%= restaurant.isActive() ? "Open" : "Closed" %>
</span>
</div>

<a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>" class="btn">
    View Menu
</a>
</div>
</div>
<%
    }
} else {
%>

<h3>No restaurants found.</h3>

<%
}
%>


</div>

</div>

<footer>
© 2026 FoodCraze | Food Delivery Application
</footer>
<script>
const slides = document.querySelectorAll(".slide");
const dots = document.querySelectorAll(".dot");

let index = 0;

function showSlide(i){

    slides.forEach(function(slide){
        slide.style.display="none";
    });

    dots.forEach(function(dot){
        dot.classList.remove("active");
    });

    slides[i].style.display="block";
    dots[i].classList.add("active");
}

showSlide(index);

// Next Button
document.querySelector(".next").onclick=function(){

    index++;

    if(index>=slides.length){
        index=0;
    }

    showSlide(index);
};

// Previous Button
document.querySelector(".prev").onclick=function(){

    index--;

    if(index<0){
        index=slides.length-1;
    }

    showSlide(index);
};

// Dot Click
dots.forEach(function(dot,i){

    dot.onclick=function(){

        index=i;

        showSlide(index);

    };

});

// Auto Slide
setInterval(function(){

    index++;

    if(index>=slides.length){
        index=0;
    }

    showSlide(index);

},3000);
const search =
	document.getElementById("search");

	const suggestions =
	document.getElementById("suggestions");

	search.addEventListener("keyup",function(){

	let keyword=this.value;

	if(keyword.length==0){

	suggestions.style.display="none";

	return;

	}

	fetch("searchRestaurant?keyword="+keyword)

	.then(response=>response.json())

	.then(data=>{

	suggestions.innerHTML="";

	data.forEach(function(name){

	let div=document.createElement("div");

	div.className="suggestion";

	div.innerHTML=name;

	div.onclick = function(){

	    search.value = name;

	    suggestions.innerHTML = "";

	    suggestions.style.display = "none";

	}

	suggestions.appendChild(div);

	});

	if(data.length>0){

	suggestions.style.display="block";

	}else{

	suggestions.style.display="none";

	}

	});

	});
	document.addEventListener("click",function(e){

		if(!document.querySelector(".search-box").contains(e.target)){

		suggestions.style.display="none";

		}

		});
</script>
</body>
</html>