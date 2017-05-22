/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Guests;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class GuestService {

    Button edit_guests;

    /**
     * methods gets data about guests from database
     * @return ObservableList contains query result
     */
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

                employee_list.add(new Guests(imie, naz, pesel, telefon));

            }

            return FXCollections.observableArrayList(employee_list);
        } catch (SQLException ex) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd wczytywania gości");
        }
        return null;
    }

    /**
     * method gets data about selected guest
     * @param name guest's name
     * @param surname guest's surname
     * @param pes guest's 'pesel"
     * @param phone guest's phone number
     * @return ObservableList contains query result
     */
    public ObservableList<Guests> getGuest_Data(String name, String surname, String pes, String phone) {
        try {
            ObservableList<Guests> employee_list = FXCollections.observableArrayList();
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "select imie, nazwisko, pesel, tel from klienci where imie "
                    + "like ? and nazwisko like ? and pesel like ? and "
                    + "tel like ?");
            prep.setString(1, name +"%");
            prep.setString(2, surname+"%");
            prep.setString(3, pes+"%");
            prep.setString(4, phone+"%");
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                //int id = result.getInt("id");
                String imie = result.getString("imie");
                String naz = result.getString("nazwisko");
                String pesel = result.getString("pesel");
                String telefon = result.getString("tel");

                employee_list.add(new Guests(imie, naz, pesel, telefon));
            }

            return FXCollections.observableArrayList(employee_list);
        } catch (SQLException ex) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd wczytywania danego gościa");
        }
        return null;
    }

    /**
     * method adds new guest into database
     * @param name guest's name
     * @param surname guest's surname
     * @param phone guest's phone number
     * @param pesel guest's "pesel"
     * @return true if success
     */
    public boolean insertClient(String name, String surname, String phone, String pesel) {
        try {
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Insert into klienci (imie, nazwisko, tel,  pesel) values (?,?,?,?)");
            prep.setString(1, name);
            prep.setString(2, surname);
            prep.setString(3, phone);
            prep.setString(4, pesel);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu pracownika");
            return false;
        }
        return true;

    }
    
//edycja goscia

    /**
     * method updates selected guest's data (only phone number)
     * @param pesel guest's "pesel"
     * @param telefon guest's phone number
     * @return true if success
     */
    public boolean updateGuestData(String pesel, String telefon) {
        try{
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update klienci set tel=? where pesel=?");
            prep.setString(1, telefon);            
            prep.setString(2, pesel);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy aktualizacji goscia");
            return false;
        }
        return true;
    }
//usuwanie pracownika

    /**
     * method deletes guest from database
     * @param pesel guest "pesel"
     * @return true if success
     */
    public boolean deleteGuest(String pesel) {
        try {
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "delete klienci where pesel=?");
            prep.setString(1, pesel);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy usuwaniu goscia");
            return false;
        }
        return true;
    }

    
}
