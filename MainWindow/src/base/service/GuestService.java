/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Guests;
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
public class GuestService {

    Button edit_guests;

    public ObservableList<Guests> getData() {
        try {
            ObservableList<Guests> employee_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select imie, nazwisko, pesel, tel from klienci");
            while (result.next()) {
                //int id = result.getInt("id");
                String imie = result.getString("imie");
                String naz = result.getString("nazwisko");
                String pesel = result.getString("pesel");
                String telefon = result.getString("tel");
                String ed = null;

                employee_list.add(new Guests(imie, naz, pesel, telefon, ed));

            }

            return FXCollections.observableArrayList(employee_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ObservableList<String> getBooking_Data() {
        try {
            ObservableList<String> employee_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select pesel from klienci");
            while (result.next()) {
                //int id = result.getInt("id");
                String naz = result.getString("pesel");
                

                employee_list.add(new Guests(naz).getPesel_guest());

            }

            return FXCollections.observableArrayList(employee_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Button makeEditGuestsButton() {
        edit_guests = new Button("Edytuj");
        edit_guests.setMaxSize(70, 10);

        return edit_guests;
    }

}
