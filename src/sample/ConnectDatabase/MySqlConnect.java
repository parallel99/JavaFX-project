package sample.ConnectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnect {

    public Connection conn;

    public Connection getConnection(){
        String DATABASE = "chat";
        String USERNAME = "root";
        String PASSWORD = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+DATABASE,USERNAME,PASSWORD);
        } catch (Exception error){
            System.out.println(error.getMessage());
        }

        return conn;
    }
}
