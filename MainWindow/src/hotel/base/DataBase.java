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
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/nazwa_bazy?user=root&password=hasło";
    private static Connection connection = null;

    public DataBase(){
        createConnection();
    }

    private static Statement statement = null;

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
