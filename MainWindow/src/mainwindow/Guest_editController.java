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
 * @author Wiola
 */
public class Guest_editController implements Initializable {

    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField pesel;

    @FXML
    private void changeGuestData(ActionEvent event) throws SQLException {
        String phone_guest = phone.getText();
        String pesel_guest = pesel.getText();
        if (phone_guest.isEmpty() || pesel_guest.isEmpty()) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Wypełnij wszystkie pola");

        } else {
            if (ObjectManager.GetInstance().guestservice.updateGuestData(pesel_guest, phone_guest)) {
                ObjectManager.GetInstance().dataservice.getInformactiontWindow("Akutalizowano goscia");
            } else {
                ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd przy akutalizacji danych goscia");
            }
        }

    }

    void addGuestData(String name2, String surname2, String pesel2, String number) {
        name.setText(name2);
        surname.setText(surname2);
        pesel.setText(pesel2);
        name.setDisable(true);
        surname.setDisable(true);
        pesel.setDisable(true);
        phone.setText(number);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObjectManager.GetInstance().dataservice.chechIsNumber(phone);
    }
}
