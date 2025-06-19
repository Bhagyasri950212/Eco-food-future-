package donation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/DonorLoginServlet")


public class DonorLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String url="jdbc:oracle:thin:@localhost:1521:xe";
        String username="system";
        String pwd="root";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =DriverManager.getConnection(url,username,pwd);
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM donors WHERE email = ? AND password = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                HttpSession session = request.getSession();
                session.setAttribute("donorName", name);
                session.setAttribute("donorEmail", email);
                response.sendRedirect("donor_dashboard.jsp");
            } else {
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("donor_login_signup.html").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error during login.");
            request.getRequestDispatcher("donor_login_signup.html").forward(request, response);
        }
    }
}
