/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Tasks;
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
import mainwindow.ObjectManager;

/**
 *
 * @author Grzesiek
 */
public class TaskService {
    
     public ObservableList<Tasks> getData() {
        try {
        ObservableList<Tasks> tasks_list = FXCollections.observableArrayList();
        Statement statement = DataBase.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select po.numer,p.nazwisko, "
                + "k.nazwisko  as klient, z.data, z.status from pracownicy p ,pokoje po, "
                + "zadania z, klienci k, lista l where z.pokoj_id=po.pokoj_id "
                + "and z.klient_id=k.klient_id and l.pracownik_id=p.pracownik_id"
                + " and l.zadanie_id=z.zadanie_id order by z.data desc");
        while(result.next()){
            //int id = result.getInt("id");
            String numer = result.getString("numer");
            String prac = result.getString("nazwisko");
            String klient= result.getString("klient");
            String data = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data").getTime());
            
            String status = result.getString("status");
            
            tasks_list.add(new Tasks(numer,prac,klient,data,status));
            
        }
        
            return FXCollections.observableArrayList(tasks_list);
        }catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
   return null;
     }
     
     public boolean insertTask(String  pokoj) {
        int pokoj_id = 0;
        int klient_id = 0;
        try {
            ResultSet result1 = DataBase.getConnection().createStatement().executeQuery("select "
                    + "pokoj_id from rezerwacje where status='2' and pokoj_id='" + pokoj + "'");
            while (result1.next()) {
                pokoj_id = result1.getInt("pokoj_id");
            }
            ResultSet result2 = DataBase.getConnection().createStatement().executeQuery("select "
                    + "klient_id from rezerwacje where status='2' and pokoj_id='" + pokoj + "'");
            while (result2.next()) {
                klient_id = result2.getInt("klient_id");
                
            }
            
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Insert into zadania (pokoj_id,klient_id,data,status) values (?,?,?,?)");
            prep.setInt(1, pokoj_id);
            prep.setInt(2, klient_id);
            prep.setString(3, ObjectManager.GetInstance().currentData);
            prep.setString(4, "1");
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu zdania z recepcji");
            return false;
        }
        return true;
    }
     
     
}
