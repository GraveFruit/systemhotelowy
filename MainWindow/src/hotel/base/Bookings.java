/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.base;

import base.service.BookingService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class Bookings {

    private  IntegerProperty id_booking;
    private  StringProperty client_booking;
    private  StringProperty employee_booking;
    private  StringProperty room_booking;
    private  StringProperty datap_booking;
    private  StringProperty datak_booking;
    private  StringProperty status_booking;
    private  StringProperty comment_booking;
    private  StringProperty edition_booking;

    public Bookings(String room) {
        this.room_booking = new SimpleStringProperty(room);
        
    }
    public Bookings(int id, String client, String employee, String room, String datap, String datak, String comment) {
        this(room);
        this.id_booking = new SimpleIntegerProperty(id);
        this.client_booking = new SimpleStringProperty(client);
        this.employee_booking = new SimpleStringProperty(employee);
        this.datap_booking = new SimpleStringProperty(datap);
        this.datak_booking = new SimpleStringProperty(datak);
        this.comment_booking = new SimpleStringProperty(comment);
    }
    
    public Bookings(int id, String client, String employee, String room, String datap, String datak, String status, String comment) {
        this(room);
        this.id_booking = new SimpleIntegerProperty(id);
        this.client_booking = new SimpleStringProperty(client);
        this.employee_booking = new SimpleStringProperty(employee);
        this.datap_booking = new SimpleStringProperty(datap);
        this.datak_booking = new SimpleStringProperty(datak);
        this.status_booking = new SimpleStringProperty(status);
        this.comment_booking = new SimpleStringProperty(comment);
    }
    

    public Integer getId_booking() {
        return id_booking.getValue();
    }

    public String getClient_booking() {
        return client_booking.getValue();
    }

    public String getEmployee_booking() {
        return employee_booking.getValue();
    }

    public String getRoom_booking() {
        return room_booking.getValue();
    }

    public String getDatap_booking() {
        return datap_booking.getValue();
    }

    public String getDatak_booking() {
        return datak_booking.getValue();
    }

    public String getStatus_booking() {
        return status_booking.getValue();
    }

    public String getComment_booking() {
        return comment_booking.getValue();
    }

}
