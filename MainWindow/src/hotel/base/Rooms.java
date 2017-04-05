/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import mainwindow.FXMLDocumentController;

/**
 *
 * @author Grzesiek
 */
public class Rooms {

    
    private final IntegerProperty number_room;
    private final StringProperty type_room;
    private final StringProperty floor_room;
    private final StringProperty standard_room;
    private final StringProperty status_room;
    private final StringProperty edition_room;

    public Rooms(int room, String floor, String type, String stan, String status, String ed) {
        this.number_room = new SimpleIntegerProperty(room);
        this.floor_room = new SimpleStringProperty(floor);
        this.type_room = new SimpleStringProperty(type);
        this.standard_room = new SimpleStringProperty(stan);
        this.status_room = new SimpleStringProperty(status);
        this.edition_room = new SimpleStringProperty(ed);

    }

    public int getNumber_room() {
        return number_room.getValue();
    }

    public String getType_room() {
        return type_room.get();
    }

    public String getFloor_room() {
        return floor_room.getValue();
    }

    public String getStandard_room() {
        return standard_room.getValue();
    }

    public String getStatus_room() {
        return status_room.getValue();
    }
    
    public Button getEdition_room() {
        return FXMLDocumentController.makeEditRoomsButton();
    }

    public static ObservableList<Rooms> getData() {
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
                String ed = null;

                rooms_list.add(new Rooms(id, floor, type, stan, status, ed));

            }

            return FXCollections.observableArrayList(rooms_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
