/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Rooms;
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
public class RoomService {

    Button edit_rooms;

    public ObservableList<Rooms> getData() {
        try {
            ObservableList<Rooms> rooms_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("Select * from pokoje");
            while (result.next()) {
                int id = result.getInt("numer");
                String floor = result.getString("pietro");
                String type = result.getString("typ");
                String stan = result.getString("standard");
                String status = result.getString("status");

                rooms_list.add(new Rooms(id, floor, type, stan, status));

            }

            return FXCollections.observableArrayList(rooms_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
