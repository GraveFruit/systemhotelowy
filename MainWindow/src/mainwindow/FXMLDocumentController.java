/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import base.service.BookingService;
import base.service.EmployeeService;
import base.service.GuestService;
import base.service.RoomService;
import base.service.TaskService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import hotel.base.Bookings;
import hotel.base.DataBase;
import hotel.base.Employee;
import hotel.base.Guests;
import hotel.base.Rooms;
import hotel.base.Offer;
import hotel.base.Tasks;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.Date;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;

/**
 *
 * @author Grzesiek
 */
public class FXMLDocumentController implements Initializable {

    DataBase base;

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
    @FXML
    private Button guest_offer;
    @FXML
    private Button reception_offer;
    @FXML
    private Tab offer_tab;
    @FXML
    private Button add_guests;
    @FXML
    private TextField client_name;
    @FXML
    private TextField client_surname;
    @FXML
    private TextField client_pesel;
    @FXML
    private TextField client_phone;
    @FXML
    private Button checkin_button;
    @FXML
    private Button checkout_button;
    @FXML
    private JFXComboBox<String> recepction_checkinbox;
    @FXML
    private JFXComboBox<String> recepction_checkoutbox;
    @FXML
    private JFXComboBox<String> recepction_taskbox;
    @FXML
    private Button order_button;
    @FXML
    private PasswordField passfield;
    @FXML
    private TextField loginfield;
    @FXML
    private Label loginprompt;

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

