/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Employee;
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
public class EmployeeService {
    
    
    public ObservableList<Employee> getData() {
        try {
        ObservableList<Employee> employee_list = FXCollections.observableArrayList();
        Statement statement = DataBase.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select p.imie, p.nazwisko, p.telefon, p.pesel, p.status, po.nazwa from pracownicy p ,posady po where p.posada_id=po.posada_id");
        while(result.next()){
            //int id = result.getInt("id");
            String imie = result.getString("imie");
            String type = result.getString("nazwisko");
            String floor= result.getString("telefon");
            String lozko = result.getString("pesel");
            String stan = result.getString("nazwa");
            String status = result.getString("status");
            
            employee_list.add(new Employee(imie,type,floor,lozko,stan,status));
            
        }
        
            return FXCollections.observableArrayList(employee_list);
        }catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
   return null;
     } 
}