package its340_andreas_anamv;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    
    // lets student 2 keep calling DBConnection.getConnection() without changes.
    public static Connection getConnection() {
        // Just calls the second method below with the default credentials
        return getConnection("root", "Password"); 
    }

    // This lets student 1 pass in "root" and "" (empty) or any other credentials
    public static Connection getConnection(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/medical";
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}