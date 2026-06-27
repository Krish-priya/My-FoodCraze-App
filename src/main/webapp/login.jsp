<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
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
    display:flex;
    justify-content:center;
    align-items:center;
    height:100vh;
}

.container{
    width:400px;
    background:rgba(255,255,255,0.05);
    border:1px solid rgba(255,255,255,0.1);
    border-radius:20px;
    padding:35px;
    backdrop-filter:blur(15px);
}

h2{
    text-align:center;
    color:white;
    margin-bottom:25px;
}

input{
    width:100%;
    padding:14px;
    margin:12px 0;
    border:none;
    border-radius:8px;
    background:#1f1f1f;
    color:white;
    font-size:15px;
}

input:focus{
    outline:none;
    box-shadow:0 0 10px rgba(255,107,0,.5);
}

button{
    width:100%;
    padding:14px;
    margin-top:20px;
    border:none;
    border-radius:30px;
    color:white;
    font-size:16px;
    cursor:pointer;
    background:linear-gradient(90deg,#ff6b00,#ff004c);
}

button:hover{
    transform:scale(1.02);
}

.bottom{
    text-align:center;
    margin-top:20px;
    color:#ccc;
}

.bottom a{
    color:#ff6b00;
    text-decoration:none;
    font-weight:bold;
}

.bottom a:hover{
    color:#ff9900;
}
</style>

</head>
<body>

<div class="container">

<h2>Login to FoodCraze</h2>

<form action="login" method="post">

    <input type="email" name="email" placeholder="Email Address" required>

    <input type="password" name="password" placeholder="Password" required>

    <button type="submit">Login</button>

</form>

<div class="bottom">
    New User?
    <a href="register.jsp">Register</a>
</div>

</div>

</body>
</html>