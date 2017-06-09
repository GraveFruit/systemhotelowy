/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hotel.base.Bookings;
import hotel.base.DataBase;
import hotel.base.Guests;
import hotel.base.Offer;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Grzesiek
 */
public class Booking_addController implements Initializable {

    DataBase base;
    String room;
    @FXML
    private TableView<Offer> booking_tableview;
    @FXML
    private TableColumn<?, ?> booking_standard;
    @FXML
    private TableColumn<?, ?> booking_type;
    @FXML
    private TableColumn<?, ?> booking_floor;
    @FXML
    private TableColumn<?, ?> booking_number;
    @FXML
    private JFXDatePicker booking_datep;
    @FXML
    private JFXDatePicker booking_datek;
    @FXML
    private ComboBox<String> booking_standardbox;
    @FXML
    private ComboBox<String> booking_typbox;
    @FXML
    private TextField booking_comment;
    @FXML
    private Button booking_add;

    ObservableList<String> booking_typ_list = FXCollections.observableArrayList("1", "1+1", "2", "2+1", "3");
    ObservableList<String> booking_standard_list = FXCollections.observableArrayList("vip", "ekonomiczny", "biznesowy");

    @FXML
    private Button booking_offer;
    @FXML
    private Label booking_label;
    @FXML
    private TextField client_name;
    @FXML
    private TextField client_surname;
    @FXML
    private TextField client_pesel;
    @FXML
    private TextField client_phone;
    @FXML
    private Button add_guests;
    @FXML
    private TableView<Guests> client_tableview;
    @FXML
    private TableColumn<?, ?> guest_name;
    @FXML
    private TableColumn<?, ?> guest_surname;
    @FXML
    private TableColumn<?, ?> guest_pesel;
    @FXML
    private TableColumn<?, ?> guest_phone;
    @FXML
    private Label booking_label2;

    private Guests clientTableSelected() {
        return client_tableview.getSelectionModel().getSelectedItem();
    }

    private Offer bookingTableSelected() {
        return booking_tableview.getSelectionModel().getSelectedItem();
    }

    private void initGuestsBooking_Table() {
        String clientName = client_name.getText();
        String clientSurname = client_surname.getText();
        String clientPesel = client_pesel.getText();
        String clientPhone = client_phone.getText();
        guest_name.setCellValueFactory(new PropertyValueFactory<>("Name_guest"));
        guest_surname.setCellValueFactory(new PropertyValueFactory<>("Surname_guest"));
        guest_pesel.setCellValueFactory(new PropertyValueFactory<>("Pesel_guest"));
        guest_phone.setCellValueFactory(new PropertyValueFactory<>("Phone_guest"));
        client_tableview.getItems().setAll(ObjectManager.GetInstance().guestservice.getGuest_Data(
                clientName, clientSurname, clientPesel, clientPhone));
    }

    @FXML//dodawanie klienta
    private void addClient(ActionEvent event) throws SQLException {
        String name_c = client_name.getText();
        String surname_c = client_surname.getText();
        String phone_c = client_phone.getText();
        String pesel_c = client_pesel.getText();

        if (name_c.isEmpty() || surname_c.isEmpty() || phone_c.isEmpty() || pesel_c.isEmpty()) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Wypełnij wszystkie pola");
        } else if (!name_c.matches("\\p{L}") || !surname_c.matches("^[\\p{L} .'-]+$")) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błędne imię lub nazwisko");
        } else if (!pesel_c.matches("[0-9]{11}")) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błędny pesel");
        } else if (!phone_c.matches("^[0-9]{7,15}$")) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Błędny telefon");
        } else {
            if (ObjectManager.GetInstance().guestservice.insertClient(name_c, surname_c, phone_c, pesel_c)) {
                ObjectManager.GetInstance().dataservice.getInformactiontWindow("Dodano klienta");
                 initGuestsBooking_Table();
                client_name.clear();
                client_surname.clear();
                client_phone.clear();
                client_pesel.clear();
               
            } else {
                ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd przy dodawaniu klienta");
            }
        }
    }

    @FXML
    private void showOffer(ActionEvent event) {
        String datap = booking_datep.getValue().toString();
        String datak = booking_datek.getValue().toString();//new SimpleDateFormat(offer_datek);
        String typ = booking_typbox.getValue();
        String stand = booking_standardbox.getValue();
        String comment = booking_comment.getText();
        booking_number.setCellValueFactory(new PropertyValueFactory<>("Number_offer"));
        booking_floor.setCellValueFactory(new PropertyValueFactory<>("Floor_offer"));
        booking_type.setCellValueFactory(new PropertyValueFactory<>("Type_offer"));
        booking_standard.setCellValueFactory(new PropertyValueFactory<>("Standard_offer"));
        booking_tableview.getItems().setAll(ObjectManager.GetInstance().offerservice.getData(typ, stand, datap, datak));
    }

    @FXML
    private void addBooking(ActionEvent event) throws SQLException {
        int pracownik = Integer.parseInt(ObjectManager.GetInstance().loginservice.employeeSessionId);
        String datap = booking_datep.getValue().toString();
        String datak = booking_datek.getValue().toString();
        String komentarz = booking_comment.getText();
        if (clientTableSelected() == null || bookingTableSelected() == null) {
            ObjectManager.GetInstance().dataservice.getAlertWindow("Wybierz gościa i pokój");
        } else {
            String klient = clientTableSelected().getPesel_guest();
            int pokoj = bookingTableSelected().getNumber_offer();
            if (ObjectManager.GetInstance().bookingservice.insertBooking(klient, pracownik, pokoj, datap, datak, komentarz)) {
                ObjectManager.GetInstance().dataservice.getInformactiontWindow("Dodano rezerwację");
                booking_comment.clear();
                if (bookingTableSelected() != null) {
                    booking_tableview.getItems().remove(bookingTableSelected());
                }
            } else {
                ObjectManager.GetInstance().dataservice.getAlertWindow("Błąd przy dodawaniu rezerwacji");
            }
        }
    }
    
    private void initTableEvent() {
        booking_tableview.getSelectionModel().selectedItemProperty().
                addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        booking_label2.setText("is booking room "
                                + bookingTableSelected().getNumber_offer());
                    }
                });
        client_tableview.getSelectionModel().selectedItemProperty().
                addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        booking_label.setText("Guest "
                                + clientTableSelected().getName_guest()
                                + " " + clientTableSelected().getSurname_guest());
                    }
                });
    }

    private void searchGuest(TextField name) {
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    initGuestsBooking_Table();

                }
            }
        });
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base = DataBase.getInstance();
        booking_typbox.setItems(booking_typ_list);
        booking_standardbox.setItems(booking_standard_list);
        ObjectManager.GetInstance().dataservice.checkBookingsDate(booking_datep,booking_datek);
        initTableEvent();
        searchGuest(client_name);
        searchGuest(client_surname);
        searchGuest(client_pesel);
        searchGuest(client_phone);
        ObjectManager.GetInstance().dataservice.chechIsNumber(client_pesel);
        ObjectManager.GetInstance().dataservice.chechIsNumber(client_phone);
    }
}
