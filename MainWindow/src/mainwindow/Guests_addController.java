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
import hotel.base.Guests;
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
 *
 * @author Wiola
 */
public class Guests_addController {
    
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
    private void addGuests(ActionEvent event) {
        String name_gue = name.getText();
        String surname_gue = surname.getText();
        String phone_gue = phone.getText();
        String pesel_gue = pesel.getText();
     
         if (name_gue.isEmpty() || surname_gue.isEmpty() || phone_gue.isEmpty() || pesel_gue.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wypełnij wszystkie pola");
            alert1.showAndWait();
            return;
         }   else {         
            try {
                Statement statement = DataBase.getConnection().createStatement();
                ResultSet result = statement.executeQuery("select imie, nazwisko, tel, pesel from klienci");
                String update = "Insert into pracownicy (imie, nazwisko, telefon, pesel) values ("
                        //+"'"+null+"',"
                        + "'" + name_gue + "',"
                        + "'" + surname_gue + "',"
                        + "'" + phone_gue + "',"
                        + "'" + pesel_gue + "',";

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
