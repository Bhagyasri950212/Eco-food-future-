<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Contact Success</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(to right, #74ebd5, #ACB6E5);
      color: #333;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }

    .box {
      background-color: #fff;
      padding: 40px;
      border-radius: 15px;
      box-shadow: 0 8px 25px rgba(0,0,0,0.2);
      text-align: center;
    }

    h1 {
      color: #f15a24;
      margin-bottom: 10px;
    }

    p {
      font-size: 16px;
      margin-bottom: 20px;
    }

    a {
      display: inline-block;
      text-decoration: none;
      background: #f15a24;
      color: white;
      padding: 10px 20px;
      border-radius: 5px;
      font-weight: bold;
      transition: background 0.3s ease;
    }

    a:hover {
      background: #d94e1f;
    }
  </style>
</head>
<body>
  <div class="box">
    <h1>Thank You!</h1>
    <p>Your message has been successfully sent.<br>We will get back to you shortly.</p>
    <a href="index.html">Back to Home</a>
  </div>
</body>
</html>
