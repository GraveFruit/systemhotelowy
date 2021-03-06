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

    private IntegerProperty id_employee = null;
    private StringProperty name_employee;
    private StringProperty surname_employee;
    private StringProperty phone_employee;
    private StringProperty pesel_employee;
    private StringProperty position_employee;
    private StringProperty password_employee = null;
    private StringProperty status_employee = null;
    private IntegerProperty count_employee = null;
    private IntegerProperty end_employee = null;

    public Employee(String name_employee, String surname_employee, String phone_employee, String pesel_employee, String position_employee, String status_employee) {
        this.name_employee = new SimpleStringProperty(name_employee);
        this.surname_employee = new SimpleStringProperty(surname_employee);
        this.phone_employee = new SimpleStringProperty(phone_employee);
        this.pesel_employee = new SimpleStringProperty(pesel_employee);
        this.position_employee = new SimpleStringProperty(position_employee);
        this.status_employee = new SimpleStringProperty(status_employee);
    }

    public Employee(int id_employee, String name_employee, String surname_employee, String phone_employee, String pesel_employee, String position_employee, String status_employee, String password_employee) {
        this(name_employee, surname_employee, phone_employee, pesel_employee, position_employee, status_employee);
        this.id_employee = new SimpleIntegerProperty(id_employee);
        this.password_employee = new SimpleStringProperty(password_employee);
    }

    public Employee(int id_employee, String name_employee, int ilosc, int ilosc2) {
        this.id_employee = new SimpleIntegerProperty(id_employee);
        this.surname_employee = new SimpleStringProperty(name_employee);
        this.count_employee = new SimpleIntegerProperty(ilosc);
        this.end_employee = new SimpleIntegerProperty(ilosc2);
    }

    public String getStatus_employee() {
        return status_employee.getValue();
    }

    public int getCount_employee() {
        return count_employee.getValue();
    }

    public int getEnd_employee() {
        return end_employee.getValue();
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
}
