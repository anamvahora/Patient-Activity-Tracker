package its340_andreas_anamv;
import java.sql.*;

public class dbUtils {
    
    public static Connection ConnectToMySqlDB(String Username, String Password)
    {
        String URL = "jdbc:mysql://localhost:3306/medical";
        Connection conn = null;
        
        try
        {
            conn = DriverManager.getConnection(URL, Username, Password);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
}
