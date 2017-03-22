/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;


/**
 *
 * @author Grzesiek
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button hotel_info;
    @FXML
    private Button app_info;
    @FXML  
    private Button settings;
    @FXML  
    private Button add_task;
    @FXML  
    private Button add_employee;
    @FXML
    private ToggleGroup admin_task;
    @FXML
    private ToggleGroup permissions;
    
    @FXML
    private void showInfoWindow(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("InfoAplication.fxml"));
        Scene info_scene= new Scene(loader);
        Stage info_stage =new Stage();
        info_stage.setScene(info_scene);
        info_stage.initModality(Modality.APPLICATION_MODAL);
        info_stage.initOwner(app_info.getScene().getWindow());
        info_stage.showAndWait();
    }
    
    @FXML
    private void showHotelInfoWindow(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("HotelInfo.fxml"));
        Scene info2_scene= new Scene(loader);
        Stage info2_stage =new Stage();
        info2_stage.setScene(info2_scene);
        info2_stage.initModality(Modality.APPLICATION_MODAL);
        info2_stage.initOwner(hotel_info.getScene().getWindow());
        info2_stage.showAndWait();
    }
    
    @FXML
    private void showSettingsWindow(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene settings_scene= new Scene(loader);
        Stage settings_stage =new Stage();
        settings_stage.setScene(settings_scene);
        settings_stage.initModality(Modality.APPLICATION_MODAL);
        settings_stage.initOwner(settings.getScene().getWindow());
        settings_stage.showAndWait();
    }
    
    @FXML
    private void add_taskwindow(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("tasks_add.fxml"));
        Scene task_scene= new Scene(loader);
        Stage task_stage =new Stage();
        task_stage.setScene(task_scene);
        task_stage.initModality(Modality.APPLICATION_MODAL);
        task_stage.initOwner(add_task.getScene().getWindow());
        task_stage.showAndWait();
    }
    
    @FXML
    private void add_employeewindow(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("employee_add.fxml"));
        Scene emp_scene= new Scene(loader);
        Stage emp_stage =new Stage();
        emp_stage.setScene(emp_scene);
        emp_stage.initModality(Modality.APPLICATION_MODAL);
        emp_stage.initOwner(add_employee.getScene().getWindow());
        emp_stage.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showHotelInfoWindow(MouseEvent event) {
    }

    @FXML
    private void showInfoWindow(MouseEvent event) {
    }

    @FXML
    private void showSettingsWindow(MouseEvent event) {
    }
    
}
