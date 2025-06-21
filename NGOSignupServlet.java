package donation;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/NGOSignupServlet")
public class NGOSignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String location = request.getParameter("location");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // ✅ Validate Gmail (lowercase only)
        if (!email.matches("[a-z0-9._%+-]+@gmail\\.com")) {
            request.setAttribute("error", "Only lowercase Gmail addresses are allowed.");
            request.getRequestDispatcher("ngo_login_signup.html").forward(request, response);
            return;
        }

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String pwd = "root";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, username, pwd);

            // ✅ Check if email already exists
            PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM ngos WHERE email = ?");
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                request.setAttribute("error", "Email already registered!");
                request.getRequestDispatcher("ngo_login_signup.html").forward(request, response);
                return;
            }

            // ✅ Insert new NGO
            PreparedStatement insertStmt = con.prepareStatement(
                "INSERT INTO ngos (name, location, email, password) VALUES (?, ?, ?, ?)");
            insertStmt.setString(1, name);
            insertStmt.setString(2, location);
            insertStmt.setString(3, email);
            insertStmt.setString(4, password);
            insertStmt.executeUpdate();

            // ✅ Store session and redirect
            HttpSession session = request.getSession();
            session.setAttribute("ngoName", name);
            session.setAttribute("ngoEmail", email);
            response.sendRedirect("ngo_dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error during signup.");
            request.getRequestDispatcher("ngo_login_signup.html").forward(request, response);
        }
    }
}
