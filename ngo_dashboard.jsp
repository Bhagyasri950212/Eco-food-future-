<%@ page session="true" %>
<%
  String ngoName = (String) session.getAttribute("ngoName");
  if (ngoName == null) {
    response.sendRedirect("ngo_login_signup.html");
    return;
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>NGO Dashboard</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background: linear-gradient(to right, #74ebd5, #9face6);
      color: #333;
      padding: 40px;
      margin: 0;
    }

    .container {
      background-color: #fff;
      border-radius: 10px;
      padding: 30px;
      max-width: 500px;
      margin: 0 auto;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
      text-align: center;
    }

    h1 {
      color: #2e7d32; /* Dark green */
      margin-bottom: 10px;
    }

    p {
      margin-bottom: 30px;
      font-size: 16px;
    }

    a.button {
      background-color: #4caf50; /* Green */
      padding: 12px 24px;
      color: white;
      text-decoration: none;
      border-radius: 6px;
      font-weight: bold;
      display: inline-block;
      margin: 10px 10px 0;
      transition: background-color 0.3s ease;
    }

    a.button:hover {
      background-color: #388e3c; /* Darker green */
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>Welcome, <%= ngoName %></h1>
    <p>View and manage donations received.</p>
    <a href="view_donations.jsp" class="button">View Donations</a>
    <a href="LogoutServlet" class="button">Logout</a>
  </div>
</body>
</html>
