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
            ResultSet result = statement.executeQuery("select z.zadanie_id, po.numer, p.nazwisko "
                    + "as pracownik, k.nazwisko, z.data, z.opis, z.status from "
                    + "zadania z join pokoje po on z.pokoj_id=po.pokoj_id join "
                    + "klienci k on z.klient_id=k.klient_id left join lista l "
                    + "on z.zadanie_id=l.zadanie_id left join pracownicy p "
                    + "on p.pracownik_id=l.pracownik_id order by z.data desc, z.status asc");
            while (result.next()) {
                int id = result.getInt("zadanie_id");
                String numer = result.getString("numer");
                String pracownik = result.getString("pracownik");
                String klient = result.getString("nazwisko");
                String data = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data").getTime());
                String opis = result.getString("opis");
                String status = result.getString("status");

                tasks_list.add(new Tasks(id, numer, pracownik, klient, data, opis, status));

            }

            return FXCollections.observableArrayList(tasks_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     public ObservableList<Tasks> getDetailsData(String osoba) {
        try {
            ObservableList<Tasks> tasks_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select z.zadanie_id, po.numer, "
                    + "k.nazwisko, z.data, z.opis, z.status from zadania z , pokoje po, "
                    + "klienci k,lista l,pracownicy p  where z.pokoj_id=po.pokoj_id "
                    + "and z.klient_id=k.klient_id and z.zadanie_id=l.zadanie_id and "
                    + "p.pracownik_id=l.pracownik_id and p.pracownik_id='"+osoba+"' order by z.data desc, z.status asc");
            while (result.next()) {
                int id = result.getInt("zadanie_id");
                String numer = result.getString("numer");
                String klient = result.getString("nazwisko");
                String data = (new SimpleDateFormat("yyyy-MM-dd")).format(result.getTimestamp("data").getTime());
                String opis = result.getString("opis");
                String status = result.getString("status");

                tasks_list.add(new Tasks(id, numer, klient, data, opis, status));

            }

            return FXCollections.observableArrayList(tasks_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean insertTask(int book, String opis) {
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
                    "Insert into zadania (pokoj_id,klient_id,data,opis,status) values (?,?,?,?,?)");
            prep.setInt(1, pokoj_id);
            prep.setInt(2, klient_id);
            prep.setString(3, ObjectManager.GetInstance().currentData);
            prep.setString(4, opis);
            prep.setString(5, "1");
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu zdania z recepcji");
            return false;
        }
        return true;
    }

    public boolean insertTask2(int book, String opis) {
        String klient_id ="";
        try {
            ResultSet result2 = DataBase.getConnection().createStatement().executeQuery("select "
                    + "klient_id from rezerwacje where pokoj_id='" + book + "' and "
                    + ObjectManager.GetInstance().currentData+" between data_p and data_k");
            while (result2.next()) {
                klient_id = result2.getString("klient_id");   
            }
            if(klient_id.isEmpty()){
                    klient_id="1";
            }
            
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Insert into zadania (pokoj_id,klient_id,data,opis,status) values (?,?,?,?,?)");
            prep.setInt(1, book);
            prep.setInt(2, Integer.parseInt(klient_id));
            prep.setString(3, ObjectManager.GetInstance().currentData);
            prep.setString(4, opis);
            prep.setString(5, "1");
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu zdania ");
            return false;
        }
        return true;
    }

    public boolean insertTaskWithEmployee(int pracownik) {
        int zadanie = 0;
        try {
            ResultSet result1 = DataBase.getConnection().createStatement().executeQuery("select "
                    + "zadanie_id from zadania order by zadanie_id desc limit 1  ");
            while (result1.next()) {
                zadanie = result1.getInt("zadanie_id");
            }
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Insert into lista (zadanie_id,pracownik_id) values (?,?)");
            prep.setInt(1, zadanie);
            prep.setInt(2, pracownik);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu zdania z recepcji");
            return false;
        }
        return true;
    }

    public boolean insertList() {
        int zadanie = 0;
        try {
            ResultSet result1 = DataBase.getConnection().createStatement().executeQuery("select "
                    + "zadanie_id from zadania order by zadanie_id desc limit 1  ");
            while (result1.next()) {
                zadanie = result1.getInt("zadanie_id");
            }
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Insert into lista (zadanie_id,pracownik_id) values (?,?)");
            prep.setInt(1, zadanie);
            prep.setInt(2, ObjectManager.GetInstance().employeeservice.getLazyTaskEmployee());
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu zdania z recepcji");
            return false;
        }
        return true;
    }

    public boolean checkRoomReady(int pokoj) {
        String zadanie = "";
        String numer="";
        boolean wynik = false;
        try {
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select zadanie_id from zadania "
                    + "where data='" + ObjectManager.GetInstance().currentData
                    + "' and status=1 and pokoj_id='" + pokoj + "'");
            while (result.next()) {
                zadanie = result.getString("zadanie_id");
            }
             ResultSet result2 = statement.executeQuery("select status from pokoje "
                    + "where pokoj_id='" + pokoj + "'");
            while (result2.next()) {
                numer = result2.getString("status");
            }
            if(numer.compareTo("1")==0){
                return false;
            }
            wynik = zadanie.isEmpty();
            //System.out.println("stan= "+standard+" wynik= "+wynik);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wynik;
    }

    public boolean deleteTask(int numer) {
        try {

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "delete from zadania where zadanie_id=?");
            prep.setInt(1, numer);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy usuwaniu zadania");
            return false;
        }
        return true;
    }
    
     public boolean updateTaskStatus(int numer) {
        try {

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update zadania set status='0' where zadanie_id=?");
            prep.setInt(1, numer);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy zmianie statusu zadania");
            return false;
        }
        return true;
    }

}
