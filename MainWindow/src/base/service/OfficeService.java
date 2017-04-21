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
import mainwindow.FXMLDocumentController;

/**
 *
 * @author Wiola
 */
public class OfficeService {
    
   
    public ObservableList<Guests> getData() {
        try {
            ObservableList<Guests> guests_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select k.imie, k.nazwisko, k.tel, k.pesel from klienci k  where k.klient_id=k.klient_id");
            while (result.next()) {
                
                String imie = result.getString("imie");
                String nazwisko = result.getString("nazwisko");
                String tel = result.getString("tel");
                String pesel = result.getString("pesel");              
                

                guests_list.add(new Guests(imie,nazwisko,tel,pesel));

            }

            return FXCollections.observableArrayList(guests_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
