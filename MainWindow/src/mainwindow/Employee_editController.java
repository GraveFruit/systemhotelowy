/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
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
public class Employee_editController implements Initializable {

    @FXML
    private JFXTextField phone;
    @FXML
    private JFXComboBox<String> position;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField pesel;

    ObservableList<String> position_list = FXCollections.observableArrayList("Admin", "Menedżer", "Recepcja", "Obsługa");
    @FXML
    private JFXPasswordField new_password;
    @FXML
    private JFXPasswordField confirmed_new_password;

    @FXML
    private void changeEmployeeData(ActionEvent event) throws SQLException {
        String phone_emp = phone.getText();
        String pesel_emp = pesel.getText();
        String position_emp = position.getValue();
        if (phone_emp.isEmpty() || position_emp.isEmpty()) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Wypełnij wszystkie pola");
        } else {
            if (ObjectManager.GetInstance().employeeservice.updateEmployeeData(pesel_emp, phone_emp, position_emp)) {
                ObjectManager.GetInstance().dataservice.getInformactiontWindow("Akutalizowano pracownika");
            } else {
                ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd przy akutalizacji danych pracownika");
            }
        }
    }

    @FXML
    private void changeEmployeePassword(ActionEvent event) throws SQLException {
        String pass1 = new_password.getText();
        String pass2 = confirmed_new_password.getText();
        String pesel1 = pesel.getText();
        if (pass1.isEmpty() || pesel1.isEmpty()) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Wypełnij wszystkie pola");
        } else if (pass1.compareTo(pass2) != 0) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Różne hasła");
        } else {
            if (ObjectManager.GetInstance().employeeservice.updateEmployeePassword(pass2, pesel1)) {
                ObjectManager.GetInstance().dataservice.getInformactiontWindow("Akutalizowano hasło pracownika");
            } else {
                ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd przy akutalizacji hasła pracownika");
            }
        }
    }

    void addEmployeeData(String name2, String surname2, String pesel2, String number, String profession) {
        name.setText(name2);
        surname.setText(surname2);
        pesel.setText(pesel2);
        name.setDisable(true);
        surname.setDisable(true);
        pesel.setDisable(true);
        phone.setText(number);
        position.setValue(profession);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        position.setItems(position_list);
        ObjectManager.GetInstance().dataservice.chechIsNumber(phone);
    }

}
