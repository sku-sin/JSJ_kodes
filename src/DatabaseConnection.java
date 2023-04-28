import java.sql.*;

public class DatabaseConnection {

    private Connection connection;
    
    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/notetakingdb";
            String username = "root";
            String password = "password";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
