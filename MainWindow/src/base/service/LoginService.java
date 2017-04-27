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

/**
 *
 * @author Grzesiek
 */
public class LoginService {

    public String permissions;
    public String employeeSessionId;

    public LoginService() {
    }

    public void getlogged(String login, String pass) throws SQLException {
        PreparedStatement prep = DataBase.getConnection().prepareStatement(
                "select p.pracownik_id, po.uprawnienia "
                + "from pracownicy p, posady po "
                + "where p.posada_id = po.posada_id "
                + "and p.pesel =? "
                + "and p.haslo =? ");
        prep.setString(1, login);
        prep.setString(2, pass);
        ResultSet result = prep.executeQuery();
        while(result.next()){
        this.employeeSessionId = result.getString("pracownik_id");
        this.permissions = result.getString("uprawnienia");
        }     
    }

}
