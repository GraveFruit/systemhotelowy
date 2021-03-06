/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import base.service.OfferService;
import com.jfoenix.controls.JFXDatePicker;
import hotel.base.DataBase;
import hotel.base.Offer;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Grzesiek
 */
public class ShowOfferController implements Initializable {
    DataBase base;
    @FXML
    private JFXDatePicker offer_datep;
    @FXML
    private JFXDatePicker offer_datek;
    @FXML
    private ComboBox<String> offer_standardbox;
    @FXML
    private ComboBox<String> offer_typ;
    @FXML
    private TableView<Offer> offer_tableview;
    @FXML
    private TableColumn<?, ?> offer_standard;
    @FXML
    private TableColumn<?, ?> offer_type;
    @FXML
    private TableColumn<?, ?> offer_floor;
    @FXML
    private TableColumn<?, ?> offer_number;
    ObservableList<String> offer_typ_list = FXCollections.observableArrayList("1", "1+1", "2", "2+1", "3");
    ObservableList<String> offer_standard_list = FXCollections.observableArrayList("vip", "ekonomiczny", "biznesowy");
    
    @FXML
    private void showOffer(ActionEvent event) {
         
        String datap = offer_datep.getValue().toString();
        String datak = offer_datek.getValue().toString();//new SimpleDateFormat(offer_datek);
        String typ = offer_typ.getValue();
        String stand = offer_standardbox.getValue();
        if(typ!=null && stand!=null){
        offer_number.setCellValueFactory(new PropertyValueFactory<>("Number_offer"));
        offer_floor.setCellValueFactory(new PropertyValueFactory<>("Floor_offer"));
        offer_type.setCellValueFactory(new PropertyValueFactory<>("Type_offer"));
        offer_standard.setCellValueFactory(new PropertyValueFactory<>("Standard_offer"));
        offer_tableview.getItems().setAll(ObjectManager.GetInstance().offerservice.getData(typ, stand, datap, datak));  
    }else{
        ObjectManager.GetInstance().dataservice.getAlertWindow("Wybierz typ i standard");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        base = DataBase.getInstance();
        offer_typ.setItems(offer_typ_list);
        offer_standardbox.setItems(offer_standard_list);
        ObjectManager.GetInstance().dataservice.checkBookingsDate(offer_datep, offer_datek);

    }    
}
   
