<%@ page session="true" %>
<%
  String donorName = (String) session.getAttribute("donorName");
  if (donorName == null) {
    response.sendRedirect("donor_login_signup.html");
    return;
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Donor Dashboard</title>
  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
    }

    body {
      font-family: Arial, sans-serif;
      background: url("Donation/Eco.jpg") no-repeat center center/cover;
      height: 100vh;
      overflow: hidden;
      position: relative;
    }

    .overlay {
      position: absolute;
      top: 0; left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.6); /* black transparency */
      z-index: 0;
    }

    .content {
      position: relative;
      z-index: 1;
      height: 100vh;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      text-align: center;
      color: white;
      padding: 20px;
    }

    h1 {
      font-size: 3rem;
      margin-bottom: 20px;
      color: #f15a24;
    }

    p {
      font-size: 1.4rem;
      margin-bottom: 30px;
    }

    a.button {
      background-color: #f15a24;
      padding: 14px 32px;
      color: white;
      text-decoration: none;
      border-radius: 6px;
      font-size: 1.1rem;
      font-weight: bold;
      margin: 10px;
      display: inline-block;
      transition: background-color 0.3s ease;
    }

    a.button:hover {
      background-color: #d94e1f;
    }
  </style>
</head>
<body>

  <!-- 🔳 Black transparent overlay -->
  <div class="overlay"></div>

  <!-- 🌟 Centered content -->
  <div class="content">
    <h1>Welcome, <%= donorName %></h1>
    <p>Make a donation to help those in need.</p>
    <a href="donation_form.html" class="button">Donate Now</a>
    <a href="LogoutServlet" class="button">Logout</a>
  </div>

</body>
</html>