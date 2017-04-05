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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mainwindow.FXMLDocumentController;

/**
 *
 * @author Grzesiek
 */
public class Tasks {
    private StringProperty employee_task;
    private StringProperty room_task;
    private StringProperty client_task;
    private StringProperty data_task;
    private StringProperty status_task;

    public Tasks(String room_task,String employee_task,String client_task, String date_task, String status_task) {
        this.employee_task = new SimpleStringProperty(employee_task);
        this.room_task = new SimpleStringProperty(room_task);
        this.data_task = new SimpleStringProperty(date_task);
        this.client_task = new SimpleStringProperty(client_task);
        this.status_task = new SimpleStringProperty(status_task);
    }

    public String getEmployee_task() {
        return employee_task.getValue();
    }

    public String getRoom_task() {
        return room_task.getValue();
    }

    public String getClient_task() {
        return client_task.getValue();
    }
    
    public StringProperty getData_task() {
        return data_task;
    }
    
    public String getStatus_task() {
        return status_task.getValue();
    }
    
    public static ObservableList<Tasks> getData() {
        try {
        ObservableList<Tasks> tasks_list = FXCollections.observableArrayList();
        Statement statement = DataBase.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select po.numer,p.nazwisko, k.nazwisko, z.data, z.status from pracownicy p ,pokoje po, zadania z, klienci k, lista l where z.pokoj_id=po.pokoj_id and z.klient_id=k.klient_id and l.pracownik_id=p.pracownik_id and l.zadanie_id=z.zadanie_id");
        while(result.next()){
            //int id = result.getInt("id");
            String numer = result.getString("numer");
            String prac = result.getString("nazwisko");
            String klient= result.getString("nazwisko");
            String data = result.getString("data");
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
