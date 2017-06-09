/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Rooms;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import mainwindow.FXMLDocumentController;
import mainwindow.ObjectManager;

/**
 *
 * @author Grzesiek
 */
public class LoginService {

    /**
     *permission storing variable
     */
    public String permissions;

    /**
     *employee_ID storing variable
     */
    public String employeeSessionId;

    /**
     *basic construct
     */
    public LoginService() {
    }

    /**
     * main login seqence method which uses sql query to connect to database
     * @param login - login variable
     * @param pass - password variable
     * @throws SQLException which does not work maybe ( to be or not to be)
     */
    public void getlogged(String login, String pass) throws SQLException {
        PreparedStatement prep = DataBase.getConnection().prepareStatement(
                "select p.pracownik_id, po.uprawnienia from pracownicy p, posady po "
                + "where p.posada_id = po.posada_id and p.pesel =? and p.haslo =? ");
        prep.setString(1, login);
        prep.setString(2, pass);
        ResultSet result = prep.executeQuery();
        while (result.next()) {
            this.employeeSessionId = result.getString("pracownik_id");
            this.permissions = result.getString("uprawnienia");
        }
    }

    /**
     *removes previous tasks(which data is other then today)
     */
    public void makeStartQuery() {
        try {
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "delete from zadania where status=0 and data!='" + ObjectManager.GetInstance().currentData + "'");
            prep.executeQuery();
            PreparedStatement prep2 = DataBase.getConnection().prepareStatement(
                    "update rezerwacje set status='-1' where data_p<'" + ObjectManager.GetInstance().currentData + "' and status=1");
            prep2.executeQuery();
            
        } catch (SQLException e) {
            System.out.println("błąd wczytywania początkowej kwerendy");
        }
    }

    /**
     * logout sequence 
     */
    public void logout() {
        ObjectManager.GetInstance().employeeservice.changeEmployeeStatus("0", employeeSessionId);
        this.permissions = null;
        this.employeeSessionId = null;
    }
}
