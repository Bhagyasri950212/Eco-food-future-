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

@WebServlet("/DonorSignupServlet")

public class DonorSignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String url="jdbc:oracle:thin:@localhost:1521:xe";
        String username="system";
        String pwd="root";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =DriverManager.getConnection(url,username,pwd);
            PreparedStatement checkStmt = con.prepareStatement("select * from donors Where email=?");
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                request.setAttribute("error", "Email already registered!");
                request.getRequestDispatcher("donor_login_signup.html").forward(request, response);
                return;
            }

            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO donors (name, email, password) VALUES (?, ?, ?)");
            insertStmt.setString(1, name);
            insertStmt.setString(2, email);
            insertStmt.setString(3, password);
            insertStmt.executeUpdate();

            HttpSession session = request.getSession();
            session.setAttribute("donorName", name);
            session.setAttribute("donorEmail", email);
            response.sendRedirect("donor_dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error during signup.");
            request.getRequestDispatcher("donor_login_signup.html").forward(request, response);
        }
    }
}
