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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Button add_task;
    @FXML
    private Button add_employee;
    @FXML
    private Button add_guest;

    @FXML
    private Region region;
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
    private TableView<Rooms> room_tableview;
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
    private TableColumn<?, ?> disc_task;
    @FXML
    private TableColumn<?, ?> status_task;
    @FXML
    private Button guest_offer;
    @FXML
    private Button reception_offer;
    @FXML
    private Button checkin_button;
    @FXML
    private Button checkout_button;
    @FXML
    private Button order_button;
    @FXML
    private PasswordField passfield;
    @FXML
    private TextField loginfield;
    @FXML
    private Label loginprompt;
    @FXML
    private Button login_button;
    @FXML
    private TextField recepction_taskComment;
    @FXML
    private TableView<Bookings> bookingCheckIn_tableview;
    @FXML
    private TableView<Bookings> bookingCheckOut_tableview;
    @FXML
    private TableColumn<?, ?> bookingCheckIn_id;
    @FXML
    private TableColumn<?, ?> bookingCheckIn_client;
    @FXML
    private TableColumn<?, ?> bookingCheckIn_employee;
    @FXML
    private TableColumn<?, ?> bookingCheckIn_room;
    @FXML
    private TableColumn<?, ?> bookingCheckIn_datap;
    @FXML
    private TableColumn<?, ?> bookingCheckIn_datak;
    @FXML
    private TableColumn<?, ?> bookingCheckIn_comment;
    @FXML
    private TableColumn<?, ?> bookingCheckOut_id;
    @FXML
    private TableColumn<?, ?> bookingCheckOut_client;
    @FXML
    private TableColumn<?, ?> bookingCheckOut_employee;
    @FXML
    private TableColumn<?, ?> bookingCheckOut_room;
    @FXML
    private TableColumn<?, ?> bookingCheckOut_datap;
    @FXML
    private TableColumn<?, ?> bookingCheckOut_datak;
    @FXML
    private TableColumn<?, ?> bookingCheckOut_comment;
    @FXML
    private Button rooms_edition;
    @FXML
    private Tab tab_login;
    @FXML
    private Tab tab_offer;
    @FXML
    private Tab tab_reception;
    @FXML
    private Tab tab_guest;
    @FXML
    private Tab tab_reserv;
    @FXML
    private Tab tab_rooms;
    @FXML
    private Tab tab_employee;
    @FXML
    private Tab tab_tasks;
    @FXML
    private Tab tab_reports;
    @FXML
    private Button logout_button;
    @FXML
    private Button booking_edit_button;
    @FXML
    private JFXDatePicker prolog_date;
    @FXML
    private Label prolog_info;
    @FXML
    private Button prolog_booking;
    @FXML
    private Button edit_employee;
    @FXML
    private Button delete_employee;
    @FXML
    private Button prolog_check_booking;
    @FXML
    private Button delete_task;
    @FXML
    private Button show_task;
    @FXML
    private TableColumn<?, ?> id_task;
    @FXML
    private Button edit_guest;
    @FXML
    private Button delete_guest;
    @FXML
    private TabPane maintabpane;

    //inicialize windows
    @FXML//okno informacji o aplikacji
    private void showInfoWindow(ActionEvent event) throws IOException {
        makeWindow("InfoAplication.fxml", "Informacje o aplikacji", app_info);
    }

    @FXML//okno informacji o hotelu
    private void showHotelInfoWindow(ActionEvent event) throws IOException {
        makeWindow("HotelInfo.fxml", "Informacje o hotelu", hotel_info);
    }

    @FXML//podokno wyświetlające dostępne pokoje dla gości
    private void add_offerwindow(ActionEvent event) throws IOException {
        makeWindow("ShowOffer.fxml", "Wybierz pokój idealny dla ciebie", guest_offer);
    }

    public void makeWindow(String file, String name, Button button) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource(file));
        Scene scene = new Scene(loader);
        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle(name);
        stage.initOwner(button.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void makeWindow(String file, String name, Button button, Stage stage) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource(file));
        Scene scene = new Scene(loader);
        stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle(name);
        stage.initOwner(button.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void getAlertWindow(String alert) {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setHeaderText(null);
        alert1.setContentText(alert);
        alert1.showAndWait();
    }

    public void getInformactiontWindow(String informaction) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText(null);
        alert1.setContentText(informaction);
        alert1.showAndWait();
    }

    public Bookings getCheckInSeleted() {
        return bookingCheckIn_tableview.getSelectionModel().getSelectedItem();
    }

    public boolean checkRoomReady() {
        String status = getCheckInSeleted().getRoom_booking();
        return ObjectManager.GetInstance().roomservice.checkRoomStatus(status);
    }

    public Bookings getCheckOutSeleted() {
        return bookingCheckOut_tableview.getSelectionModel().getSelectedItem();
    }

    public Employee getEmployeeTableSeleted() {
        return employee_tableview.getSelectionModel().getSelectedItem();
    }

    public Rooms getRoomTableSeleted() {
        return room_tableview.getSelectionModel().getSelectedItem();
    }

    public Tasks getTaskTableSeleted() {
        return task_tableview.getSelectionModel().getSelectedItem();
    }

    public Guests getGuestTableSeleted() {
        return guest_tableview.getSelectionModel().getSelectedItem();
    }

    private void makeEditEmployeeWindow(String employeeName, String employeeSurname,
            String employeePesel, String employeePhone, String employeePosition) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("Employee_edit.fxml").openStream());
            Employee_editController controller = (Employee_editController) loader.getController();
            controller.addEmployeeData(employeeName, employeeSurname, employeePesel, employeePhone, employeePosition);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Edytuj pracownika");
            stage.initOwner(edit_employee.getScene().getWindow());
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

    private void makeEditRoomsWindow(int roomNumber, String roomFloor,
            String roomType, String roomStan, String roomStatus) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("Rooms_edit.fxml").openStream());
            Rooms_editController controller = (Rooms_editController) loader.getController();
            controller.addRoomsData(roomNumber, roomFloor, roomType, roomStan, roomStatus);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Edytuj");
            stage.initOwner(rooms_edition.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    refreshRoomTable();
                }
            });
            stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (IOException exc) {
            System.out.print(" Error during making new window " + exc);
        }
    }

    private void makeEditGuestWindow(String guestName, String guestSurname, String guestPesel, String guestPhone) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("Guest_edit.fxml").openStream());
            Guest_editController controller = (Guest_editController) loader.getController();
            controller.addGuestData(guestName, guestSurname, guestPesel, guestPhone);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Edytuj goscia");
            stage.initOwner(edit_guest.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    refreshGuestTable();
                }
            });
            stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (IOException exc) {
            System.out.print(" Error during making new window " + exc);
        }
    }

    @FXML//podokno edytuj pracownika
    private void editEmployeeWindow(ActionEvent event) throws IOException {
        if (getEmployeeTableSeleted() != null) {
            String employeeName = getEmployeeTableSeleted().getName_employee();
            String employeeSurname = getEmployeeTableSeleted().getSurname_employee();
            String employeePesel = getEmployeeTableSeleted().getPesel_employee();
            String employeePhone = getEmployeeTableSeleted().getPhone_employee();
            String employeePosition = getEmployeeTableSeleted().getPosition_employee();
            makeEditEmployeeWindow(employeeName, employeeSurname, employeePesel, employeePhone, employeePosition);
        } else {
            getAlertWindow("Zaznacz pracownika");
        }
    }

    @FXML//podokno edytuj pokoj
    private void editRoomsWindow(ActionEvent event) throws IOException {
        if (getRoomTableSeleted() != null) {
            int roomNumber = getRoomTableSeleted().getNumber_room();
            String roomFloor = getRoomTableSeleted().getFloor_room();
            String roomType = getRoomTableSeleted().getType_room();
            String roomStan = getRoomTableSeleted().getStandard_room();
            String roomStatus = getRoomTableSeleted().getStatus_room();
            if (roomStatus.compareTo("0") == 0) {
                makeEditRoomsWindow(roomNumber, roomFloor, roomType, roomStan, roomStatus);
            } else {
                getAlertWindow("Pokój nie jest gotowy");
            }
        } else {
            getAlertWindow("Wybierz pokoj");
        }
    }

    @FXML//podokno edytuj goscia
    private void editGuestWindow(ActionEvent event) throws IOException {
        if (getGuestTableSeleted() != null) {
        String guestName = getGuestTableSeleted().getName_guest();
        String guestSurname = getGuestTableSeleted().getSurname_guest();
        String guestPesel = getGuestTableSeleted().getPesel_guest();
        String guestPhone = getGuestTableSeleted().getPhone_guest();
            makeEditGuestWindow(guestName, guestSurname, guestPesel, guestPhone);
        } else {
            getAlertWindow("Wybierz gościa");
        }
    }

    @FXML//podokno dodaj pracownika
    private void add_employeewindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        makeWindow("EmployeeAdd.fxml", "Dodaj pracownika", add_employee, stage);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                refreshEmployeeTable();
            }
        });
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML//podokno dodawania rezerwacji
    private void add_ReceptionOfferWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        makeWindow("BookingAdd.fxml", "Dodaj rezerwację", reception_offer, stage);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                refreshBookingTable();
                refreshRoomTable();
                refreshGuestTable();
                refreshReceptionTable();
            }
        });
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML//podokno edytuj rezerwację
    private void editBookingWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        makeWindow("Booking_edit.fxml", "Edytuj rezerwację", booking_edit_button, stage);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                refreshBookingTable();
                refreshReceptionTable();
            }
        });
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML//podokno edycja zadań
    private void addTaskWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        makeWindow("TasksAdd.fxml", "Dodaj zadanie", add_task, stage);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                refreshTaskTable();
                refreshRoomTable();
            }
        });
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML//podokno wysietlania zadań dla pracowników
    private void showTaskWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        makeWindow("Tasks_show.fxml", "Edytuj swoje zadania", show_task, stage);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                refreshTaskTable();
                refreshRoomTable();
            }
        });
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML//podokno dodaj goscia
    private void addGuestWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        makeWindow("Guest_add.fxml", "Dodaj goscia", add_guest, stage);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                refreshGuestTable();
            }
        });
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

