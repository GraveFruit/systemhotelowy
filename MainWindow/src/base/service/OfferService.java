/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Offer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
//wyswietlanie dostepnych pokoi
    public ObservableList<Offer> getData(String typ, String stand, String datap, String datak) {
        try {
            ObservableList<Offer> offer_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select distinct p.pokoj_id as numer,p.pietro,p.typ,p.standard "
                    + "from pokoje p"
                    + " where p.typ='" + typ + "' and p.standard='" + stand + "' and p.pokoj_id not in "
                    + " (select distinct p.pokoj_id from pokoje p, rezerwacje r "
                    + " where p.pokoj_id = r.pokoj_id and r.status>0 and(('"
                    + datap + "' between r.data_p  and DATE_SUB(r.data_k,INTERVAL 1 DAY)) or ('" + datak + "' between DATE_ADD(r.data_p,INTERVAL 1 DAY) and r.data_k)"
                    + " or ('" + datap + "' between r.data_p  and r.data_k and '" + datak + "' between r.data_p and r.data_k)))");
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
//wyswietlanie rezerwacji danych klientow
    public ObservableList<Offer> getReceptionData(String pesel) {
        int klient_id = 0;
        try {
            ResultSet res = DataBase.getConnection().createStatement().executeQuery("select "
                    + "klient_id from klienci where pesel='" + pesel + "'");
            while (res.next()) {
                klient_id = res.getInt("klient_id");
            }
            ObservableList<Offer> offer_list = FXCollections.observableArrayList();
            PreparedStatement prep = DataBase.getConnection().prepareStatement("select r.rezerwacja_id, "
                    + "p.pokoj_id, p.standard, p.typ, p.pietro, r.data_p, "
                    + "r.data_k,r.komentarz,r.status from rezerwacje r, pokoje p, "
                    + "klienci k where r.pokoj_id=p.pokoj_id and "
                    + "r.klient_id=k.klient_id and (r.status='1' or r.status='-1') and k.klient_id=?");
            prep.setInt(1, klient_id);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                int rid = result.getInt("rezerwacja_id");
                int id = result.getInt("pokoj_id");
                String stan = result.getString("standard");
                String type = result.getString("typ");
                String floor = result.getString("pietro");
                String datap = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_p").getTime());
                String datak = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data_k").getTime());
                String kom = result.getString("komentarz");
                String st = result.getString("status");
                offer_list.add(new Offer(rid, id, stan,type, floor, datap, datak, kom,st));

            }

            return FXCollections.observableArrayList(offer_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//sprawdzanie mozliwosci przedluzenia rezerwacji
     public boolean getPrologData(String pokoj, String datap, String datak) {
        String standard="";
        boolean wynik=false;
         try {
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select p.standard from pokoje p"
                    + " where p.pokoj_id='" + pokoj + "' and p.pokoj_id not in "
                    + " (select distinct p.pokoj_id from pokoje p, rezerwacje r "
                    + " where p.pokoj_id = r.pokoj_id and r.status>0 and(('"
                    + datap + "' between r.data_p  and DATE_SUB(r.data_k,INTERVAL 1 DAY)) or ('" + datak + "' between DATE_ADD(r.data_p,INTERVAL 1 DAY) and r.data_k)"
                    + " or ('" + datap + "' between r.data_p  and r.data_k and '" + datak + "' between r.data_p and r.data_k)))");
            while(result.next()){
             standard=result.getString("standard");
         }
             wynik=standard.isEmpty();
              //System.out.println("stan= "+standard+" wynik= "+wynik);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return !wynik;
    }
    
}
