/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grzesiek
 */
public final class DataBase {
    private static DataBase base = null;
    private static String base_name = "hotelmaster";
    private static String base_password = "inus33";
    private static String DRIVER = "org.mariadb.jdbc.Driver";
    private static String baselink = "jdbc:mariadb://localhost:3306/";
    private static String DB_URL = baselink + base_name + "?user=root&password=" + base_password;
    private static Connection connection = null;
     private static Statement statement = null;

    public DataBase() throws FileNotFoundException, IOException{
        String[] params = new String[3];

        int i = 0;

        BufferedReader br = null;
        FileReader fr = null;

        fr = new FileReader("config.txt");
        br = new BufferedReader(fr);
        String sCurrentLine;
        br = new BufferedReader(new FileReader("config.txt"));
        while ((sCurrentLine = br.readLine()) != null) {
            System.out.println(sCurrentLine.split("=")[1]);
            params[i] = sCurrentLine;
            i++;
        }
        if (br != null) {
            br.close();
        }
        if (fr != null) {
            fr.close();
        }

        this.base_name = params[0];
        this.base_password = params[1];
        this.baselink = params[2];
        createConnection();
    }

    public static DataBase getInstance(){
        if(base==null)
            try {
                base = new DataBase();
        } catch (IOException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
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