//Methods
    @FXML//metoda usuń pracownika
    private void cancelEmployee(ActionEvent event) {;
        if (getEmployeeTableSeleted() != null) {
            String pesel = getEmployeeTableSeleted().getPesel_employee();
            ObjectManager.GetInstance().employeeservice.deleteEmployee(pesel);
            refreshEmployeeTable();
        } else {
            getAlertWindow("Wybierz pracownika");
        }
    }

    @FXML//metoda usuń goscia
    private void cancelGuest(ActionEvent event) {
        if (getGuestTableSeleted() != null) {
            String usunPoPesel = getGuestTableSeleted().getPesel_guest();
            ObjectManager.GetInstance().guestservice.deleteGuest(usunPoPesel);
            refreshGuestTable();
        } else {
            getAlertWindow("Wybierz goscia");
        }
    }

    @FXML//melodowanie gościa
    private void addCheckin(ActionEvent event) throws SQLException {
        if (getCheckInSeleted() == null) {
            getAlertWindow("Wybierz pokój");
        } else {
            int bookingId = getCheckInSeleted().getId_booking();
            String room = getCheckInSeleted().getRoom_booking();
            if (checkRoomReady()) {
                if (ObjectManager.GetInstance().bookingservice.updateCheckin(bookingId)
                        && ObjectManager.GetInstance().roomservice.updateRoomStatus(room, "1")) {
                    getInformactiontWindow("Zameldowano");
                    refreshBookingTable();
                    refreshReceptionTable();
                    refreshRoomTable();
                } else {
                    getAlertWindow("Błąd przy dodawaniu meldowania");
                }
            } else {
                getAlertWindow("Pokój jest jeszcze niegotowy");
                refreshBookingTable();
                refreshReceptionTable();
            }
        }
    }

    @FXML//wymeldowywanie gościa
    private void addCheckout(ActionEvent event) throws SQLException {
        if (getCheckOutSeleted() == null) {
            getAlertWindow("Wybierz pokój");
        } else {
            int id = getCheckOutSeleted().getId_booking();
            String room = getCheckOutSeleted().getRoom_booking();
            if (ObjectManager.GetInstance().bookingservice.updateCheckout(id)
                    && ObjectManager.GetInstance().roomservice.updateRoomStatus(room, "2")
                    && ObjectManager.GetInstance().taskservice.insertTask(id, "sprzatanie")
                    && ObjectManager.GetInstance().taskservice.insertList()) {
                getAlertWindow("Wymeldowano");
                refreshRoomTable();
                refreshBookingTable();
                refreshReceptionTable();
                refreshTaskTable();
            } else {
                getAlertWindow("Błąd przy wymeldowywaniu");
            }
        }
    }

    @FXML//dodawanie zadania z recepcji
    private void addRecepctionTask(ActionEvent event) throws SQLException {
        String comment = recepction_taskComment.getText();
        if (getCheckOutSeleted() == null) {
            getAlertWindow("Wybierz pokój");
        } else {
            int id = getCheckOutSeleted().getId_booking();
            if (ObjectManager.GetInstance().taskservice.insertTask(id, comment)
                    && ObjectManager.GetInstance().taskservice.insertList()) {
                getAlertWindow("Dodano zadanie");
                refreshTaskTable();
            } else {
                getAlertWindow("Błąd przy dodawaniu zadania");
            }
        }
    }

    @FXML//sprawdzanie możliwości przedłużenia rezerwacji
    private void checkBookingProlog(ActionEvent event) throws SQLException {
        if (getCheckOutSeleted() == null) {
            getAlertWindow("Wybierz pokój");
        } else {
            String prolongDateEnd = prolog_date.getValue().toString();
            String prolongDateStart = bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getDatak_booking();
            if (prolongDateEnd.compareTo(prolongDateStart) > 0) {
                String roomNumber = bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getRoom_booking();
                if (ObjectManager.GetInstance().offerservice.getPrologData(roomNumber, prolongDateStart, prolongDateEnd)) {
                    prolog_info.setText("Dostępny");
                } else {
                    prolog_info.setText("Niedostępny");
                }
            } else {
                getAlertWindow("Zła data");
            }
        }
    }

    @FXML//przedłużanie rezerwacji
    private void prologBooking(ActionEvent event) throws SQLException {
        String prolongDate = prolog_date.getValue().toString();
        if (getCheckOutSeleted() == null || prolongDate == null) {
            getAlertWindow("Wybierz pokój i datę");
        } else {
            String roomNumber = bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getRoom_booking();
            String startDate=bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getDatak_booking();
            String endDate= prolog_date.getValue().toString();
            if (ObjectManager.GetInstance().offerservice.getPrologData(roomNumber,startDate,endDate)) {
                int id=bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getId_booking();
                String newEndDate=prolog_date.getValue().toString();
                ObjectManager.GetInstance().bookingservice.prologBooking(id,newEndDate);
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Przedłużono rezerwację");
                alert4.showAndWait();
                refreshReceptionTable();
                refreshBookingTable();

            } else {
                getAlertWindow("Błąd przy przedłużaniu rezerwacji");
            }
        }
    }

    @FXML//metoda usuń zadanie
    private void cancelTask(ActionEvent event) {
        if (getTaskTableSeleted()!= null) {
            String room = task_tableview.getSelectionModel().getSelectedItem().getRoom_task();
            int id=task_tableview.getSelectionModel().getSelectedItem().getId_task();
            if (ObjectManager.GetInstance().taskservice.deleteTask(id)) {
                getAlertWindow("Usunięto zadanie");
                refreshTaskTable();
                if (ObjectManager.GetInstance().taskservice.checkRoomReady(Integer.parseInt(room))) {
                    ObjectManager.GetInstance().roomservice.updateRoomStatus(room, "0");
                    refreshRoomTable();
                }
            }
        } else {
            getAlertWindow("Wybierz zadanie");
        }

    }

    //inicjalizacja tabel
    public void initRooms_Table() {
        room_number.setCellValueFactory(new PropertyValueFactory<>("Number_room"));
        room_floor.setCellValueFactory(new PropertyValueFactory<>("Floor_room"));
        room_type.setCellValueFactory(new PropertyValueFactory<>("Type_room"));
        room_standard.setCellValueFactory(new PropertyValueFactory<>("Standard_room"));
        room_status.setCellValueFactory(new PropertyValueFactory<>("Status_room"));
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
        disc_task.setCellValueFactory(new PropertyValueFactory<>("Discription_task"));
        status_task.setCellValueFactory(new PropertyValueFactory<>("Status_task"));
        id_task.setCellValueFactory(new PropertyValueFactory<>("Id_task"));
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
        booking_tableview.getItems().setAll(ObjectManager.GetInstance().bookingservice.getData());
    }

    public void initBookingsCheckIn_Table() {
        bookingCheckIn_id.setCellValueFactory(new PropertyValueFactory<>("Id_booking"));
        bookingCheckIn_client.setCellValueFactory(new PropertyValueFactory<>("Client_booking"));
        bookingCheckIn_employee.setCellValueFactory(new PropertyValueFactory<>("Employee_booking"));
        bookingCheckIn_room.setCellValueFactory(new PropertyValueFactory<>("Room_booking"));
        bookingCheckIn_datap.setCellValueFactory(new PropertyValueFactory<>("Datap_booking"));
        bookingCheckIn_datak.setCellValueFactory(new PropertyValueFactory<>("Datak_booking"));
        bookingCheckIn_comment.setCellValueFactory(new PropertyValueFactory<>("Comment_booking"));
        bookingCheckIn_tableview.getItems().setAll(ObjectManager.GetInstance().bookingservice.getBookingCheckIn());
    }

    public void initBookingsCheckOut_Table() {
        bookingCheckOut_id.setCellValueFactory(new PropertyValueFactory<>("Id_booking"));
        bookingCheckOut_client.setCellValueFactory(new PropertyValueFactory<>("Client_booking"));
        bookingCheckOut_employee.setCellValueFactory(new PropertyValueFactory<>("Employee_booking"));
        bookingCheckOut_room.setCellValueFactory(new PropertyValueFactory<>("Room_booking"));
        bookingCheckOut_datap.setCellValueFactory(new PropertyValueFactory<>("Datap_booking"));
        bookingCheckOut_datak.setCellValueFactory(new PropertyValueFactory<>("Datak_booking"));
        bookingCheckOut_comment.setCellValueFactory(new PropertyValueFactory<>("Comment_booking"));
        bookingCheckOut_tableview.getItems().setAll(ObjectManager.GetInstance().bookingservice.getBookingCheckOut());
    }

    public void initGuests_Table() {
        guest_name.setCellValueFactory(new PropertyValueFactory<>("Name_guest"));
        guest_surname.setCellValueFactory(new PropertyValueFactory<>("Surname_guest"));
        guest_phone.setCellValueFactory(new PropertyValueFactory<>("Phone_guest"));
        guest_pesel.setCellValueFactory(new PropertyValueFactory<>("Pesel_guest"));
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

    public void refreshReceptionTable() {
        bookingCheckIn_tableview.getItems().setAll(ObjectManager.GetInstance().bookingservice.getBookingCheckIn());
        bookingCheckOut_tableview.getItems().setAll(ObjectManager.GetInstance().bookingservice.getBookingCheckOut());
    }

    @FXML
    public void logoutsequence(ActionEvent event) {
        tab_lock(-1);
        ObjectManager.GetInstance().loginservice.logout();
    }

    @FXML
    public void loginsequence(ActionEvent event) throws SQLException {
        try {
            String loginVar = loginfield.getText();
            String passVar = passfield.getText();
            ObjectManager.GetInstance().loginservice.getlogged(loginVar, passVar);

            System.out.println(ObjectManager.GetInstance().loginservice.employeeSessionId);
        } catch (SQLException e) {
            loginprompt.setText("zly login lub haslo");
        }

        if (ObjectManager.GetInstance().loginservice.permissions == null) {
            loginprompt.setText("zly login lub haslo");
        } else {
            int permission_level = Integer.parseInt(ObjectManager.GetInstance().loginservice.permissions);
            tab_lock(permission_level);
            loginprompt.setText("");

            ObjectManager.GetInstance().employeeservice.changeEmployeeStatus(ObjectManager.GetInstance().loginservice.employeeSessionId);
        }
    }

    public void tab_lock(int permission_level) {
        switch (permission_level) {
            case 0:
                edit_employee.setDisable(false);
            case 1:
                tab_reports.setDisable(false);
                tab_employee.setDisable(false);
            case 2:
                tab_guest.setDisable(false);
                tab_reserv.setDisable(false);
                tab_reception.setDisable(false);
            case 3:
                tab_rooms.setDisable(false);
                tab_tasks.setDisable(false);
                tab_login.setDisable(true);
                logout_button.setDisable(false);
                maintabpane.getSelectionModel().select(tab_offer);
                break;
            default:
                tab_reports.setDisable(true);
                tab_employee.setDisable(true);
                tab_guest.setDisable(true);
                tab_reserv.setDisable(true);
                tab_reception.setDisable(true);
                tab_rooms.setDisable(true);
                tab_tasks.setDisable(true);
                tab_login.setDisable(false);
                logout_button.setDisable(true);
                edit_employee.setDisable(true);
                maintabpane.getSelectionModel().select(tab_login);
        }
    }

    @FXML
    public void openOffer(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base = DataBase.getInstance();
        //ObjectManager.GetInstance().loginservice.makeStartBase();
        ObjectManager.GetInstance().loginservice.makeStartQuery();
        //System.console().printf("Błąd");
        initRooms_Table();
        initEmployee_Table();
        initBookings_Table();
        initGuests_Table();
        initTasks_Table();
        initBookingsCheckIn_Table();
        initBookingsCheckOut_Table();
        System.out.println(LocalDateTime.now());
        bookingCheckOut_tableview.getSelectionModel().selectedItemProperty().
                addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        String comment = bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getComment_booking();
                        recepction_taskComment.setText(comment);
                    }
                });

        tab_lock(-1);
        ObjectManager.GetInstance().loginservice.logout();
    }
}
