/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Tasks;
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
public class TaskService {
    
     public ObservableList<Tasks> getData() {
        try {
        ObservableList<Tasks> tasks_list = FXCollections.observableArrayList();
        Statement statement = DataBase.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select po.numer,p.nazwisko, k.nazwisko, z.data, z.status from pracownicy p ,pokoje po, zadania z, klienci k, lista l where z.pokoj_id=po.pokoj_id and z.klient_id=k.klient_id and l.pracownik_id=p.pracownik_id and l.zadanie_id=z.zadanie_id");
        while(result.next()){
            //int id = result.getInt("id");
            String numer = result.getString("numer");
            String prac = result.getString("nazwisko");
            String klient= result.getString("nazwisko");
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
}
