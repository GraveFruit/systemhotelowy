/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Tasks;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mainwindow.FXMLDocumentController;
import mainwindow.ObjectManager;

/**
 *
 * @author Grzesiek
 */
public class TaskService {

    public ObservableList<Tasks> getData() {
        try {
            ObservableList<Tasks> tasks_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select po.numer, p.nazwisko "
                    + "as pracownik, k.nazwisko, z.data, z.status from "
                    + "zadania z join pokoje po on z.pokoj_id=po.pokoj_id join "
                    + "klienci k on z.klient_id=k.klient_id left join lista l "
                    + "on z.zadanie_id=l.zadanie_id left join pracownicy p "
                    + "on p.pracownik_id=l.pracownik_id");
            while (result.next()) {
                //int id = result.getInt("id");
                String numer = result.getString("numer");
                String pracownik = result.getString("pracownik");
                String klient = result.getString("nazwisko");
                String data = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data").getTime());
                String status = result.getString("status");

                tasks_list.add(new Tasks(numer, pracownik, klient, data, status));

            }

            return FXCollections.observableArrayList(tasks_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertTask(int book) {
        int pokoj_id = 0;
        int klient_id = 0;
        try {
            ResultSet result1 = DataBase.getConnection().createStatement().executeQuery("select "
                    + "pokoj_id from rezerwacje where rezerwacja_id='" + book + "'");
            while (result1.next()) {
                pokoj_id = result1.getInt("pokoj_id");
            }
            ResultSet result2 = DataBase.getConnection().createStatement().executeQuery("select "
                    + "klient_id from rezerwacje where rezerwacja_id='" + book + "'");
            while (result2.next()) {
                klient_id = result2.getInt("klient_id");

            }

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Insert into zadania (pokoj_id,klient_id,data,status) values (?,?,?,?)");
            prep.setInt(1, pokoj_id);
            prep.setInt(2, klient_id);
            prep.setString(3, ObjectManager.GetInstance().currentData);
            prep.setString(4, "1");
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu zdania z recepcji");
            return false;
        }
        return true;
    }

}
