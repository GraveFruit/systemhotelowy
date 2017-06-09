/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import hotel.base.DataBase;
import hotel.base.Employee;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Grzesiek
 */
public class Employee_addController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField pesel;
    @FXML
    private JFXComboBox<String> position;
    @FXML
    private JFXPasswordField password1;
    @FXML
    private JFXPasswordField password2;
    @FXML
    private JFXButton add;

    DataBase base;

    ObservableList<String> position_list = FXCollections.observableArrayList("Admin", "Menedżer", "Recepcja", "Obsługa");

    @FXML
    private void addEmployee(ActionEvent event) throws SQLException {
        String name_emp = name.getText();
        String surname_emp = surname.getText();
        String phone_emp = phone.getText();
        String pesel_emp = pesel.getText();
        String position_emp = position.getValue();
        String passwd1 = password1.getText();
        String passwd2 = password2.getText();
        if (name_emp.isEmpty() || surname_emp.isEmpty() || phone_emp.isEmpty() || pesel_emp.isEmpty() || position_emp.isEmpty() || passwd1.isEmpty() || passwd2.isEmpty()) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Wypełnij wszystkie pola");
        } else if (!passwd1.equals(passwd2)) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Różne hasła");
        } else if (!name_emp.matches("\\p{L}*") || !surname_emp.matches("\\p{L}*")) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błędne imię lub nazwisko");
        } else if (!pesel_emp.matches("[0-9]{11}")) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błędny pesel");
        } else if (!phone_emp.matches("^[0-9]{7,15}$")) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błędny telefon");
        } else {
            if (ObjectManager.GetInstance().employeeservice.insertEmployee(name_emp, surname_emp, phone_emp, pesel_emp, position_emp, passwd2)) {
                ObjectManager.GetInstance().dataservice.getInformactiontWindow("Dodano element");
            } else {
                ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd przy dodawaniu pracownika");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        position.setItems(position_list);
        base = DataBase.getInstance();
        ObjectManager.GetInstance().dataservice.chechIsNumber(phone);
        ObjectManager.GetInstance().dataservice.chechIsNumber(pesel);
    }

}