    @FXML//podokno wyświetlające dostępne pokoje dla gości
    private void add_offerwindow(ActionEvent event) throws IOException {
        makeWindow("ShowOffer.fxml", "Wybierz pokój idealny dla ciebie", guest_offer);
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

    @FXML//podokno dodawania rezerwacji
    private void add_ReceptionOfferWindow(ActionEvent event) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("booking_add.fxml"));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Dodaj rezerwację");
            stage.initOwner(reception_offer.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    refreshBookingTable();
                    refreshRoomTable();
                    initBookingBoxes();
                }
            });
            stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (IOException exc) {
            System.out.print(" Error during making new window " + exc);
        }
    }

    @FXML//podokno dodaj pracownika
    private void add_employeewindow(ActionEvent event) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("employee_add.fxml"));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Dodaj pracownika");
            stage.initOwner(add_employee.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    refreshEmployeeTable();
                }
            });
            stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (IOException exc) {
            System.out.print(" Error during making new window " + exc);
        }
    }

    @FXML//dodawanie klienta
    private void addClient(ActionEvent event) throws SQLException {
        String name_c = client_name.getText();
        String surname_c = client_surname.getText();
        String phone_c = client_phone.getText();
        String pesel_c = client_pesel.getText();
        
       if (name_c.isEmpty() || surname_c.isEmpty() || phone_c.isEmpty() || pesel_c.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wypełnij wszystkie pola");
            alert1.showAndWait();
        }else if(!name_c.matches("^[\\p{L} .'-]+$") || !surname_c.matches("^[\\p{L} .'-]+$")){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setHeaderText(null);
            alert2.setContentText("Błędne imię lub nazwisko");
            alert2.showAndWait();
        }else if(!pesel_c.matches("[0-9]{11}")){
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setHeaderText(null);
            alert3.setContentText("Błędny pesel");
            alert3.showAndWait();
        }else if(!phone_c.matches("^[0-9]{7,15}$")){
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setHeaderText(null);
            alert3.setContentText("Błędny telefon");
            alert3.showAndWait();
        }else {
            if (ObjectManager.GetInstance().guestservice.insertClient(name_c, surname_c, phone_c, pesel_c)) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Dodano klienta");
                alert4.showAndWait();
                client_name.clear();
                client_surname.clear();
                client_phone.clear();
                client_pesel.clear();
                refreshGuestTable();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy dodawaniu klienta");
                alert2.showAndWait();
            }
        }
    }

    @FXML//melodowanie gościa
    private void addCheckin(ActionEvent event) throws SQLException {
        if (recepction_checkinbox.getValue()==null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pokój");
            alert1.showAndWait();

        } else {
            if (ObjectManager.GetInstance().bookingservice.updateCheckin(recepction_checkinbox.getValue())) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Zameldowano");
                alert4.showAndWait();
                initBookingBoxes();
                refreshBookingTable();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy dodawaniu meldowaniu");
                alert2.showAndWait();
            }
        }
    }
    @FXML//wymeldowywanie gościa
    private void addCheckout(ActionEvent event) throws SQLException {
        String room =recepction_checkoutbox.getValue();
        if (recepction_checkoutbox.getValue()==null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pokój");
            alert1.showAndWait();

        } else {
            if (ObjectManager.GetInstance().bookingservice.updateCheckout(room)) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Wymeldowano");
                alert4.showAndWait();
                refreshBookingTable();
                initBookingBoxes();
                
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy wymeldowywaniu");
                alert2.showAndWait();
            }
        }
    }

    @FXML//dodawanie zadania z recepcji
    private void addRecepctionTask(ActionEvent event) throws SQLException {
        String room =recepction_taskbox.getValue();
        if (recepction_taskbox.getValue()==null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pokój");
            alert1.showAndWait();
        } else {
            if (ObjectManager.GetInstance().taskservice.insertTask(room)) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Dodano zadanie");
                alert4.showAndWait();
                refreshGuestTable();
                
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy dodawaniu zadania");
                alert2.showAndWait();
            }
        }
    }
    
    public void initBookingBoxes() {
        ObservableList<String> recepction_checkin_list = ObjectManager.GetInstance().bookingservice.getBookingCheckin();
        recepction_checkinbox.setItems(recepction_checkin_list);
        ObservableList<String> recepction_checkout_list = ObjectManager.GetInstance().bookingservice.getBookingCheckout();
        recepction_checkoutbox.setItems(recepction_checkout_list);
        ObservableList<String> recepction_task_list = ObjectManager.GetInstance().bookingservice.getBookingCheckout();
        recepction_taskbox.setItems(recepction_task_list);
    }

    //inicjalizacja tabel
    public void initRooms_Table() {

        room_number.setCellValueFactory(new PropertyValueFactory<>("Number_room"));
        room_floor.setCellValueFactory(new PropertyValueFactory<>("Floor_room"));
        room_type.setCellValueFactory(new PropertyValueFactory<>("Type_room"));
        room_standard.setCellValueFactory(new PropertyValueFactory<>("Standard_room"));
        room_status.setCellValueFactory(new PropertyValueFactory<>("Status_room"));
        room_edition.setCellValueFactory(new PropertyValueFactory<>("Edition_room"));
        room_tableview.getItems().setAll(ObjectManager.GetInstance().roomservice.getData());
    }

    public void initEmployee_Table() {
        employee_name.setCellValueFactory(new PropertyValueFactory<>("Name_employee"));
        employee_surname.setCellValueFactory(new PropertyValueFactory<>("Surname_employee"));
        employee_phone.setCellValueFactory(new PropertyValueFactory<>("Phone_employee"));
        employee_pesel.setCellValueFactory(new PropertyValueFactory<>("Pesel_employee"));
        employee_position.setCellValueFactory(new PropertyValueFactory<>("Position_employee"));
        employee_status.setCellValueFactory(new PropertyValueFactory<>("Status_employee"));
        employee_tableview.getItems().setAll(ObjectManager.GetInstance().employeeservice.getData());
    }

    public void initTasks_Table() {
        room_task.setCellValueFactory(new PropertyValueFactory<>("Room_task"));
        employee_task.setCellValueFactory(new PropertyValueFactory<>("Employee_task"));
        client_task.setCellValueFactory(new PropertyValueFactory<>("Client_task"));
        data_task.setCellValueFactory(new PropertyValueFactory<>("Data_task"));
        status_task.setCellValueFactory(new PropertyValueFactory<>("Status_task"));
        task_tableview.getItems().setAll(ObjectManager.GetInstance().taskservice.getData());
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
        booking_tableview.getItems().setAll(ObjectManager.GetInstance().bookingservice.getData());
    }

    public void initGuests_Table() {
        guest_name.setCellValueFactory(new PropertyValueFactory<>("Name_guest"));
        guest_surname.setCellValueFactory(new PropertyValueFactory<>("Surname_guest"));
        guest_pesel.setCellValueFactory(new PropertyValueFactory<>("Phone_guest"));
        guest_phone.setCellValueFactory(new PropertyValueFactory<>("Pesel_guest"));
        guest_edition.setCellValueFactory(new PropertyValueFactory<>("Edition_guest"));
        guest_tableview.getItems().setAll(ObjectManager.GetInstance().guestservice.getData());
    }

    public void refreshEmployeeTable() {
        employee_tableview.getItems().setAll(ObjectManager.GetInstance().employeeservice.getData());
    }

    public void refreshBookingTable() {
        booking_tableview.getItems().setAll(ObjectManager.GetInstance().bookingservice.getData());
    }

    public void refreshRoomTable() {
        room_tableview.getItems().setAll(ObjectManager.GetInstance().roomservice.getData());
    }

    public void refreshGuestTable() {
        guest_tableview.getItems().setAll(ObjectManager.GetInstance().guestservice.getData());
    }
    
    public void refreshTaskTable() {
        task_tableview.getItems().setAll(ObjectManager.GetInstance().taskservice.getData());
    }
    
    public void loginsequence(ActionEvent event) throws SQLException{
        String loginVar = loginfield.getText();
        String passVar = passfield.getText();
        loginprompt.setText(ObjectManager.GetInstance().loginservice.employeeSessionId);
        ObjectManager.GetInstance().loginservice.getlogged(loginVar, passVar);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base = DataBase.getInstance();
        initRooms_Table();
        initEmployee_Table();
        initBookings_Table();
        initGuests_Table();
        initTasks_Table();
        initBookingBoxes();
        
}
}