/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hotel.base.DataBase;
import hotel.base.Employee;
import hotel.base.Tasks;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Grzesiek
 */
public class Tasks_addController implements Initializable {
DataBase base; 
    @FXML
    private JFXComboBox<String> add_room;
    @FXML
    private JFXTextField add_comment;
    @FXML
    private TableView<Employee> task_employee_tableview;
    @FXML
    private TableColumn<?, ?> task_employee_id;
    @FXML
    private TableColumn<?, ?> task_employee;
    @FXML
    private TableColumn<?, ?> task_employee_count;
    ObservableList<String> task_room = ObjectManager.GetInstance().roomservice.getRoomData();
    @FXML
    private TableColumn<?, ?> task_employee_count2;
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

    private void initEmployeeTaskCount_Table() {
        task_employee_id.setCellValueFactory(new PropertyValueFactory<>("Id_employee"));
        task_employee.setCellValueFactory(new PropertyValueFactory<>("Surname_employee"));
        task_employee_count.setCellValueFactory(new PropertyValueFactory<>("Count_employee"));
        task_employee_count2.setCellValueFactory(new PropertyValueFactory<>("End_employee"));
        task_employee_tableview.getItems().setAll(ObjectManager.GetInstance().
                employeeservice.getTaskEmployeeData());
    }
    public void initEmployeeTasks_Table() {
        room_task.setCellValueFactory(new PropertyValueFactory<>("Room_task"));
        client_task.setCellValueFactory(new PropertyValueFactory<>("Client_task"));
        data_task.setCellValueFactory(new PropertyValueFactory<>("Data_task"));
        disc_task.setCellValueFactory(new PropertyValueFactory<>("Discription_task"));
        status_task.setCellValueFactory(new PropertyValueFactory<>("Status_task"));
        id_task.setCellValueFactory(new PropertyValueFactory<>("Id_task"));
        task_tableview.getItems().setAll(ObjectManager.GetInstance().taskservice.getDetailsData(
        Integer.toString(task_employee_tableview.getSelectionModel().getSelectedItem().getId_employee())));
    }
    
    @FXML
    private void addTask(ActionEvent event) {
        String room = add_room.getValue();
        String opis = add_comment.getText();
         if ( room==null || task_employee_tableview.getSelectionModel().getSelectedItem()==null ) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setHeaderText(null);
                alert1.setContentText("Uzupełnij wszystkie dane");
                alert1.showAndWait();
            } else {
                if (ObjectManager.GetInstance().taskservice.insertTask2(
                        Integer.parseInt(room), opis)
                && ObjectManager.GetInstance().taskservice.insertTaskWithEmployee(
                task_employee_tableview.getSelectionModel().getSelectedItem().getId_employee())) {
                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert4.setHeaderText(null);
                    alert4.setContentText("Dodano zadanie");
                    alert4.showAndWait();
                    initEmployeeTaskCount_Table();
                    if (ObjectManager.GetInstance().taskservice.checkRoomReady(
                    Integer.parseInt(room))) {
                    ObjectManager.GetInstance().roomservice.updateRoomStatus(
                        room, "2");
                    }

                } else {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setHeaderText(null);
                    alert2.setContentText("Błąd przy dodawaniu zadania");
                    alert2.showAndWait();
                }
            }
         
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
                   initEmployeeTaskCount_Table();

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
        initEmployeeTaskCount_Table();
        add_room.setItems(task_room);
        task_employee_tableview.getSelectionModel().selectedItemProperty().
                addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        initEmployeeTasks_Table();
                    }
                });
    }    
    
}
