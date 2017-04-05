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
 * @author Wiola
 */
public class Offer {
    private final IntegerProperty number_offer;
    private final StringProperty type_offer;
    private final StringProperty floor_offer;
    private final StringProperty standard_offer;
    
    public Offer(int room, String floor, String type, String stan) {
        this.number_offer = new SimpleIntegerProperty(room);
        this.floor_offer = new SimpleStringProperty(floor);
        this.type_offer = new SimpleStringProperty(type);
        this.standard_offer = new SimpleStringProperty(stan);
    }

    public Integer getNumber_offer() {
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
         
    
    public static ObservableList<Offer> getData() {
        try {
            ObservableList<Offer> offer_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("Select * from pokoje WHERE status = 0 ");
            while (result.next()) {
                int id = result.getInt("numer");
                String floor = result.getString("pietro");
                String type = result.getString("typ");
                String stan = result.getString("standard");
                

                offer_list.add(new Offer(id, floor, type, stan));

            }

            return FXCollections.observableArrayList(offer_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
