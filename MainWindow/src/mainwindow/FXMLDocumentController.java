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
    private Button guests_edition;
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

    @FXML//podokno dodaj pracownika
    private void add_employeewindow(ActionEvent event) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("EmployeeAdd.fxml"));
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

    @FXML//podokno edytuj pracownika
    private void editEmployeeWindow(ActionEvent event) throws IOException {
        if (employee_tableview.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("Employee_edit.fxml").openStream());
                Employee_editController controller = (Employee_editController) loader.getController();
                controller.addEmployeeData(employee_tableview.getSelectionModel().getSelectedItem().getName_employee(),
                        employee_tableview.getSelectionModel().getSelectedItem().getSurname_employee(),
                        employee_tableview.getSelectionModel().getSelectedItem().getPesel_employee(),
                        employee_tableview.getSelectionModel().getSelectedItem().getPhone_employee(),
                        employee_tableview.getSelectionModel().getSelectedItem().getPosition_employee());
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
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pracownika");
            alert1.showAndWait();
        }
    }
    @FXML//podokno edytuj pokoj
    private void editRoomsWindow(ActionEvent event) throws IOException {
        if (room_tableview.getSelectionModel().getSelectedItem() != null) {
            if(room_tableview.getSelectionModel().getSelectedItem().getStatus_room().compareTo("0")==0){
            try {
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("Rooms_edit.fxml").openStream());
                Rooms_editController controller = (Rooms_editController) loader.getController();
                controller.addRoomsData(room_tableview.getSelectionModel().getSelectedItem().getNumber_room(),
                        room_tableview.getSelectionModel().getSelectedItem().getFloor_room(),
                        room_tableview.getSelectionModel().getSelectedItem().getType_room(),
                        room_tableview.getSelectionModel().getSelectedItem().getStandard_room(),
                        room_tableview.getSelectionModel().getSelectedItem().getStatus_room());
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
            } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Pokój nie jest gotowy");
            alert1.showAndWait();
        }
            
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pokoj");
            alert1.showAndWait();
        }
    }

    @FXML//podokno dodawania rezerwacji
    private void add_ReceptionOfferWindow(ActionEvent event) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("BookingAdd.fxml"));
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
                    refreshGuestTable();
                    refreshReceptionTable();
                }
            });
            stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (IOException exc) {
            System.out.print(" Error during making new window " + exc);
        }
    }

    @FXML//podokno edytuj rezerwację
    private void editBookingWindow(ActionEvent event) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Booking_edit.fxml"));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Edytuj rezerwację");
            stage.initOwner(booking_edit_button.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    refreshBookingTable();
                    refreshReceptionTable();
                }
            });
            stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (IOException exc) {
            System.out.print(" Error during making new window " + exc);
        }
    }

    @FXML//podokno edycja zadań
    private void addTaskWindow(ActionEvent event) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("TasksAdd.fxml"));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Dodaj zadanie");
            stage.initOwner(add_task.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    refreshTaskTable();
                    refreshRoomTable();
                }
            });
            stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (IOException exc) {
            System.out.print(" Error during making new window " + exc);
        }
    }
    
    @FXML//podokno wysietlania zadań dla pracowników
    private void showTaskWindow(ActionEvent event) throws IOException {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Tasks_show.fxml"));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Edytuj swoje zadania");
            stage.initOwner(show_task.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    refreshTaskTable();
                    refreshRoomTable();
                }
            });
            stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (IOException exc) {
            System.out.print(" Error during making new window " + exc);
        }
    }


