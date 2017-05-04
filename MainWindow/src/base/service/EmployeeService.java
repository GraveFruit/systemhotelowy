/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mainwindow.FXMLDocumentController;

/**
 *
 * @author Grzesiek
 */
public class EmployeeService {

    public ObservableList<Employee> getData() {
        try {
            ObservableList<Employee> employee_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select p.imie, p.nazwisko, p.telefon, p.pesel, p.status, po.nazwa from pracownicy p ,posady po where p.posada_id=po.posada_id");
            while (result.next()) {
                //int id = result.getInt("id");
                String imie = result.getString("imie");
                String type = result.getString("nazwisko");
                String floor = result.getString("telefon");
                String lozko = result.getString("pesel");
                String stan = result.getString("nazwa");
                String status = result.getString("status");

                employee_list.add(new Employee(imie, type, floor, lozko, stan, status));

            }

            return FXCollections.observableArrayList(employee_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ObservableList<Employee> getTaskEmployeeData() {
        try {
            ObservableList<Employee> employee_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select p.pracownik_id, p.nazwisko, "
                    + "count(l.zadanie_id) as ilosc_zadan, count(z.zadanie_id) as ilosc_skonczonych "
                    + "from pracownicy p left join lista l on p.pracownik_id=l.pracownik_id  "
                    + "left join zadania z on z.zadanie_id=l.zadanie_id and z.status=0 group by p.pracownik_id "
                    + "having p.pracownik_id in( select pracownik_id from pracownicy where status='1' and posada_id='4') "
                    + "order by ilosc_zadan asc, ilosc_skonczonych desc");
            while (result.next()) {
                //int id = result.getInt("id");
                int id = result.getInt("pracownik_id");
                String naz = result.getString("nazwisko");
                int ilosc = result.getInt("ilosc_zadan");
                int ilosc2 = result.getInt("ilosc_skonczonych");

                employee_list.add(new Employee(id, naz, ilosc,ilosc2));

            }

            return FXCollections.observableArrayList(employee_list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getLazyTaskEmployee() {
        int wynik = 0;
        try {
            ObservableList<Employee> employee_list = FXCollections.observableArrayList();
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("select distinct p.pracownik_id, "
                    + "count(l.zadanie_id) as ilosc_zadan, count(z.zadanie_id) as ilosc_skonczonych "
                    + "from pracownicy p left join lista l on p.pracownik_id=l.pracownik_id "
                    + "left join zadania z on z.zadanie_id=l.zadanie_id and z.status=0 group by p.pracownik_id "
                    + "having p.pracownik_id in( select pracownik_id from pracownicy where status='1' and posada_id='4') "
                    + "order by ilosc_zadan asc, ilosc_skonczonych desc limit 1");
            while (result.next()) {
                //int id = result.getInt("id");
                int id = result.getInt("pracownik_id");
                wynik = id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wynik;
    }

    public boolean insertEmployee(String imie, String nazwisko, String telefon, String pesel, String posada, String hasło) {
        int posada_nr = 0;
        try {
            ResultSet result = DataBase.getConnection().createStatement().executeQuery("select posada_id from posady where nazwa='" + posada + "'");

            while (result.next()) {
                posada_nr = result.getInt("posada_id");
            }

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Insert into pracownicy (imie,nazwisko,telefon,pesel,posada_id,status,haslo) values (?,?,?,?,?,?,?)");
            prep.setString(1, imie);
            prep.setString(2, nazwisko);
            prep.setString(3, telefon);
            prep.setString(4, pesel);
            prep.setInt(5, posada_nr);
            prep.setString(6, "0");
            prep.setString(7, hasło);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu pracownika");
            return false;
        }
        return true;
    }

    public boolean updateEmployeeData(String pesel, String telefon, String posada) {
        int posada_nr = 0;
        try {
            ResultSet result = DataBase.getConnection().createStatement().executeQuery(
                    "select posada_id from posady where nazwa='" + posada + "'");

            while (result.next()) {
                posada_nr = result.getInt("posada_id");
            }

            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update pracownicy set telefon=?, posada_id=? where pesel=?");
            prep.setString(1, telefon);
            prep.setInt(2, posada_nr);
            prep.setString(3, pesel);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy aktualizacji pracownika");
            return false;
        }
        return true;
    }

    public boolean deleteEmployee(String pesel) {
        try {
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "Update pracownicy set status='-1' where pesel=?");
            prep.setString(1, pesel);
            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Błąd przy usuwaniu pracownika");
            return false;
        }
        return true;
    }

}
