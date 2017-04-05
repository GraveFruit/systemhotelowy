/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import hotel.base.Bookings;
import hotel.base.DataBase;
import hotel.base.Employee;
import hotel.base.Guests;
import hotel.base.Rooms;
import hotel.base.Offer;
import hotel.base.Tasks;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
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

    DataBase base;
    static  Button edit_rooms;
    static  Button edit_bookings;
    static  Button edit_guests;
    @FXML
    private Region region;
    @FXML
    private Button button_new;
    @FXML
    private TableColumn<?, ?> room_number;
    @FXML
    private TableColumn<?, ?> room_type;
    @FXML
    private TableColumn<?, ?> room_floor;
    @FXML
    private TableColumn<?, ?> room_standard;
    @FXML
    private TableColumn<?, ?> room_status;
    @FXML
    private TableColumn<?, ?> employee_name;
    @FXML
    private TableColumn<?, ?> employee_surname;
    @FXML
    private TableColumn<?, ?> employee_phone;
    @FXML
    private TableColumn<?, ?> employee_pesel;
    @FXML
    private TableColumn<?, ?> employee_position;
    @FXML
    private TableColumn<?, ?> employee_status;
    @FXML
    private TableView<Rooms> room_tableview;
    @FXML
    private TableColumn<?, ?> room_edition;
    @FXML
    private TableView<Employee> employee_tableview;
    @FXML
    private TableColumn<?, ?> offer_number;
    @FXML
    private TableColumn<?, ?> offer_type;
    @FXML
    private TableColumn<?, ?> offer_floor;
    @FXML
    private TableColumn<?, ?> offer_standard;
    @FXML
    private TableView<Offer> offer_tableview;
    @FXML
    private TableView<Guests> guest_tableview;
    @FXML
    private TableColumn<?, ?> guest_name;
    @FXML
    private TableColumn<?, ?> guest_surname;
    @FXML
    private TableColumn<?, ?> guest_pesel;
    @FXML
    private TableColumn<?, ?> guest_phone;
    @FXML
    private TableColumn<?, ?> guest_edition;
    @FXML
    private TableView<Bookings> booking_tableview;
    @FXML
    private TableColumn<?, ?> booking_id;
    @FXML
    private TableColumn<?, ?> booking_client;
    @FXML
    private TableColumn<?, ?> booking_employee;
    @FXML
    private TableColumn<?, ?> booking_room;
    @FXML
    private TableColumn<?, ?> booking_datap;
    @FXML
    private TableColumn<?, ?> booking_datak;
    @FXML
    private TableColumn<?, ?> booking_status;
    @FXML
    private TableColumn<?, ?> booking_comment;
    @FXML
    private TableColumn<?, ?> booking_edition;
    @FXML
    private TableView<Tasks> task_tableview;
    @FXML
    private TableColumn<?, ?> room_task;
    @FXML
    private TableColumn<?, ?> employee_task;
    @FXML
    private TableColumn<?, ?> client_task;
    @FXML
    private TableColumn<?, ?> data_task;
    @FXML
    private TableColumn<?, ?> status_task;

    @FXML//okno informacji o aplikacji
    private void showInfoWindow(ActionEvent event) throws IOException {
        makeWindow("InfoAplication.fxml", "Informacje o aplikacji", app_info);
    }

    @FXML//okno informacji o hotelu
    private void showHotelInfoWindow(ActionEvent event) throws IOException {
        makeWindow("HotelInfo.fxml", "Informacje o hotelu", hotel_info);
    }

    @FXML//okno ustawień
    private void showSettingsWindow(ActionEvent event) throws IOException {
        makeWindow("Settings.fxml", "Ustawienia", settings);
    }

    @FXML//podokno dodaj zadanie
    private void add_taskwindow(ActionEvent event) throws IOException {
        makeWindow("tasks_add.fxml", "Dodaj zadanie", add_task);
    }

    @FXML//podokno dodaj pracownika
    private void add_employeewindow(ActionEvent event) throws IOException {
        makeWindow("employee_add.fxml", "Dodaj pracownika", add_employee);
    }

    public void makeWindow(String file, String name, Button button) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource(file));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(name);
            stage.initOwner(button.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException exc) {
            System.out.print(" Error during making new window " + exc);
        }
    }

    
    public static Button makeEditRoomsButton(){
        edit_rooms = new Button("Edytuj");
        edit_rooms.setMaxSize(120, 10);
        
        return edit_rooms;
    }
    
    public static Button makeEditBookingsButton(){
        edit_bookings = new Button("Edytuj");
        edit_bookings.setMaxSize(100, 10);
        
        return edit_bookings;
    }
    
    public static Button makeEditGuestsButton(){
        edit_guests = new Button("Edytuj");
        edit_guests.setMaxSize(180, 10);
        
        return edit_guests;
    }
    
    public void initOffer_Tables() {

        offer_number.setCellValueFactory(new PropertyValueFactory<>("Number_offer"));
        offer_floor.setCellValueFactory(new PropertyValueFactory<>("Floor_offer"));
        offer_type.setCellValueFactory(new PropertyValueFactory<>("Type_offer"));
        offer_standard.setCellValueFactory(new PropertyValueFactory<>("Standard_offer"));
        offer_tableview.getItems().setAll(Offer.getData());
    }

    
    public void initRooms_Table() {

        room_number.setCellValueFactory(new PropertyValueFactory<>("Number_room"));
        room_floor.setCellValueFactory(new PropertyValueFactory<>("Floor_room"));
        room_type.setCellValueFactory(new PropertyValueFactory<>("Type_room"));
        room_standard.setCellValueFactory(new PropertyValueFactory<>("Standard_room"));
        room_status.setCellValueFactory(new PropertyValueFactory<>("Status_room"));
        room_edition.setCellValueFactory(new PropertyValueFactory<>("Edition_room"));
        room_tableview.getItems().setAll(Rooms.getData());
    }
       public void initEmployee_Table() {
        employee_name.setCellValueFactory(new PropertyValueFactory<>("Name_employee"));
        employee_surname.setCellValueFactory(new PropertyValueFactory<>("Surname_employee"));
        employee_phone.setCellValueFactory(new PropertyValueFactory<>("Phone_employee"));
        employee_pesel.setCellValueFactory(new PropertyValueFactory<>("Pesel_employee"));
        employee_position.setCellValueFactory(new PropertyValueFactory<>("Position_employee"));
        employee_status.setCellValueFactory(new PropertyValueFactory<>("Status_employee"));
        employee_tableview.getItems().setAll(Employee.getData());     
    }
       
         public void initTasks_Table() {
        room_task.setCellValueFactory(new PropertyValueFactory<>("Employee_task"));
        employee_task.setCellValueFactory(new PropertyValueFactory<>("Room_task"));
        client_task.setCellValueFactory(new PropertyValueFactory<>("Client_task"));
        data_task.setCellValueFactory(new PropertyValueFactory<>("Data_task"));
        status_task.setCellValueFactory(new PropertyValueFactory<>("Status_task"));
        task_tableview.getItems().setAll(Tasks.getData());     
    }
         
          public void initBookings_Table() {
        booking_id.setCellValueFactory(new PropertyValueFactory<>("Id_booking"));
        booking_client.setCellValueFactory(new PropertyValueFactory<>("Client_booking"));
        booking_employee.setCellValueFactory(new PropertyValueFactory<>("Employee_booking"));
        booking_room.setCellValueFactory(new PropertyValueFactory<>("Room_booking"));
        booking_datap.setCellValueFactory(new PropertyValueFactory<>("Datap_booking"));
        booking_datak.setCellValueFactory(new PropertyValueFactory<>("Datak_booking"));
        booking_status.setCellValueFactory(new PropertyValueFactory<>("Status_booking"));
        booking_comment.setCellValueFactory(new PropertyValueFactory<>("Comment_booking"));
        booking_edition.setCellValueFactory(new PropertyValueFactory<>("Edition_booking"));
        booking_tableview.getItems().setAll(Bookings.getData());     
    }
          
      public void initGuests_Table() {
        guest_name.setCellValueFactory(new PropertyValueFactory<>("Name_guest"));
        guest_surname.setCellValueFactory(new PropertyValueFactory<>("Surname_guest"));
        guest_pesel.setCellValueFactory(new PropertyValueFactory<>("Phone_guest"));
        guest_phone.setCellValueFactory(new PropertyValueFactory<>("Pesel_guest"));
        guest_edition.setCellValueFactory(new PropertyValueFactory<>("Edition_guest"));
        guest_tableview.getItems().setAll(Guests.getData());     
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base = DataBase.getInstance();
        initOffer_Tables();
        initRooms_Table();
        initEmployee_Table();
        initBookings_Table();
        initGuests_Table();
        initTasks_Table();
    }

}
