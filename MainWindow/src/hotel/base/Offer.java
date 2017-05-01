/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.base;

import java.sql.Date;
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
 * @author Wiola
 */
public class Offer {

    private IntegerProperty number_offer;
    private IntegerProperty id_booking;
    private StringProperty type_offer;
    private StringProperty floor_offer;
    private StringProperty standard_offer;
    private StringProperty datap_booking;
    private StringProperty datak_booking;
    private StringProperty comment_booking;
    private StringProperty status_booking;

    public Offer(int room, String floor, String type, String stan) {
        this.number_offer = new SimpleIntegerProperty(room);
        this.floor_offer = new SimpleStringProperty(floor);
        this.type_offer = new SimpleStringProperty(type);
        this.standard_offer = new SimpleStringProperty(stan);
    }

    public Offer(int id, int room, String stan, String type,String floor, String datap, String datak, String comment,String status) {
        this(room,floor,type,stan);
        this.id_booking = new SimpleIntegerProperty(id);
        this.datap_booking = new SimpleStringProperty(datap);
        this.datak_booking = new SimpleStringProperty(datak);
        this.comment_booking = new SimpleStringProperty(comment);
        this.status_booking = new SimpleStringProperty(status);

    }

    public int getId_booking() {
        return id_booking.getValue();
    }

    public String getDatap_booking() {
        return datap_booking.getValue();
    }

    public String getDatak_booking() {
        return datak_booking.getValue();
    }

    public String getComment_booking() {
        return comment_booking.getValue();
    }
    
     public String getStatus_booking() {
        return status_booking.getValue();
    }
    
    public int getNumber_offer() {
        return number_offer.getValue();
    }

    public String getType_offer() {
        return type_offer.getValue();
    }

    public String getFloor_offer() {
        return floor_offer.getValue();
    }

    public String getStandard_offer() {
        return standard_offer.getValue();
    }
}
