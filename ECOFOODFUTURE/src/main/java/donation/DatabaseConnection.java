package donation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        	 String url="jdbc:oracle:thin:@localhost:1521:xe";
             String username="system";
             String password="root";
             if (connection == null || connection.isClosed()) {
  
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c= DriverManager.getConnection(url,username,password);       
           System.out.println("Dtabase Connected Successfully");   
        }
        return connection;
    }
}
