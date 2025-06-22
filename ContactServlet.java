package donation;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // ✅ Validate Gmail format (lowercase only)
        if (!email.matches("^[a-z0-9._%+-]+@gmail\\.com$")) {
            response.setContentType("text/html");
            response.getWriter().println("<h3 style='color:red;text-align:center;'>Only lowercase Gmail addresses are allowed!</h3>");
            return;
        }

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "root";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO contact_messages (name, email, message) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, message);
            ps.executeUpdate();

            conn.close();

            response.sendRedirect("contact_success.jsp"); // ✅ Redirect to thank you page

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }
}
