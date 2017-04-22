/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import hotel.base.DataBase;
import hotel.base.Guests;
import hotel.base.Offer;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
    private JFXComboBox<String> booking_clients;
    @FXML
    private JFXDatePicker booking_datep;
    @FXML
    private JFXDatePicker booking_datek;
    @FXML
    private ComboBox<String> booking_standardbox;
    @FXML
    private ComboBox<String> booking_typ;
    @FXML
    private TextField booking_comment;
    @FXML
    private Button booking_add;
    ObservableList<String> booking_typ_list = FXCollections.observableArrayList("1", "1+1", "2", "2+1", "3");
    ObservableList<String> booking_client = ObjectManager.GetInstance().guestservice.getBooking_Data();
    ObservableList<String> booking_standard_list = FXCollections.observableArrayList("vip", "ekonomiczny", "biznesowy");
   
    @FXML
    private Button booking_offer;
    @FXML
    private Label booking_label;

    @FXML
    private void showOffer(ActionEvent event) {
        String datap = booking_datep.getValue().toString();
        String datak = booking_datek.getValue().toString();//new SimpleDateFormat(offer_datek);
        String typ = booking_typ.getValue();
        String stand = booking_standardbox.getValue();
        String comment= booking_comment.getText();
        booking_number.setCellValueFactory(new PropertyValueFactory<>("Number_offer"));
        booking_floor.setCellValueFactory(new PropertyValueFactory<>("Floor_offer"));
        booking_type.setCellValueFactory(new PropertyValueFactory<>("Type_offer"));
        booking_standard.setCellValueFactory(new PropertyValueFactory<>("Standard_offer"));
        booking_tableview.getItems().setAll(ObjectManager.GetInstance().offerservice.getData(typ, stand, datap, datak));  
    }
    
    @FXML
    private void addBooking(ActionEvent event) throws SQLException {
        String klient = booking_clients.getValue();
        int pracownik = 1;//po dodaniu logowania pojawi się tu zmienna z numerem zalogowanego pracownika
        String datap = booking_datep.getValue().toString();
        String datak = booking_datek.getValue().toString();
        String komentarz = booking_comment.getText();
        if (booking_clients.getValue()==null || booking_tableview.getSelectionModel().getSelectedItem()==null  ) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wypełnij wszystkie pola");
            alert1.showAndWait();
            return;
        } else {
            int  pokoj = booking_tableview.getSelectionModel().getSelectedItem().getNumber_offer();
            if (ObjectManager.GetInstance().bookingservice.insertBooking(klient, pracownik, pokoj, datap, datak, komentarz)) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText(null);
                alert4.setContentText("Dodano rezerwację");
                alert4.showAndWait();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("Błąd przy dodawaniu rezerwacji");
                alert2.showAndWait();
            }
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base = DataBase.getInstance();
        booking_typ.setItems(booking_typ_list);
        booking_standardbox.setItems(booking_standard_list);
        booking_clients.setItems(booking_client);
        booking_datep.setValue(LocalDate.now());
        booking_datek.setValue(LocalDate.now().plusDays(1));
        booking_tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        booking_label.setText("You've choosen room "+booking_tableview.getSelectionModel().getSelectedItem().getNumber_offer().toString());
    }
});
         final Callback<DatePicker, DateCell> dateCalLabel1 = 
            (final DatePicker datePicker) -> new DateCell() {
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
        final Callback<DatePicker, DateCell> dateCalLabel2 = 
            (final DatePicker datePicker) -> new DateCell() {
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
    }    
}