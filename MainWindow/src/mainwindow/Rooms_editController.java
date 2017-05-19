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
import javafx.scene.control.TableColumn;
/**
 *
 * @author Eruru
 */
public class Rooms_editController implements Initializable{

@FXML
    private JFXTextField room_number;
    @FXML
    private JFXComboBox<String> room_type;
    @FXML
    private JFXTextField room_floor;
    @FXML
    private JFXComboBox<String> room_standard;
    @FXML
    private JFXComboBox<String> room_status;
    
    ObservableList<String> position_list1 = FXCollections.observableArrayList("1", "1+1", "2", "2+1");
    ObservableList<String> position_list2 = FXCollections.observableArrayList("biznesowy", "ekonomiczny", "vip");
    ObservableList<String> position_list3 = FXCollections.observableArrayList("0", "1", "2");

    @FXML
    private void changeRoomsData(ActionEvent event) throws SQLException {
        String nr_pokoju = room_number.getText();
        String nr_pietra = room_floor.getText();
        String typ_pokoju = room_type.getValue();
        String standard_pokoju = room_standard.getValue();
        String status_pokoju = room_status.getValue();
        
        
        if (typ_pokoju.isEmpty() || standard_pokoju.isEmpty() || status_pokoju.isEmpty() ) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wypełnij wszystkie pola");
            alert1.showAndWait();
        } else {
            if (ObjectManager.GetInstance().roomservice.updateRoomsData(nr_pokoju, typ_pokoju, standard_pokoju, status_pokoju )) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Zakutalizowano pokój");
                alert4.showAndWait();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy akutalizacji pokoju");
                alert2.showAndWait();
            }
        }
    }

     void addRoomsData(int room2, String floor2, String type2, String standard2, String status2) {
        room_number.setText(Integer.toString(room2));
        room_floor.setText(floor2);
        room_number.setDisable(true);
        room_floor.setDisable(true);
        //room_status.setDisable(true);
        room_type.setValue(type2);
        room_standard.setValue(standard2);
        room_status.setValue(status2);
    }

    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        room_type.setItems(position_list1);
        room_standard.setItems(position_list2);
        room_status.setItems(position_list3);

    }
    
}
