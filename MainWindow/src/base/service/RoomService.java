/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Rooms;
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

/**
 *
 * @author Grzesiek
 */
public class RoomService {

    Button edit_rooms;
//wyswietlanie pokoi
    public ObservableList<Rooms> getData() {
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

                rooms_list.add(new Rooms(id, floor, type, stan, status));

            }

            return FXCollections.observableArrayList(rooms_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //wyswietlanie numerow pokoi
    public ObservableList<String> getRoomData() {
        try {
            ObservableList<String> rooms_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("Select pokoj_id from pokoje");
            while (result.next()) {
                int id = result.getInt("pokoj_id");
                rooms_list.add(Integer.toString(new Rooms(id).getNumber_room()));
            }

            return FXCollections.observableArrayList(rooms_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//zmiana statusu pokoju
    public boolean updateRoomStatus(String numer, String status) {
        try {

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update pokoje set status=? where pokoj_id=?");
            prep.setString(1, status);
            prep.setString(2, numer);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy meldowaniu");
            return false;
        }
        return true;
    }
  //sprawdzanie czy pokoj jest gotowy do meldowania
      public boolean checkRoomStatus(String numer) {
       String status="";
        boolean wynik=false;
         try {
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery(
                    "Select status from pokoje where pokoj_id='"+ numer+"'");
            while(result.next()){
             status=result.getString("status");
         }
            wynik=status.compareTo("2")==0;
        } catch (SQLException e) {
            System.err.println("Błąd przy sprawdzaniu statusu pokoju");
        }
        return !wynik;
    }
      
 //edycja pokoi
 
      public boolean updateRoomsData(String pokoj, String typ, String standard, String status) {

        try {
           
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update pokoje set typ=?, standard=?, status=? where pokoj_id=?");
            prep.setString(1, typ);
            prep.setString(2, standard);
            prep.setString(3, status);
            prep.setString(4, pokoj);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy aktualizacji pokoju");
            return false;
        }
        return true;
    }
    
}