//Methods
    @FXML//metoda usuń pracownika
    private void cancelEmployee(ActionEvent event) {
        if (employee_tableview.getSelectionModel().getSelectedItem() != null) {
            ObjectManager.GetInstance().employeeservice.deleteEmployee(
                    employee_tableview.getSelectionModel().getSelectedItem().getPesel_employee());
            refreshEmployeeTable();
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pracownika");
            alert1.showAndWait();
        }

    }

    @FXML//melodowanie gościa
    private void addCheckin(ActionEvent event) throws SQLException {
        if (bookingCheckIn_tableview.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pokój");
            alert1.showAndWait();
        } else {
            if (ObjectManager.GetInstance().roomservice.checkRoomStatus(
                    bookingCheckIn_tableview.getSelectionModel().getSelectedItem()
                            .getRoom_booking())) {
                if (ObjectManager.GetInstance().bookingservice.updateCheckin(
                        bookingCheckIn_tableview.getSelectionModel().getSelectedItem().getId_booking())
                        && ObjectManager.GetInstance().roomservice.updateRoomStatus(
                                bookingCheckIn_tableview.getSelectionModel().getSelectedItem().getRoom_booking(), "1")) {
                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert4.setHeaderText(null);
                    alert4.setContentText("Zameldowano");
                    alert4.showAndWait();
                    refreshBookingTable();
                    refreshReceptionTable();
                    refreshRoomTable();
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setHeaderText(null);
                    alert2.setContentText("Błąd przy dodawaniu meldowania");
                    alert2.showAndWait();
                }
            } else {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Pokój jest jeszcze nie gotowy");
                alert4.showAndWait();
                refreshBookingTable();
                refreshReceptionTable();
            }
        }
    }

    @FXML//wymeldowywanie gościa
    private void addCheckout(ActionEvent event) throws SQLException {
        if (bookingCheckOut_tableview.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pokój");
            alert1.showAndWait();

        } else {
            if (ObjectManager.GetInstance().bookingservice.updateCheckout(
                    bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getId_booking())
                    && ObjectManager.GetInstance().roomservice.updateRoomStatus(
                            bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getRoom_booking(), "2")
                    && ObjectManager.GetInstance().taskservice.insertTask(
                            bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getId_booking(), "sprzatanie")
                    && ObjectManager.GetInstance().taskservice.insertList()) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Wymeldowano");
                alert4.showAndWait();
                refreshRoomTable();
                refreshBookingTable();
                refreshReceptionTable();
                refreshTaskTable();
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
        if (bookingCheckOut_tableview.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pokój");
            alert1.showAndWait();
        } else {
            if (ObjectManager.GetInstance().taskservice.insertTask(
                    bookingCheckOut_tableview.getSelectionModel().getSelectedItem()
                            .getId_booking(), recepction_taskComment.getText())
                    && ObjectManager.GetInstance().taskservice.insertList()) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Dodano zadanie");
                alert4.showAndWait();
                refreshTaskTable();

            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy dodawaniu zadania");
                alert2.showAndWait();
            }
        }
    }

    @FXML//sprawdzanie możliwości przedłużenia rezerwacji
    private void checkBookingProlog(ActionEvent event) throws SQLException {
        if (bookingCheckOut_tableview.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pokój");
            alert1.showAndWait();
        } else {
            if (prolog_date.getValue().toString().compareTo(bookingCheckOut_tableview.
                    getSelectionModel().getSelectedItem().getDatak_booking()) > 0) {
                if (ObjectManager.GetInstance().offerservice.getPrologData(
                        bookingCheckOut_tableview.getSelectionModel().getSelectedItem()
                                .getRoom_booking(), bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getDatak_booking(),
                        prolog_date.getValue().toString())) {
                    prolog_info.setText("Dostępny");
                } else {
                    prolog_info.setText("Niedostępny");
                }
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Zła data");
                alert2.showAndWait();
            }
        }
    }

    @FXML//przedłużanie rezerwacji
    private void prologBooking(ActionEvent event) throws SQLException {
        if (bookingCheckOut_tableview.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz pokój");
            alert1.showAndWait();
        } else {
            if (ObjectManager.GetInstance().offerservice.getPrologData(
                    bookingCheckOut_tableview.getSelectionModel().getSelectedItem()
                            .getRoom_booking(), bookingCheckOut_tableview.getSelectionModel().getSelectedItem().getDatak_booking(),
                    prolog_date.getValue().toString())) {
                ObjectManager.GetInstance().bookingservice.prologBooking(bookingCheckOut_tableview.
                        getSelectionModel().getSelectedItem().getId_booking(), prolog_date.getValue().toString());
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Przedłużono rezerwację");
                alert4.showAndWait();
                refreshReceptionTable();
                refreshBookingTable();

            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy przedłużaniu rezerwacji");
                alert2.showAndWait();
            }
        }
    }

    @FXML//metoda usuń zadanie
    private void cancelTask(ActionEvent event) {
        if (task_tableview.getSelectionModel().getSelectedItem() != null) {
            String room= task_tableview.getSelectionModel().getSelectedItem().getRoom_task();
            if(ObjectManager.GetInstance().taskservice.deleteTask(
                    task_tableview.getSelectionModel().getSelectedItem().getId_task())){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Usunięto zadanie");
            alert1.showAndWait();
            refreshTaskTable();
            if (ObjectManager.GetInstance().taskservice.checkRoomReady(
                    Integer.parseInt(room))) {
                ObjectManager.GetInstance().roomservice.updateRoomStatus(
                        room, "0");
                refreshRoomTable();
            }
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz zadanie");
            alert1.showAndWait();
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

        int permission_level = Integer.parseInt(ObjectManager.GetInstance().loginservice.permissions);
        tab_lock(permission_level);
    }

    public void tab_lock(int permission_level) {
        switch (permission_level) {
            case 0:
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
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base = DataBase.getInstance();
        initRooms_Table();
        initEmployee_Table();
        initBookings_Table();
        initGuests_Table();
        initTasks_Table();
        initBookingsCheckIn_Table();
        initBookingsCheckOut_Table();
        tab_lock(-1);
        ObjectManager.GetInstance().loginservice.logout();
    }
}
