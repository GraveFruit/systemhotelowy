/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Grzesiek
 */
public class Rooms_addController implements Initializable {

    @FXML
    private JFXComboBox<String> room_type;
    @FXML
    private JFXComboBox<String> room_standard;
    @FXML
    private JFXComboBox<String> room_status;
    @FXML
    private JFXTextField room_number;
    @FXML
    private JFXTextField room_floor;

    ObservableList<String> position_list1 = FXCollections.observableArrayList("1", "1+1", "2", "2+1");
    ObservableList<String> position_list2 = FXCollections.observableArrayList("biznesowy", "ekonomiczny", "vip");
    ObservableList<String> position_list3 = FXCollections.observableArrayList("0", "1", "2");
    ObservableList<String> rooms_list;

    @FXML
    private void addRoomsData(ActionEvent event) throws SQLException {
        String nr_pokoju = room_number.getText();
        String nr_pietra = room_floor.getText();
        String typ_pokoju = room_type.getValue();
        String standard_pokoju = room_standard.getValue();
        String status_pokoju = room_status.getValue();
        
        
        if (typ_pokoju.isEmpty() || standard_pokoju.isEmpty() || status_pokoju.isEmpty() || nr_pokoju.isEmpty() || nr_pietra.isEmpty() ) {
           ObjectManager.GetInstance().dataservice.getAlertWindow("Wypełnij wszystkie pola");
        } else if(!rooms_list.contains(nr_pokoju)){
            if (ObjectManager.GetInstance().roomservice.addRoomsData(nr_pokoju,nr_pietra, typ_pokoju, standard_pokoju, status_pokoju )) {
                ObjectManager.GetInstance().dataservice.getInformactiontWindow("Dodano pokój");
            } else {
                ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd przy akutalizacji pokoju");
            }
        }else{
            ObjectManager.GetInstance().dataservice.getInformactiontWindow("Pokój istnieje");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rooms_list=ObjectManager.GetInstance().roomservice.getRoomData();
        room_type.setItems(position_list1);
        room_standard.setItems(position_list2);
        room_status.setItems(position_list3);
    }    

}
