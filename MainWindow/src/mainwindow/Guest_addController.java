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
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wypełnij wszystkie pola");
            alert1.showAndWait();
        } else {
            if (ObjectManager.GetInstance().guestservice.insertClient(name_guest, surname_guest, phone_guest, pesel_guest)) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Dodano element");
                alert4.showAndWait();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy dodawaniu pracownika");
                alert2.showAndWait();
            }
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        base = DataBase.getInstance();
        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d*")) {
                } else {
                    phone.setText(oldValue);
                }
            }
        });
        pesel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d*")) {
                } else {
                    pesel.setText(oldValue);
                }
            }
        });
    }

}
