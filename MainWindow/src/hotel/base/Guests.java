/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.base;

import base.service.GuestService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
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
public class Guests {
    private StringProperty name_guest;
    private StringProperty surname_guest;
    private StringProperty phone_guest;
    private StringProperty pesel_guest;
    
     public Guests(String pesel) {
        this.pesel_guest = new SimpleStringProperty(pesel);

    }

    public Guests(String name, String surname, String pesel, String phone) {
        this(pesel);
        this.name_guest = new SimpleStringProperty(name);
        this.surname_guest = new SimpleStringProperty(surname);
        this.phone_guest = new SimpleStringProperty(phone);
    }
    
    public String getName_guest() {
        return name_guest.getValue();
    }

    public String getSurname_guest() {
        return surname_guest.getValue();
    }

    public String getPhone_guest() {
        return phone_guest.getValue();
    }

    public String getPesel_guest() {
        return pesel_guest.getValue();
    }
      
}
