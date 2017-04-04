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
    
    ObservableList<String> position_list = FXCollections.observableArrayList("admin", "manager","recepcionist","cleaner");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       position.setItems(position_list);
        base= DataBase.getInstance();
    }

    @FXML
    private void addEmployee(ActionEvent event) {
        String name_emp = name.getText();
        String surname_emp = surname.getText();
        String phone_emp = phone.getText();
        String pesel_emp=pesel.getText();
        String position_emp=position.getValue();
        String passwd1=password1.getText();
        String passwd2=password2.getText();
        
    }

}
