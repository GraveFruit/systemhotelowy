/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.Bookings;
import hotel.base.DataBase;
import hotel.base.Guests;
import java.sql.PreparedStatement;
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
import mainwindow.ObjectManager;

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
            ResultSet result = statement.executeQuery("select r.rezerwacja_id, "
                    + "k.nazwisko as klient, p.nazwisko, po.numer, r.data_p,r.data_k,"
                    + "r.status,r.komentarz from rezerwacje r, pracownicy p,"
                    + " pokoje po, klienci k where r.pracownik_id=p.pracownik_id "
                    + "and r.pokoj_id=po.pokoj_id and r.klient_id=k.klient_id"
                    + " order by r.status desc, r.data_p asc");
            while (result.next()) {
                int id = result.getInt("rezerwacja_id");
                String pesel = result.getString("klient");
                String naz = result.getString("nazwisko");
                String pokoj = result.getString("numer");
                String datap = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_p").getTime());
                //String datap1 = result.getString("data_p");
                String datak = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_k").getTime());
                String status = result.getString("status");
                String kom = result.getString("komentarz");

                bookings_list.add(new Bookings(id, pesel, naz, pokoj, datap, datak, status, kom));

            }

            return FXCollections.observableArrayList(bookings_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ObservableList<Bookings> getBookingCheckIn() {
        try {
            ObservableList<Bookings> bookingChceckin_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select r.rezerwacja_id, "
                    + "k.nazwisko as klient, p.nazwisko, po.numer, r.data_p,r.data_k,"
                    + "r.komentarz from rezerwacje r, pracownicy p,"
                    + " pokoje po, klienci k where r.pracownik_id=p.pracownik_id "
                    + "and r.pokoj_id=po.pokoj_id and r.klient_id=k.klient_id"
                    + " and r.status='1' and r.data_p='" + ObjectManager.GetInstance().currentData
                    + "' order by po.numer asc");
            while (result.next()) {
                int id = result.getInt("rezerwacja_id");
                String pesel = result.getString("klient");
                String naz = result.getString("nazwisko");
                String pokoj = result.getString("numer");
                String datap = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_p").getTime());
                //String datap1 = result.getString("data_p");
                String datak = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_k").getTime());
                String kom = result.getString("komentarz");

                bookingChceckin_list.add(new Bookings(id, pesel, naz, pokoj, datap, datak, kom));
            }

            return FXCollections.observableArrayList(bookingChceckin_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ObservableList<Bookings> getBookingCheckOut() {
        try {
            ObservableList<Bookings> bookingChceckin_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select r.rezerwacja_id, "
                    + "k.nazwisko as klient, p.nazwisko, po.numer, r.data_p,r.data_k,"
                    + "r.komentarz from rezerwacje r, pracownicy p,"
                    + " pokoje po, klienci k where r.pracownik_id=p.pracownik_id "
                    + "and r.pokoj_id=po.pokoj_id and r.klient_id=k.klient_id "
                    + "and r.status='2' order by po.numer asc");
            while (result.next()) {
                int id = result.getInt("rezerwacja_id");
                String pesel = result.getString("klient");
                String naz = result.getString("nazwisko");
                String pokoj = result.getString("numer");
                String datap = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_p").getTime());
                //String datap1 = result.getString("data_p");
                String datak = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_k").getTime());
                String kom = result.getString("komentarz");

                bookingChceckin_list.add(new Bookings(id, pesel, naz, pokoj, datap, datak, kom));
            }

            return FXCollections.observableArrayList(bookingChceckin_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertBooking(String klient, int pracownik, int pokoj, String datap, String datak, String komentarz) {
        int klient_id = 0;
        try {
            ResultSet result = DataBase.getConnection().createStatement().executeQuery("select "
                    + "klient_id from klienci where pesel='" + klient + "'");
            while (result.next()) {
                klient_id = result.getInt("klient_id");
            }

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Insert into rezerwacje (klient_id,pracownik_id,pokoj_id,data_p,data_k,status,komentarz) values (?,?,?,?,?,?,?)");
            prep.setInt(1, klient_id);
            prep.setInt(2, pracownik);
            prep.setInt(3, pokoj);
            prep.setString(4, datap);
            prep.setString(5, datak);
            prep.setString(6, "1");
            prep.setString(7, komentarz);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu rezewacji");
            return false;
        }
        return true;
    }

    public boolean updateCheckin(int numer) {
        try {

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update rezerwacje set status='2' where rezerwacja_id=?");
            prep.setInt(1, numer);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy meldowaniu");
            return false;
        }
        return true;
    }

    public boolean updateCheckout(int numer) {
        try {

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update rezerwacje set status='0',data_k='"
                    + ObjectManager.GetInstance().currentData + "' where rezerwacja_id=?");
            prep.setInt(1, numer);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy wymeldowywaniu");
            return false;
        }
        return true;
    }

    public boolean cancelBooking(int numer) {
        try {

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update rezerwacje set status='-1' where rezerwacja_id=?");
            prep.setInt(1, numer);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy archiwizacji rezerwacji");
            return false;
        }
        return true;
    }

    public boolean restoreBooking(int numer) {
        try {

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update rezerwacje set status='1' where rezerwacja_id=?");
            prep.setInt(1, numer);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy przywracaniu rezerwacji");
            return false;
        }
        return true;
    }

    public boolean deleteBooking(int numer) {
        try {

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "delete from rezerwacje where rezerwacja_id=?");
            prep.setInt(1, numer);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy archiwizacji rezerwacji");
            return false;
        }
        return true;
    }
    
    public boolean prologBooking(int numer,String data) {
        try {

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update rezerwacje set data_k=? where rezerwacja_id=?");
            prep.setString(1, data); 
            prep.setInt(2, numer);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy przedłużaniu rezerwacji");
            return false;
        }
        return true;
    }
}
