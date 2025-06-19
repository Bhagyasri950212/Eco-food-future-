package donation;

import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DonationServlet")
public class DonationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from the form
        String donationType = request.getParameter("donationType");
        String quantity = request.getParameter("quantity");
        String location = request.getParameter("location");
        String donateTo = request.getParameter("donateTo");
        String contact = request.getParameter("contact");

        // Hardcoded donor_id for testing (replace with session data later)
        int donorId = 101;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Insert donation record
            String sql = "INSERT INTO donations (donor_id, category, quantity, location, donate_to, contact, status, donation_date) " +
                         "VALUES (?, ?, ?, ?, ?, ?, 'Pending', SYSDATE)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, donorId);
            ps.setString(2, donationType);
            ps.setString(3, quantity);
            ps.setString(4, location);
            ps.setString(5, donateTo);
            ps.setString(6, contact);

            int rows = ps.executeUpdate();
            conn.close();

            if (rows > 0) {
                response.sendRedirect("thankyou.jsp");
            } else {
                out.println("<h1>Donation failed.</h1>");
            }

        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }
}
