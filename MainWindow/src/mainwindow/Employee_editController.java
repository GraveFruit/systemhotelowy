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
    private void changeEmployeeData(ActionEvent event) throws SQLException {
        String phone_emp = phone.getText();
        String pesel_emp = pesel.getText();
        String position_emp = position.getValue();
        if (phone_emp.isEmpty() || position_emp.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wypełnij wszystkie pola");
            alert1.showAndWait();
        } else {
            if (ObjectManager.GetInstance().employeeservice.updateEmployeeData(pesel_emp, phone_emp, position_emp)) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Akutalizowano pracownika");
                alert4.showAndWait();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy akutalizacji danych pracownika");
                alert2.showAndWait();
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
        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d*")) {
                } else {
                    phone.setText(oldValue);
                }
            }
        });
    }

}
