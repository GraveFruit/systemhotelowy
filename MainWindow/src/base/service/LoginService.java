/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import hotel.base.DataBase;
import hotel.base.Rooms;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import mainwindow.FXMLDocumentController;
import mainwindow.ObjectManager;

/**
 *
 * @author Grzesiek
 */
public class LoginService {

    /**
     *permission storing variable
     */
    public String permissions;

    /**
     *employee_ID storing variable
     */
    public String employeeSessionId;

    /**
     *basic construct
     */
    public LoginService() {
    }

    /**
     * main login seqence method which uses sql query to connect to database
     * @param login - login variable
     * @param pass - password variable
     * @throws SQLException which does not work maybe ( to be or not to be)
     */
    public void getlogged(String login, String pass) throws SQLException {
        PreparedStatement prep = DataBase.getConnection().prepareStatement(
                "select p.pracownik_id, po.uprawnienia "
                + "from pracownicy p, posady po "
                + "where p.posada_id = po.posada_id "
                + "and p.pesel =? "
                + "and p.haslo =? ");
        prep.setString(1, login);
        prep.setString(2, pass);
        ResultSet result = prep.executeQuery();
        while (result.next()) {
            this.employeeSessionId = result.getString("pracownik_id");
            this.permissions = result.getString("uprawnienia");
        }
    }

    /**
     *removes previous tasks(which data is other then today)
     */
    public void makeStartQuery() {
        try {
            PreparedStatement prep = DataBase.getConnection().prepareStatement(
                    "delete from zadania where status=0 and data!='" + ObjectManager.GetInstance().currentData + "'");
            prep.executeQuery();
        } catch (SQLException e) {
            System.out.println("błąd wczytywania początkowej kwerendy");
        }
    }

    /**
     * logout sequence 
     */
    public void logout() {
        this.permissions = null;
        this.employeeSessionId = null;
    }

