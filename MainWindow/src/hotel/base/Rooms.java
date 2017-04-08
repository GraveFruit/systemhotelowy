/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.base;

import base.service.RoomService;
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
import mainwindow.ObjectManager;

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
        return ObjectManager.GetInstance().roomservice.makeEditRoomsButton();
    }

    
}
