package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection connection;

    public Connection getConnection(){
        return this.connection;
    }
    //=====================================

    private static DBConnection dbConnection;

    public static DBConnection getInstance(){
        if (dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    private DBConnection(){
        String url = "jdbc:mysql://localhost:3306/CS_M3";
        String username = "root";
        String password = "kieuanhxinh";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Ket noi thanh cong");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
