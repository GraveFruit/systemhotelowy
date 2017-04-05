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
public class Bookings {


    private final IntegerProperty id_booking;
    private final StringProperty client_booking;
    private final StringProperty employee_booking;
    private final StringProperty room_booking;
    private final IntegerProperty datap_booking;
    private final IntegerProperty datak_booking;
    private final StringProperty status_booking;
    private final StringProperty comment_booking;
    private final StringProperty edition_booking;

    public Bookings(int id_booking, String client_booking, String employee_booking, String room_booking, int datap_booking, int datak_booking, String status_booking, String comment_booking, String edit_booking) {
        this.id_booking = new SimpleIntegerProperty(id_booking);
        this.client_booking = new SimpleStringProperty(client_booking);
        this.employee_booking = new SimpleStringProperty(employee_booking);
        this.room_booking = new SimpleStringProperty(room_booking);
        this.datap_booking = new SimpleIntegerProperty(datap_booking);
        this.datak_booking = new SimpleIntegerProperty(datak_booking);
        this.status_booking = new SimpleStringProperty(status_booking);
        this.comment_booking = new SimpleStringProperty(comment_booking);
        this.edition_booking = new SimpleStringProperty(edit_booking);
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

    public int getDatap_booking() {
        return datap_booking.getValue();
    }

    public int getDatak_booking() {
        return datak_booking.getValue();
    }

    public String getStatus_booking() {
        return status_booking.getValue();
    }

    public String getComment_booking() {
        return comment_booking.getValue();
    }
    public Button getEdit_booking() {
        return FXMLDocumentController.makeEditRoomsButton();
    }

    public static ObservableList<Bookings> getData() {
        try {
            ObservableList<Bookings> bookings_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select r.rezerwacja_id, k.pesel, p.nazwisko, po.numer, r.data_p,r.data_k,r.status,r.komentarz from rezerwacje r, pracownicy p, pokoje po, klienci k where r.pracownik_id=p.pracownik_id and r.pokoj_id=po.pokoj_id and r.klient_id=k.klient_id");
            while (result.next()) {
                int id = result.getInt("rezerwacja_id");
                String pesel = result.getString("pesel");
                String naz = result.getString("nazwisko");
                String pokoj = result.getString("numer");
                int datap = result.getInt("data_p");
                 int datak = result.getInt("data_k");
                 String status = result.getString("status");
                String kom = result.getString("komentarz");
                String ed = null;

                bookings_list.add(new Bookings(id, pesel, naz, pokoj, datap,datak,status,kom, ed));

            }

            return FXCollections.observableArrayList(bookings_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
