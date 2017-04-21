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

    public static ObservableList<Offer> getData(String typ, String stand, String datap, String datak) {
        try {
            ObservableList<Offer> offer_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select distinct p.numer,p.pietro,p.typ,p.standard  from pokoje p"
                    + " where p.typ='"+typ+"' and p.standard='"+stand+"' and p.pokoj_id not in "
                    + " (select distinct p.pokoj_id"
                    + " from pokoje p, rezerwacje r "
                    + " where p.pokoj_id = r.pokoj_id and(("
                    + datap+" between r.data_p  and r.data_k) or ( "+datak+" between r.data_p and r.data_k)"
                    + " or ("+datap+" between r.data_p  and r.data_k and "+datak+" between r.data_p and r.data_k)))");
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