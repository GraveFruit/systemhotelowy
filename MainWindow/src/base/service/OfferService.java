/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Offer;
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
 * @author Grzesiek
 */
public class OfferService {
    public ObservableList<Offer> getData(String typ, String stand, String datap, String datak) {
        try {
            ObservableList<Offer> offer_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select distinct p.numer,p.pietro,p.typ,p.standard  from pokoje p"
                    + " where p.typ='"+typ+"' and p.standard='"+stand+"' and p.pokoj_id not in "
                    + " (select distinct p.pokoj_id"
                    + " from pokoje p, rezerwacje r "
                    + " where p.pokoj_id = r.pokoj_id and(('"
                    + datap+"' between r.data_p  and r.data_k) or ('"+datak+"' between r.data_p and r.data_k)"
                    + " or ('"+datap+"' between r.data_p  and r.data_k and '"+datak+"' between r.data_p and r.data_k)))");
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
