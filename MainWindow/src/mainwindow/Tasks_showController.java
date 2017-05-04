/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import hotel.base.DataBase;
import hotel.base.Tasks;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Grzesiek
 */
public class Tasks_showController implements Initializable {

    @FXML
    private TableView<Tasks> task_tableview;
    @FXML
    private TableColumn<?, ?> id_task;
    @FXML
    private TableColumn<?, ?> room_task;
    @FXML
    private TableColumn<?, ?> client_task;
    @FXML
    private TableColumn<?, ?> data_task;
    @FXML
    private TableColumn<?, ?> disc_task;
    @FXML
    private TableColumn<?, ?> status_task;
    @FXML
    private Button change_task_status;
    
    DataBase base;
    public void initEmployeeTasks_Table() {
        room_task.setCellValueFactory(new PropertyValueFactory<>("Room_task"));
        client_task.setCellValueFactory(new PropertyValueFactory<>("Client_task"));
        data_task.setCellValueFactory(new PropertyValueFactory<>("Data_task"));
        disc_task.setCellValueFactory(new PropertyValueFactory<>("Discription_task"));
        status_task.setCellValueFactory(new PropertyValueFactory<>("Status_task"));
        id_task.setCellValueFactory(new PropertyValueFactory<>("Id_task"));
        task_tableview.getItems().setAll(ObjectManager.GetInstance().taskservice.getDetailsData(
                ObjectManager.GetInstance().loginservice.employeeSessionId));
    }

     @FXML
    private void changeTaskStatus(ActionEvent event) {
         if ( task_tableview.getSelectionModel().getSelectedItem()==null ) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setHeaderText(null);
                alert1.setContentText("Zaznacz zadanie");
                alert1.showAndWait();
            } else {
             if(ObjectManager.GetInstance().taskservice.updateTaskStatus(
             task_tableview.getSelectionModel().getSelectedItem().getId_task())){
             String room= task_tableview.getSelectionModel().getSelectedItem().getRoom_task();
                if (ObjectManager.GetInstance().taskservice.checkRoomReady(
                    Integer.parseInt(room))) {
                ObjectManager.GetInstance().roomservice.updateRoomStatus(
                        room, "0");
                }
                   initEmployeeTasks_Table();

                } else {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setHeaderText(null);
                    alert2.setContentText("Błąd przy dodawaniu zadania");
                    alert2.showAndWait();
                }
            
    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       base = DataBase.getInstance();
        initEmployeeTasks_Table();
    }

}
