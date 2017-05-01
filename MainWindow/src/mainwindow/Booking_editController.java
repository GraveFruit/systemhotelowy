/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import com.jfoenix.controls.JFXDatePicker;
import hotel.base.Bookings;
import hotel.base.DataBase;
import hotel.base.Guests;
import hotel.base.Offer;
import java.net.URL;
import java.text.SimpleDateFormat;
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

/**
 * FXML Controller class
 *
 * @author Grzesiek
 */
public class Booking_editController implements Initializable {

    DataBase base;
    @FXML
    private JFXDatePicker booking_datep;
    @FXML
    private JFXDatePicker booking_datek;
    @FXML
    private ComboBox<String> booking_standardbox;
    @FXML
    private ComboBox<String> booking_typbox;
    @FXML
    private Button booking_offer;
    @FXML
    private TextField booking_comment;
    @FXML
    private Button booking_add;
    @FXML
    private TextField client_name;
    @FXML
    private TextField client_surname;
    @FXML
    private TextField client_pesel;
    @FXML
    private TextField client_phone;
    @FXML
    private TableColumn<?, ?> booking_standard;
    @FXML
    private TableView<Offer> booking_tableview;
    @FXML
    private TableColumn<?, ?> booking_id;
    @FXML
    private TableColumn<?, ?> booking_room;
    @FXML
    private TableColumn<?, ?> booking_type;
    @FXML
    private TableColumn<?, ?> booking_floor;
    @FXML
    private TableColumn<?, ?> booking_datap;
    @FXML
    private TableColumn<?, ?> booking_datak;
    @FXML
    private TableColumn<?, ?> booking_comment1;
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
    ObservableList<String> booking_typ_list = FXCollections.observableArrayList("1", "1+1", "2", "2+1", "3");
    ObservableList<String> booking_standard_list = FXCollections.observableArrayList("vip", "ekonomiczny", "biznesowy");
    @FXML
    private TableView<Offer> newbooking_tableview;
    @FXML
    private TableColumn<?, ?> newbooking_standard;
    @FXML
    private TableColumn<?, ?> newbooking_type;
    @FXML
    private TableColumn<?, ?> newbooking_floor;
    @FXML
    private TableColumn<?, ?> newbooking_number;
    @FXML
    private TableColumn<?, ?> booking_status;

    private void initGuestsBooking_Table() {
        guest_name.setCellValueFactory(new PropertyValueFactory<>("Name_guest"));
        guest_surname.setCellValueFactory(new PropertyValueFactory<>("Surname_guest"));
        guest_pesel.setCellValueFactory(new PropertyValueFactory<>("Pesel_guest"));
        guest_phone.setCellValueFactory(new PropertyValueFactory<>("Phone_guest"));
        client_tableview.getItems().setAll(ObjectManager.GetInstance().guestservice.getGuest_Data(
                client_name.getText(), client_surname.getText(),
                client_pesel.getText(), client_phone.getText()));

    }

    private void initNewBooking_Table() {
        booking_id.setCellValueFactory(new PropertyValueFactory<>("Id_booking"));
        booking_room.setCellValueFactory(new PropertyValueFactory<>("Number_offer"));
        booking_standard.setCellValueFactory(new PropertyValueFactory<>("Standard_offer"));
        booking_type.setCellValueFactory(new PropertyValueFactory<>("Type_offer"));
        booking_floor.setCellValueFactory(new PropertyValueFactory<>("Floor_offer"));
        booking_datap.setCellValueFactory(new PropertyValueFactory<>("Datap_booking"));
        booking_datak.setCellValueFactory(new PropertyValueFactory<>("Datak_booking"));
        booking_comment1.setCellValueFactory(new PropertyValueFactory<>("Comment_booking"));
        booking_status.setCellValueFactory(new PropertyValueFactory<>("Status_booking"));
        booking_tableview.getItems().setAll(ObjectManager.GetInstance().offerservice.getReceptionData(
                client_tableview.getSelectionModel().getSelectedItem().getPesel_guest()));
    }

    @FXML
    private void cancelBooking(ActionEvent event) {
        if(booking_tableview.getSelectionModel().getSelectedItem() == null){
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz rezerwacje, którą archiwizujesz");
            alert1.showAndWait();
        } else{
        ObjectManager.GetInstance().bookingservice.cancelBooking(
                booking_tableview.getSelectionModel().getSelectedItem().getId_booking());
        refreshTable();
        }
    }
    @FXML
    private void restoreBooking(ActionEvent event) {
        if(booking_tableview.getSelectionModel().getSelectedItem() == null){
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz rezerwację, którą przywracasz");
            alert1.showAndWait();
        } else{
        ObjectManager.GetInstance().bookingservice.restoreBooking(
                booking_tableview.getSelectionModel().getSelectedItem().getId_booking());
        refreshTable();
        }
    }
    
