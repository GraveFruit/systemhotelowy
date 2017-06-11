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
 *
 * @author Wiola
 */
public class Guest_addController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField pesel;
    @FXML
    private JFXButton add;

    DataBase base;

    @FXML
    private void addGuest(ActionEvent event) throws SQLException {
        String name_guest = name.getText();
        String surname_guest = surname.getText();
        String phone_guest = phone.getText();
        String pesel_guest = pesel.getText();
        if (name_guest.isEmpty() || surname_guest.isEmpty() || phone_guest.isEmpty() || pesel_guest.isEmpty()) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Wypełnij wszystkie pola");
        } else if (!ObjectManager.GetInstance().checkdata.isName(name_guest) || !ObjectManager.GetInstance().checkdata.isName(surname_guest)) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błędne imię lub nazwisko");
        } else if (!ObjectManager.GetInstance().checkdata.isPesel(pesel_guest)) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błędny pesel");
        } else if (!ObjectManager.GetInstance().checkdata.isPhone(phone_guest)) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błędny telefon");
        } else {
            if (ObjectManager.GetInstance().guestservice.insertClient(name_guest, surname_guest, phone_guest, pesel_guest)) {
                ObjectManager.GetInstance().dataservice.getInformactiontWindow("Dodano element");
            } else {
                ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd przy dodawaniu pracownika");

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base = DataBase.getInstance();
        ObjectManager.GetInstance().dataservice.chechIsNumber(phone);
        ObjectManager.GetInstance().dataservice.chechIsNumber(pesel);
    }

}
