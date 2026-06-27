<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.food.model.CartModel"%>
<%@ page import="com.food.model.UserModel"%>

<%
UserModel loggedUser =
(UserModel)session.getAttribute("loggedUser");

List<CartModel> cart =
(List<CartModel>)request.getAttribute("cart");

double grandTotal =
request.getAttribute("grandTotal") != null
?
(Double)request.getAttribute("grandTotal")
:0;

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
width:92%;
margin:auto;
padding:40px 0;
}

.page-title{
font-size:42px;
margin-bottom:35px;
}

.checkout-container{
display:grid;
grid-template-columns:2fr 1fr;
gap:35px;
}

.left-panel,
.right-panel{
background:rgba(255,255,255,.05);
border-radius:22px;
padding:25px;
border:1px solid rgba(255,255,255,.1);
backdrop-filter:blur(18px);
}

.section-title{
font-size:28px;
margin-bottom:20px;
}

.cart-item{
display:flex;
align-items:center;
gap:20px;
padding:18px;
border-bottom:1px solid rgba(255,255,255,.08);
}

.cart-item img{
width:90px;
height:90px;
object-fit:cover;
border-radius:15px;
}

.item-details{
flex:1;
}

.item-name{
font-size:22px;
font-weight:600;
}

.item-price{
margin-top:8px;
color:#00ff99;
font-size:20px;
}

.qty{
margin-top:6px;
color:#ccc;
}

.summary-box{
display:flex;
justify-content:space-between;
margin:18px 0;
font-size:18px;
}

.total{
font-size:28px;
font-weight:bold;
color:#00ff99;
border-top:1px solid rgba(255,255,255,.15);
padding-top:18px;
margin-top:18px;
}

.address-box{
margin-top:35px;
}

.address-box textarea{
width:100%;
height:130px;
background:#181818;
border:1px solid rgba(255,255,255,.1);
border-radius:15px;
padding:15px;
color:white;
font-size:16px;
resize:none;
outline:none;
}

.address-box textarea:focus{
border:1px solid #ff6b00;
}

.payment-option{
margin-top:18px;
display:flex;
flex-direction:column;
gap:15px;
}

.payment-card{
display:flex;
align-items:center;
gap:15px;
padding:18px;
background:#181818;
border-radius:14px;
cursor:pointer;
transition:.3s;
border:1px solid transparent;
}

.payment-card:hover{
border-color:#ff6b00;
transform:translateY(-3px);
}

.payment-card input{
transform:scale(1.2);
}

.payment-card i{
font-size:24px;
color:#ff6b00;
}

.place-btn{
margin-top:30px;
width:100%;
padding:18px;
border:none;
border-radius:35px;
background:linear-gradient(90deg,#ff6b00,#ff004c);
color:white;
font-size:20px;
font-weight:600;
cursor:pointer;
transition:.3s;
}

.place-btn:hover{
transform:scale(1.03);
box-shadow:0 0 25px rgba(255,107,0,.5);
}

footer{
margin-top:60px;
text-align:center;
padding:20px;
color:#999;
}

@media(max-width:900px){

.checkout-container{

grid-template-columns:1fr;

}

}
.container,
.checkout-container,
.left-panel,
.right-panel{
    position:relative;
    z-index:1;
}
form{
    position:relative;
    z-index:0;
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
}
else{
%>

<div class="profile-menu">

<img src="images/profile.png"
class="profile-pic">

<span><%=loggedUser.getName()%></span>

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

</div>

</header>

<div class="container">

<h2 class="page-title">

🧾 Checkout

</h2>

<form action="placeOrder" method="post">

<div class="checkout-container">

<div class="left-panel">

<h3 class="section-title">

Order Summary

</h3>

<%
if(cart!=null && !cart.isEmpty()){

for(CartModel item:cart){

double itemTotal=item.getPrice()*item.getQuantity();

%>

<div class="cart-item">

<img src="<%=item.getImageUrl()%>">

<div class="item-details">

<div class="item-name">

<%=item.getMenuName()%>

</div>

<div class="item-price">

₹ <%=item.getPrice()%>

</div>

<div class="qty">

Quantity :
<b><%=item.getQuantity()%></b>

</div>

</div>

<div class="item-price">

₹ <%=itemTotal%>

</div>

</div>

<%
}
}
else{
%>

<h2 style="color:#ff6b00;text-align:center;padding:50px;">

Your Cart is Empty

</h2>

<%
}
%>

<div class="address-box">

<h3 class="section-title">

Delivery Address

</h3>

<textarea
name="address"
placeholder="Enter your complete delivery address..."
required></textarea>

</div>

</div>

<div class="right-panel">

<h3 class="section-title">

Payment Method

</h3>

<div class="payment-option">

<label class="payment-card">

<input
type="radio"
name="paymentMethod"
value="Cash on Delivery"
checked>

<i class="fa-solid fa-money-bill-wave"></i>

<div>

<h4>Cash On Delivery</h4>

<p>Pay when your food arrives.</p>

</div>

</label>

<label class="payment-card">

<input
type="radio"
name="paymentMethod"
value="UPI">

<i class="fa-brands fa-google-pay"></i>

<div>

<h4>UPI Payment</h4>

<p>Google Pay, PhonePe, Paytm</p>

</div>

</label>

<label class="payment-card">

<input
type="radio"
name="paymentMethod"
value="Card">

<i class="fa-solid fa-credit-card"></i>

<div>

<h4>Credit / Debit Card</h4>

<p>Visa, Mastercard, RuPay</p>

</div>

</label>

<hr
style="margin:30px 0;
border-color:rgba(255,255,255,.1);">

<div class="summary-box">

<span>Items Total</span>

<span>

₹ <%=grandTotal%>

</span>

</div>

<div class="summary-box">

<span>Delivery Fee</span>

<span style="color:#00ff99;">

FREE

</span>

</div>

<div class="summary-box">

<span>GST</span>

<span>

Included

</span>

</div>

<div class="total">

Grand Total

<br><br>

₹ <%=grandTotal%>

</div>

<input
type="hidden"
name="grandTotal"
value="<%=grandTotal%>">

<button
type="submit"
class="place-btn">

<i class="fa-solid fa-bag-shopping"></i>

&nbsp;

Place Order

</button>

</div>

</div>

</form>

</div>

<footer>

© 2026 FoodCraze | Checkout

</footer>

</body>

</html>