    @FXML
    private void showOffer(ActionEvent event) {
        String datap = booking_datep.getValue().toString();
        String datak = booking_datek.getValue().toString();//new SimpleDateFormat(offer_datek);
        String typ = booking_typbox.getValue();
        String stand = booking_standardbox.getValue();
        String comment = booking_comment.getText();
        newbooking_number.setCellValueFactory(new PropertyValueFactory<>("Number_offer"));
        newbooking_floor.setCellValueFactory(new PropertyValueFactory<>("Floor_offer"));
        newbooking_type.setCellValueFactory(new PropertyValueFactory<>("Type_offer"));
        newbooking_standard.setCellValueFactory(new PropertyValueFactory<>("Standard_offer"));
        newbooking_tableview.getItems().setAll(ObjectManager.GetInstance().offerservice.getData(typ, stand, datap, datak));
    }

    @FXML
    private void updateBooking(ActionEvent event) {
        int pracownik = 1;//po dodaniu logowania pojawi się tu zmienna z numerem zalogowanego pracownika
        String datap = booking_datep.getValue().toString();
        String datak = booking_datek.getValue().toString();
        String komentarz = booking_comment.getText();
        if (client_tableview.getSelectionModel().getSelectedItem() == null || newbooking_tableview.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wybierz gościa i pokój");
            alert1.showAndWait();
            return;
        } else {
            String klient = client_tableview.getSelectionModel().getSelectedItem().getPesel_guest();
            int pokoj = newbooking_tableview.getSelectionModel().getSelectedItem().getNumber_offer();
            if (ObjectManager.GetInstance().bookingservice.insertBooking(klient, pracownik, pokoj, datap, datak, komentarz)
                    && ObjectManager.GetInstance().bookingservice.deleteBooking(
                            booking_tableview.getSelectionModel().getSelectedItem().getId_booking())) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Zakutalizowano rezerwację");
                alert4.showAndWait();
                booking_comment.clear();
                if (booking_tableview.getSelectionModel().getSelectedItem() != null) {
                    booking_tableview.getItems().remove(booking_tableview.getSelectionModel().getSelectedItem());
                }
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy dodawaniu rezerwacji");
                alert2.showAndWait();
            }
        }
    }
    public void refreshTable() {
        booking_tableview.getItems().setAll(ObjectManager.GetInstance().offerservice.getReceptionData(
                client_tableview.getSelectionModel().getSelectedItem().getPesel_guest()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base = DataBase.getInstance();
        booking_typbox.setItems(booking_typ_list);
        booking_standardbox.setItems(booking_standard_list);
        booking_datep.setValue(LocalDate.now());
        booking_datek.setValue(LocalDate.now().plusDays(1));
        client_tableview.getSelectionModel().selectedItemProperty().
                addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        initNewBooking_Table();
                    }
                });
        booking_tableview.getSelectionModel().selectedItemProperty().
                addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        booking_datep.setValue(LocalDate.parse(booking_tableview.
                                getSelectionModel().getSelectedItem().getDatap_booking()));
                        booking_datek.setValue(LocalDate.parse(booking_tableview.
                                getSelectionModel().getSelectedItem().getDatak_booking()));
                        booking_standardbox.setValue(booking_tableview.
                                getSelectionModel().getSelectedItem().getStandard_offer());
                        booking_typbox.setValue(booking_tableview.
                                getSelectionModel().getSelectedItem().getType_offer());
                    }
                });

        final Callback<DatePicker, DateCell> dateCalLabel1
                = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(LocalDate.now()) || item.isAfter(booking_datek.getValue().minusDays(1))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
        booking_datep.setDayCellFactory(dateCalLabel1);
        final Callback<DatePicker, DateCell> dateCalLabel2
                = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(booking_datep.getValue().plusDays(1))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
        booking_datek.setDayCellFactory(dateCalLabel2);
        client_name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    initGuestsBooking_Table();

                }
            }
        });
        client_surname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    initGuestsBooking_Table();

                }
            }
        });
        client_pesel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    initGuestsBooking_Table();

                }
            }
        });
        client_phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    initGuestsBooking_Table();

                }
            }
        });
    }

}
