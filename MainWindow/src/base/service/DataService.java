/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 *
 * @author Grzesiek
 */
public class DataService {

    /**
     *methods selects correct data dor booking
     * @param startDate booking start date
     * @param endDate booking end date
     */
    public void checkBookingsDate(JFXDatePicker startDate, JFXDatePicker endDate) {
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now().plusDays(1));
        final Callback<DatePicker, DateCell> dateCalLabel1
                = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(LocalDate.now()) || item.isAfter(endDate.getValue().minusDays(1))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
        final Callback<DatePicker, DateCell> dateCalLabel2
                = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(startDate.getValue().plusDays(1))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
        startDate.setDayCellFactory(dateCalLabel1);
        endDate.setDayCellFactory(dateCalLabel2);
    }

    /**
     * method selects correct date for booking prolongation 
     * @param startDate booking end date
     * @param endDate prolongation date
     */
    public void checkRaportDate(JFXDatePicker startDate, JFXDatePicker endDate) {
        startDate.setValue(LocalDate.now().minusDays(2));
        endDate.setValue(LocalDate.now().minusDays(1));
        final Callback<DatePicker, DateCell> dateCalLabel1
                = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isAfter(LocalDate.now()) || item.isAfter(endDate.getValue().minusDays(1))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
        final Callback<DatePicker, DateCell> dateCalLabel2
                = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(startDate.getValue().plusDays(1)) || item.isAfter(LocalDate.now().minusDays(1))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
        startDate.setDayCellFactory(dateCalLabel1);
        endDate.setDayCellFactory(dateCalLabel2);
    }

    /**
     *methods creates alert window
     * @param alert alert information
     */
    public void getAlertWindow(String alert) {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setHeaderText(null);
        alert1.setContentText(alert);
        alert1.showAndWait();
    }

    /**
     *methods creates information window
     * @param informaction alert information
     */
    public void getInformactiontWindow(String informaction) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText(null);
        alert1.setContentText(informaction);
        alert1.showAndWait();
    }

    /**
     *methods checks if input data is number
     * @param text data from JFX textfield
     */
    public void chechIsNumber(JFXTextField text) {
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d*")) {
                } else {
                    text.setText(oldValue);
                }
            }
        });
    }

   /**
     *methods checks if input data is number
     * @param text data from textfield
     */
    public void chechIsNumber(TextField text) {
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d*")) {
                } else {
                    text.setText(oldValue);
                }
            }
        });
    }

}
