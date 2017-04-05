/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hotel.base.DataBase;
import hotel.base.Employee;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
    private JFXTextField password1;
    @FXML
    private JFXTextField password2;
    @FXML
    private JFXButton add;

    DataBase base;

    ObservableList<String> position_list = FXCollections.observableArrayList("Admin", "Menedzer", "Recepcja", "Obsługa");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        position.setItems(position_list);
        base = DataBase.getInstance();
    }

    @FXML
    private void addEmployee(ActionEvent event) {
        String name_emp = name.getText();
        String surname_emp = surname.getText();
        String phone_emp = phone.getText();
        String pesel_emp = pesel.getText();
        String position_emp = position.getValue();
        String passwd1 = password1.getText();
        String passwd2 = password2.getText();

        if (name_emp.isEmpty() || surname_emp.isEmpty() || phone_emp.isEmpty() || pesel_emp.isEmpty() || position_emp.isEmpty() || passwd1.isEmpty() || passwd2.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wypełnij wszystkie pola");
            alert1.showAndWait();
            return;
        } else if (!passwd1.equals(passwd2)) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setHeaderText(null);
            alert2.setContentText("Różne hasła");
            alert2.showAndWait();
            return;
        } else {
            try {
                Statement statement = DataBase.getConnection().createStatement();
                String update = "Insert into pracownicy (imie, nazwisko, telefon, pesel, podasa_id, hasło) values ("
                        //+"'"+null+"',"
                        + "'" + name_emp + "',"
                        + "'" + surname_emp + "',"
                        + "'" + phone_emp + "',"
                        + "'" + pesel_emp + "',"
                        + "'" + position_emp + "',"
                        //+"'"+ null+"',"
                        + "'" + passwd2 + "') from";

                statement.executeUpdate(update);
            } catch (SQLException ex) {
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setHeaderText(null);
                alert3.setContentText("Błąd przy dodawaniu danych");
                alert3.showAndWait();
            }
            Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
            alert4.setHeaderText(null);
            alert4.setContentText("Dodano element");
            alert4.showAndWait();
            FXMLDocumentController ob = new FXMLDocumentController();
        }
        

    }

}
