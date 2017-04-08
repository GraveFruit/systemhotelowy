/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.Bookings;
import hotel.base.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class BookingService {

    Button edit_bookings;

    public ObservableList<Bookings> getData() {
        try {
            ObservableList<Bookings> bookings_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select r.rezerwacja_id, k.pesel, p.nazwisko, po.numer, r.data_p,r.data_k,r.status,r.komentarz from rezerwacje r, pracownicy p, pokoje po, klienci k where r.pracownik_id=p.pracownik_id and r.pokoj_id=po.pokoj_id and r.klient_id=k.klient_id");
            while (result.next()) {
                int id = result.getInt("rezerwacja_id");
                String pesel = result.getString("pesel");
                String naz = result.getString("nazwisko");
                String pokoj = result.getString("numer");
                String datap = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_p").getTime());
                //String datap1 = result.getString("data_p");
                String datak = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_k").getTime());
                String status = result.getString("status");
                String kom = result.getString("komentarz");
                String ed = null;

                bookings_list.add(new Bookings(id, pesel, naz, pokoj, datap, datak, status, kom, ed));

            }

            return FXCollections.observableArrayList(bookings_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Button makeEditBookingsButton() {
        edit_bookings = new Button("Edytuj");
        edit_bookings.setMaxSize(70, 10);

        return edit_bookings;
    }

}