    /**
     * inicialize database
     */
    public void makeStartBase() {
        try {
            PreparedStatement prep = DataBase.getConnection().prepareStatement("-- "
                    + "----------------------------------------------------------------------------\n"
                    + "-- Software        :  JPDB Admin for MariaDB - Free Edition [Windows]\n"
                    + "-- Server Version  :  mariadb.org binary distribution - 10.1.22-MariaDB\n"
                    + "-- Database        :  hotelmaster\n"
                    + "-- Host            :  127.0.0.1\n"
                    + "-- Date/Time       :  04/05/2017 09:07:34 PM\n"
                    + "-- ----------------------------------------------------------------------------\n"
                    + "\n"
                    + "SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;\n"
                    + "SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;\n"
                    + "SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';\n"
                    + "SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;\n"
                    + "SET NAMES utf8;\n"
                    + "\n"
                    + "DROP TABLE IF EXISTS `klienci`;\n"
                    + "CREATE TABLE `klienci` (\n"
                    + "  `klient_id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `imie` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  `nazwisko` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  `tel` varchar(12) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  `pesel` varchar(11) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`klient_id`)\n"
                    + ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;\n"
                    + "\n"
                    + "ALTER TABLE `klienci` DISABLE KEYS;\n"
                    + "SET AUTOCOMMIT=0;\n"
                    + "INSERT INTO `klienci` VALUES ('1', 'system', 'system', '0', '0');\n"
                    + "INSERT INTO `klienci` VALUES ('2', 'Wioletta', 'Żołądek', '87513469832', '72092636307');\n"
                    + "INSERT INTO `klienci` VALUES ('3', 'Grzegorz', 'Śledziona', '87549626', '83112125216');\n"
                    + "INSERT INTO `klienci` VALUES ('4', 'Jakub', 'Trzustka', '216874236', '94021614125');\n"
                    + "INSERT INTO `klienci` VALUES ('5', 'Eustachy', 'Okrężnica', '976428314', '85041113034');\n"
                    + "INSERT INTO `klienci` VALUES ('6', 'Hilda', 'Obojczyk', '788914568', '76060622943');\n"
                    + "INSERT INTO `klienci` VALUES ('7', 'Róża', 'Piszczel', '972358468', '67080131852');\n"
                    + "INSERT INTO `klienci` VALUES ('8', 'Kamil', 'Łydka', '875315978', '78102740761');\n"
                    + "INSERT INTO `klienci` VALUES ('9', 'Dominik', 'Miednica', '987213564', '89122251672');\n"
                    + "INSERT INTO `klienci` VALUES ('10', 'Mikołaj', 'Zaraza', '927211584', '52022271661');\n"
                    + "INSERT INTO `klienci` VALUES ('11', 'Albert', 'Mak', '987213564', '89122251670');\n"
                    + "INSERT INTO `klienci` VALUES ('12', 'Beata', 'Tomaszewska', '539017654', '68111534728');\n"
                    + "INSERT INTO `klienci` VALUES ('13', 'Karolina', 'Walczak', '698634761', '96103095845');\n"
                    + "INSERT INTO `klienci` VALUES ('14', 'Zenon', 'Kamiński', '533662236', '95102058499');\n"
                    + "INSERT INTO `klienci` VALUES ('15', 'Łucja', 'Sokołowska', '699214687', '97032230585');\n"
                    + "INSERT INTO `klienci` VALUES ('16', 'Beata', 'Kowalska', '884432672', '96011721702');\n"
                    + "INSERT INTO `klienci` VALUES ('17', 'Maryla', 'Duda', '795569973', '35082365507');\n"
                    + "INSERT INTO `klienci` VALUES ('18', 'Patryk', 'Sobczak', '882807953', '78092934033');\n"
                    + "INSERT INTO `klienci` VALUES ('19', 'Witold', 'Wysocki', '788911307', '68032650299');\n"
                    + "INSERT INTO `klienci` VALUES ('20', 'Jagoda', 'Tekla', '609958144', '70101011788');\n"
                    + "COMMIT;\n"
                    + "ALTER TABLE `klienci` ENABLE KEYS;\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "DROP TABLE IF EXISTS `pokoje`;\n"
                    + "CREATE TABLE `pokoje` (\n"
                    + "  `pokoj_id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `numer` varchar(3) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  `pietro` varchar(2) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  `typ` varchar(3) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  `standard` varchar(12) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  `status` varchar(2) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`pokoj_id`)\n"
                    + ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;\n"
                    + "\n"
                    + "ALTER TABLE `pokoje` DISABLE KEYS;\n"
                    + "SET AUTOCOMMIT=0;\n"
                    + "INSERT INTO `pokoje` VALUES ('1', '1', 'P', '1', 'vip', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('2', '2', 'P', '1+1', 'ekonomiczny', '1');\n"
                    + "INSERT INTO `pokoje` VALUES ('3', '3', 'P', '2', 'biznesowy', '1');\n"
                    + "INSERT INTO `pokoje` VALUES ('4', '4', '1', '2+1', 'ekonomiczny', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('5', '5', '2', '1+1', 'vip', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('6', '6', '1', '2', 'biznesowy', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('7', '7', '1', '1+1', 'ekonomiczny', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('8', '8', 'P', '2+1', 'vip', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('9', '9', '2', '1+1', 'vip', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('10', '10', '1', '1', 'biznesowy', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('11', '11', 'P', '2', 'ekonomiczny', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('12', '12', '2', '2+1', 'biznesowy', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('13', '13', 'P', '1+1', 'ekonomiczny', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('14', '14', '1', '1', 'vip', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('15', '15', '2', '1+1', 'biznesowy', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('16', '16', 'P', '2', 'ekonomiczny', '2');\n"
                    + "INSERT INTO `pokoje` VALUES ('17', '17', '1', '2+1', 'biznesowy', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('18', '18', 'P', '1', 'vip', '1');\n"
                    + "INSERT INTO `pokoje` VALUES ('19', '19', '2', '2', 'ekonomiczny', '0');\n"
                    + "INSERT INTO `pokoje` VALUES ('20', '20', 'P', '1+1', 'vip', '1');\n"
                    + "COMMIT;\n"
                    + "ALTER TABLE `pokoje` ENABLE KEYS;\n"
                    + "\n"
                    + "DROP TABLE IF EXISTS `pracownicy`;\n"
                    + "CREATE TABLE `pracownicy` (\n"
                    + "  `pracownik_id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `imie` varchar(50) COLLATE utf8_polish_ci NOT NULL,\n"
                    + "  `nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL,\n"
                    + "  `telefon` varchar(12) COLLATE utf8_polish_ci NOT NULL,\n"
                    + "  `pesel` varchar(11) COLLATE utf8_polish_ci NOT NULL,\n"
                    + "  `posada_id` int(11) NOT NULL,\n"
                    + "  `status` varchar(2) COLLATE utf8_polish_ci NOT NULL,\n"
                    + "  `haslo` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`pracownik_id`),\n"
                    + "  KEY `posada_id` (`posada_id`),\n"
                    + "  CONSTRAINT `posada_id` FOREIGN KEY (`posada_id`) REFERENCES `posady` (`posada_id`)\n"
                    + ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;\n"
                    + "\n"
                    + "ALTER TABLE `pracownicy` DISABLE KEYS;\n"
                    + "SET AUTOCOMMIT=0;\n"
                    + "INSERT INTO `pracownicy` VALUES ('1', 'system', 'system', 'system', 'system', '1', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('2', 'Józef', 'Noga', '159267483', '65102740761', '1', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('3', 'Gertruda', 'Pancernik', '321654987', '74102740761', '2', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('4', 'Adolf', 'Malarz', '936825714', '84102740761', '3', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('5', 'Ilona', 'Rybak', '132684579', '93102740761', '4', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('6', 'Ewa', 'Bąk', '608971052', '94093532725', '2', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('7', 'Mikołaj', 'Kowalski', '608971052', '95090532735', '2', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('8', 'Jarosław', 'Majewski', '531579185', '95042285125', '1', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('9', 'Edmund', 'Kuleczka', '703271052', '35011870832', '2', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('10', 'Maksym', 'Wieczorek', '515305415', '78021257211', '1', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('11', 'Arkadiusz', 'Walczak', '799127956', '37121894274', '3', '1', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('12', 'Zdzisława', 'Dąbrowski', '661924978', '53040837842', '3', '1', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('13', 'Mieczysława', 'Zając', '761924998', '63470843689', '4', '1', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('14', 'Magda', 'Bednarek', '961934965', '31121520979', '4', '0', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('15', 'Elżbieta', 'Pękala', '761924998', '60070857689', '4', '1', '');\n"
                    + "INSERT INTO `pracownicy` VALUES ('16', 'Marek', 'Wek', '519056285', '792268686', '4', '1', '');\n"
                    + "COMMIT;\n"
                    + "ALTER TABLE `pracownicy` ENABLE KEYS;\n"
                    + "\n"
                    + "DROP TABLE IF EXISTS `lista`;\n"
                    + "CREATE TABLE `lista` (\n"
                    + "  `zadanie_id` int(11) DEFAULT NULL,\n"
                    + "  `pracownik_id` int(11) DEFAULT NULL,\n"
                    + "  KEY `zadanie_id1` (`zadanie_id`),\n"
                    + "  KEY `pracownik_id1` (`pracownik_id`),\n"
                    + "  CONSTRAINT `pracownik_id1` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownicy` (`pracownik_id`),\n"
                    + "  CONSTRAINT `zadanie_id1` FOREIGN KEY (`zadanie_id`) REFERENCES `zadania` (`zadanie_id`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;\n"
                    + "ALTER TABLE `lista` DISABLE KEYS;\n"
                    + "SET AUTOCOMMIT=0;\n"
                    + "INSERT INTO `lista` VALUES ('1', '16');\n"
                    + "INSERT INTO `lista` VALUES ('2', '15');\n"
                    + "INSERT INTO `lista` VALUES ('3', '13');\n"
                    + "INSERT INTO `lista` VALUES ('4', '5');\n"
                    + "INSERT INTO `lista` VALUES ('5', '16');\n"
                    + "\n"
                    + "COMMIT;\n"
                    + "ALTER TABLE `lista` ADD FOREIGN KEY ( `zadanie_id` ) REFERENCES `zadania` (`zadanie_id`) ON DELETE CASCADE ON UPDATE CASCADE ;\n"
                    + "ALTER TABLE `lista` ADD FOREIGN KEY ( `pracownik_id` ) REFERENCES `pracownicy` (`pracownik_id`) ON DELETE CASCADE ON UPDATE CASCADE ;\n"
                    + "\n"
                    + "DROP TABLE IF EXISTS `rezerwacje`;\n"
                    + "CREATE TABLE `rezerwacje` (\n"
                    + "  `rezerwacja_id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `klient_id` int(11) NOT NULL DEFAULT '0',\n"
                    + "  `pracownik_id` int(11) NOT NULL DEFAULT '0',\n"
                    + "  `pokoj_id` int(11) NOT NULL DEFAULT '0',\n"
                    + "  `data_p` date NOT NULL,\n"
                    + "  `data_k` date NOT NULL,\n"
                    + "  `status` VARCHAR(2) NOT NULL DEFAULT '0',\n"
                    + "  `komentarz` VARCHAR(30) NOT NULL,\n"
                    + "  PRIMARY KEY (`rezerwacja_id`),\n"
                    + "  KEY `klient_id` (`klient_id`),\n"
                    + "  KEY `pracownik_id` (`pracownik_id`),\n"
                    + "  KEY `pokoj_id` (`pokoj_id`),\n"
                    + "  CONSTRAINT `klient_id` FOREIGN KEY (`klient_id`) REFERENCES `klienci` (`klient_id`),\n"
                    + "  CONSTRAINT `pokoj_id` FOREIGN KEY (`pokoj_id`) REFERENCES `pokoje` (`pokoj_id`),\n"
                    + "  CONSTRAINT `pracownik_id` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownicy` (`pracownik_id`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;\n"
                    + "\n"
                    + "ALTER TABLE `rezerwacje` DISABLE KEYS;\n"
                    + "SET AUTOCOMMIT=0;\n"
                    + "INSERT INTO `rezerwacje` VALUES ('1', '20', '15', '8', '2017-06-02', '2017-07-01', '1', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('2', '18', '12', '6', '2017-03-21', '2017-04-15', '0', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('3', '14', '9', '10', '2017-09-01', '2017-10-10', '1', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('4', '5', '6', '5', '2017-05-16', '2017-05-20', '1', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('5', '7', '1', '19', '2017-04-21', '2017-04-23', '0', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('6', '10', '4', '5', '2016-12-27', '2017-01-05', '0', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('7', '3', '16', '15', '2017-02-21', '2017-03-26', '0', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('8', '11', '9', '12', '2017-04-26', '2017-04-30', '0', 'poranna kawa o 8:00');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('9', '2', '3', '20', '2017-04-25', '2017-06-29', '2', 'sprzątanie o 10:00');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('10', '7', '2', '19', '2017-05-25', '2017-05-27', '1', 'codziennie nowe ręczniki');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('11', '20', '12', '18', '2017-04-25', '2017-05-19', '2', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('12', '5', '1', '1', '2017-08-12', '2017-09-02', '1', 'śniadanie o 7:00');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('13', '6', '2', '4', '2017-07-01', '2017-07-26', '1', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('14', '5', '1', '1', '2017-09-08', '2017-09-15', '1', 'śniadanie o 7:15');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('15', '4', '14', '4', '2017-06-03', '2017-06-07', '1', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('16', '19', '2', '16', '2017-04-25', '2017-05-02', '0', 'zamawiam kawę o 8:00');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('17', '8', '5', '9', '2017-05-05', '2017-05-11', '1', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('18', '9', '12', '9', '2017-05-11', '2017-05-26', '1', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('19', '10', '16', '1', '2017-01-15', '2017-01-20', '0', 'sprzątanie pokoju o 8:30');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('20', '20', '7', '16', '2016-11-01', '2017-01-15', '0', 'co tydzień nowy ręcznik');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('21', '15', '8', '2', '2017-04-25', '2017-05-31', '2', 'brak');\n"
                    + "INSERT INTO `rezerwacje` VALUES ('22', '16', '8', '3', '2017-04-25', '2017-05-31', '2', 'poranne budzenie o 7:30');\n"
                    + "COMMIT;\n"
                    + "ALTER TABLE `pracownicy` ENABLE KEYS;\n"
                    + "\n"
                    + "DROP TABLE IF EXISTS `zadania`;\n"
                    + "CREATE TABLE `zadania` (\n"
                    + "  `zadanie_id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `pokoj_id` int(11) NOT NULL DEFAULT '0',\n"
                    + "  `klient_id` int(11) NOT NULL DEFAULT '0',\n"
                    + "  `data` date DEFAULT NULL,\n"
                    + "  `opis` VARCHAR(30) NOT NULL,		\n"
                    + "  `status` varchar(2) NOT NULL DEFAULT '0',\n"
                    + "  PRIMARY KEY (`zadanie_id`),\n"
                    + "  KEY `pokoj_id1` (`pokoj_id`),\n"
                    + "  KEY `klient_id1` (`klient_id`),\n"
                    + "  CONSTRAINT `klient_id1` FOREIGN KEY (`klient_id`) REFERENCES `klienci` (`klient_id`),\n"
                    + "  CONSTRAINT `pokoj_id1` FOREIGN KEY (`pokoj_id`) REFERENCES `pokoje` (`pokoj_id`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;\n"
                    + "ALTER TABLE `zadania` DISABLE KEYS;\n"
                    + "SET AUTOCOMMIT=0;\n"
                    + "INSERT INTO `zadania` VALUES ('1', '20', '15', '2017-05-04', 'sprzatanie', '1');\n"
                    + "INSERT INTO `zadania` VALUES ('2', '17', '12', '2017-03-21', 'sprzatanie', '0');\n"
                    + "INSERT INTO `zadania` VALUES ('3', '16', '9', '2017-05-04', 'sprzatanie', '1');\n"
                    + "INSERT INTO `zadania` VALUES ('4', '11', '6', '2017-04-04', 'sprzatanie', '0');\n"
                    + "INSERT INTO `zadania` VALUES ('5', '6', '1', '2017-04-21', 'sprzatanie', '0');\n"
                    + "COMMIT;\n"
                    + "ALTER TABLE `pracownicy` ENABLE KEYS;\n"
                    + "\n"
                    + "SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;\n"
                    + "SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;\n"
                    + "SET SQL_MODE=@OLD_SQL_MODE;\n"
                    + "SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;\n"
                    + "\n"
                    + "");
            prep.executeQuery();
        } catch (SQLException e) {
            System.out.println("błąd wczytywania bazy");
        }
    }
    

}
