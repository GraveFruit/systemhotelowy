/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.base;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Grzesiek
 */
public final class DataBase {
    private static DataBase base = null;
    private static final String base_name = "hotelmaster";
    private static final String base_password = "";
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/" + base_name + "?user=root&password=" + base_password;
    private static Connection connection = null;
     private static Statement statement = null;

    public DataBase(){
        createConnection();
    }

    public static DataBase getInstance(){
        if(base==null)
            base = new DataBase();
        return base;  
}
    
    public static String getDRIVER() {
        return DRIVER;
    }

    public static String getDB_URL() {
        return DB_URL;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }

   

    public static void createConnection() {
        try {
            Class.forName(DRIVER);
            System.out.println("Controller found");
        } catch (ClassNotFoundException ex) {
            System.err.println("Controller error");
        }
        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
            System.out.println("Conected");
        } catch (SQLException e) {
            System.err.println("Connection error");
        }

    }

    public static void closeConnection() {

        try {
            connection.close();
            statement.close();
            System.out.println("Disconnected");
        } catch (SQLException e) {
            System.err.println("Disconnection error");
        }

    }
    
  
    
    
}
