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
        Scene info_scene= new Scene(loader);
        Stage info_stage =new Stage();
        info_stage.setScene(info_scene);
        info_stage.initModality(Modality.APPLICATION_MODAL);
        info_stage.initOwner(hotel_info.getScene().getWindow());
        info_stage.showAndWait();
    }
    
    @FXML
    private void showSettingsWindow(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene info_scene= new Scene(loader);
        Stage info_stage =new Stage();
        info_stage.setScene(info_scene);
        info_stage.initModality(Modality.APPLICATION_MODAL);
        info_stage.initOwner(settings.getScene().getWindow());
        info_stage.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
