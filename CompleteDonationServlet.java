package donation;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/CompleteDonationServlet")
public class CompleteDonationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int donationId = Integer.parseInt(request.getParameter("donation_id"));

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root");

            // Option 1: Delete the record
            String sql = "DELETE FROM donations WHERE donation_id = ?";
            // Option 2: If you prefer to just update the status to 'Completed':
            // String sql = "UPDATE donations SET status='Completed' WHERE donation_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, donationId);

            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("view_donations.jsp");  // Reload the page after action
    }
}
