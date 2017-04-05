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
import mainwindow.FXMLDocumentController;

/**
 *
 * @author Grzesiek
 */
public class Employee {
    private IntegerProperty id_employee=null;
    private StringProperty name_employee;
    private StringProperty surname_employee;
    private StringProperty phone_employee;
    private StringProperty pesel_employee;
    private StringProperty position_employee;
    private StringProperty password_employee=null;
    private StringProperty status_employee=null;
    
    public Employee( String name_employee, String surname_employee, String phone_employee, String pesel_employee, String position_employee,String status_employee) {
        this.name_employee = new SimpleStringProperty( name_employee);
        this.surname_employee =new SimpleStringProperty( surname_employee);
        this.phone_employee = new SimpleStringProperty( phone_employee);
        this.pesel_employee =new SimpleStringProperty( pesel_employee);
        this.position_employee = new SimpleStringProperty( position_employee);
        this.status_employee=new SimpleStringProperty(status_employee);
}
     public Employee(int id_employee, String name_employee, String surname_employee, String phone_employee, String pesel_employee, String position_employee,String status_employee, String password_employee) {
        this(name_employee, surname_employee, phone_employee, pesel_employee, position_employee, status_employee);
        this.id_employee = new SimpleIntegerProperty( id_employee);
        this.password_employee=new SimpleStringProperty(password_employee);
        
     }
        public String getStatus_employee() {
        return status_employee.getValue();
    }

    public int getId_employee() {
        return id_employee.getValue();
    }

    public String getName_employee() {
        return name_employee.getValue();
    }

    public String getSurname_employee() {
        return surname_employee.getValue();
    }

    public String getPhone_employee() {
        return phone_employee.getValue();
    }

    public String getPesel_employee() {
        return pesel_employee.getValue();
    }

    public String getPosition_employee() {
        return position_employee.getValue();
    }

    public String getPassword_employee() {
        return password_employee.getValue();
    }
public static ObservableList<Employee> getData() {
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
