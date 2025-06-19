<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>Donations List</title>
  <style>
    body {
      background: linear-gradient(to right, #dfe9f3, #ffffff);
      font-family: Arial;
      padding: 20px;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      background: #fff;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    th, td {
      border: 1px solid #ccc;
      padding: 12px;
      text-align: center;
    }
    th {
      background-color: #4CAF50;
      color: white;
    }
    button {
      background: #f15a24;
      color: white;
      border: none;
      padding: 5px 10px;
      border-radius: 4px;
      cursor: pointer;
    }
    button:hover {
      background-color: #d94e1f;
    }
  </style>
</head>
<body>
  <h2>Donations List</h2>
  <table>
    <tr>
      <th>Donation ID</th>
      <th>Category</th>
      <th>Quantity</th>
      <th>Location</th>
      <th>Organisation</th>
      <th>Contact</th>
      <th>Status</th>
      <th>Date</th>
    </tr>

<%
  try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root");
    Statement stmt = conn.createStatement();

    String query = "SELECT donation_id, category, quantity, location, donate_to, contact, status, donation_date FROM donations";
    ResultSet rs = stmt.executeQuery(query);

    while (rs.next()) {
%>
    <tr>
      <td><%= rs.getInt("donation_id") %></td>
      <td><%= rs.getString("category") %></td>
      <td><%= rs.getString("quantity") %></td>
      <td><%= rs.getString("location") %></td>
      <td><%= rs.getString("donate_to") %></td>
      <td><%= rs.getString("contact") %></td>
      <td>
        <%= rs.getString("status") %>
        <% if ("Pending".equalsIgnoreCase(rs.getString("status"))) { %>
          <form action="CompleteDonationServlet" method="post" style="display:inline;">
            <input type="hidden" name="donation_id" value="<%= rs.getInt("donation_id") %>" />
            <button type="submit">Complete</button>
          </form>
        <% } %>
      </td>
      <td><%= rs.getDate("donation_date") %></td>
    </tr>
<%
    }
    rs.close();
    stmt.close();
    conn.close();
  } catch (Exception e) {
    out.println("<tr><td colspan='8'>Error: " + e.getMessage() + "</td></tr>");
  }
%>
  </table>
</body>
</html>
