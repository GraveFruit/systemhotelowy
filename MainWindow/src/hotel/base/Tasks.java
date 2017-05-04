/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    private IntegerProperty id_task;
    private StringProperty employee_task;
    private StringProperty room_task;
    private StringProperty client_task;
    private StringProperty data_task;
    private StringProperty discription_task;
    private StringProperty status_task;

    public Tasks(int id,String room_task,String employee_task, String client_task, String date_task, String dis, String status_task) {
        this.id_task = new SimpleIntegerProperty(id);
        this.room_task = new SimpleStringProperty(room_task);
        this.employee_task = new SimpleStringProperty(employee_task);
        this.data_task = new SimpleStringProperty(date_task);
        this.client_task = new SimpleStringProperty(client_task);
        this.discription_task = new SimpleStringProperty(dis);
        this.status_task = new SimpleStringProperty(status_task);
    }

     public Tasks(int id,String room_task, String client_task, String date_task, String dis, String status_task) {
        this.id_task = new SimpleIntegerProperty(id);
        this.room_task = new SimpleStringProperty(room_task);
        this.data_task = new SimpleStringProperty(date_task);
        this.client_task = new SimpleStringProperty(client_task);
        this.discription_task = new SimpleStringProperty(dis);
        this.status_task = new SimpleStringProperty(status_task);
    }
    
    public int getId_task() {
        return id_task.getValue();
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

    public String getData_task() {
        return data_task.getValue();
    }

     public String getDiscription_task() {
        return discription_task.getValue();
    }
    
    public String getStatus_task() {
        return status_task.getValue();
    }

}
