import java.sql.*;

 class checklistDB {
    static String title = "";
    static String description="";

    public checklistDB() {}

    public checklistDB(String title, String description) {
        this.title = title;
        this.description = description;
    }
    checklistDB(String title) {
        this.title = title;
    }

    Connection addConnection() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/projects";
        String uname = "root";
        String password = "";

        //Loading the driver
        Class.forName(driver);

        //Establishing the connection
        Connection con = DriverManager.getConnection(url, uname, password);

        return con;
    }

    void addingATask(Connection con) throws Exception {
        String query = "insert into checklist values(?,?,?);";
        String idQuery = "SELECT * FROM checklist ORDER BY id DESC LIMIT 1;";

        //Creating a statement
        PreparedStatement addValues = con.prepareStatement(query);
        PreparedStatement fetchId = con.prepareStatement(idQuery);

        //Execute the statements
        ResultSet rs = fetchId.executeQuery(idQuery);
        int id = 0;

        if(rs.next()) {
            id = rs.getInt("id");
        }
        addValues.setInt(1, ++id);
        addValues.setString(2, title);
        addValues.setString(3, description);

        System.out.println(addValues.executeUpdate() + " rows affected.");

        addValues.close();
        fetchId.close();
        con.close();
    }
    public static void main(String[] args) throws Exception {
        checklistDB database = new checklistDB(title, description);
        Connection paramCon = database.addConnection();
        database.addingATask(paramCon);
    }
}