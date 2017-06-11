/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import hotel.base.DataBase;
import hotel.base.Guests;
import hotel.base.Rooms;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import mainwindow.FXMLDocumentController;
import mainwindow.ObjectManager;

/**
 *
 * @author Grzesiek
 */
public class RaportService {

    /**
     * method creates new roomtable using query
     * @param document new object Document
     * @param pdfTable new object pdfTable
     * @param query database query
     */
    public void makeRoomTable(Document document, PdfPTable pdfTable, String query) {
        try {
            pdfTable = new PdfPTable(4);
            PdfPCell cell1 = new PdfPCell(new Phrase("Pokój"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("Standard"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("Typ"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("Pietro"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            pdfTable.setHeaderRows(1);
            Statement statement = DataBase.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                pdfTable.addCell(Integer.toString(result.getInt("numer")));
                pdfTable.addCell(result.getString("standard"));
                pdfTable.addCell(result.getString("typ"));
                pdfTable.addCell(result.getString("pietro"));
            }
            document.add(pdfTable);
        } catch (DocumentException | SQLException e) {
            getAlertWindow("Wystąpił problem przy generowaniu tabeli");
        }
    }

    /**
     * method performs query
     * @param query new query
     * @return ResultSet object created using query
     */
    public ResultSet makeQuery(String query) {
        ResultSet result = null;
        try {
            Statement statement = DataBase.getConnection().createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            getAlertWindow("Wystąpił problem przy generowaniu zapytania");
        }
        return result;
    }

    /**
     * method creates new roomtable using query
     * @param document new Pdf Document
     * @param pdfTable new Pdf Table
     * @param query new query using to fill table
     * @param columns name of table columns and also name columns of data creates
     * using query
     * @param size Pdf Table size
     */
    public void makeRoomTable(Document document, PdfPTable pdfTable, String query, ArrayList<String> columns, int size) {
        try {
            pdfTable = new PdfPTable(size);
            for (int i = 0; i < columns.size(); i++) {
                PdfPCell cell1 = new PdfPCell(new Phrase(columns.get(i)));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTable.addCell(cell1);
            }
            pdfTable.setHeaderRows(1);
            ResultSet result = makeQuery(query);
            while (result.next()) {
                for (int i = 0; i < columns.size(); i++) {
                    pdfTable.addCell(result.getString(columns.get(i)));
                }
            }
            document.add(pdfTable);
        } catch (DocumentException | SQLException e) {
            getAlertWindow("Wystąpił problem przy generowaniu tabeli");
        }
    }

    /**
     * method counts averange of daily occupation
     * @param document new object Document
     * @param list new object List
     * @param query database query
     * @param column name column from query 
     */
    public void countAverageForSingleDay(Document document, List list, String query, String column) {
        double count;
        double average;
        double rooms=1;
        try {
            String query1 = "select count(pokoj_id) as liczba from pokoje";
            ResultSet res = makeQuery(query1);
             while (res.next()) {
             rooms = Double.parseDouble(res.getString("liczba"));
             }
            ResultSet result = makeQuery(query);
            while (result.next()) {
                list.add(new ListItem("Rooms occupied: " + result.getString(column)));
                count = Double.parseDouble(result.getString(column));
                average = ObjectManager.GetInstance().checkdata.countAverage(count, rooms);

                list.add(new ListItem("Occupaction average= " + ObjectManager.GetInstance().checkdata.roundNumber(average) + "%"));
            }

        } catch (SQLException e) {
            getAlertWindow("Wystąpił problem przy generowaniu tabeli");
        }
    }

    /**
     * method create new roomtable using query
     * @param document Pdf Document
     * @param list new List name
     * @param query database query
     * @param listName List's new object name
     * @param column name of column use to perform query
     */
    public void makePdfList(Document document, List list, String query, String listName, String column) {
        try {
            ResultSet result = makeQuery(query);
            while (result.next()) {
                list.add(new ListItem(listName + result.getString(column)));
            }
        } catch (SQLException e) {
            getAlertWindow("Wystąpił problem przy generowaniu tabeli");
        }
    }

    /**
     * methods creates new AlertWindow
     * @param alert comment which Alert will show
     */
    public void getAlertWindow(String alert) {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setHeaderText(null);
        alert1.setContentText(alert);
        alert1.showAndWait();
    }

    /**
     * method opens pdf file
     * @param path path to pdf file
     */
    public void openPdf(String path) {
        try {
            if ((new File(path)).exists()) {
                Process process = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
                process.waitFor();
            } else {
                getAlertWindow("Ten pdf nie istnieje");
            }

        } catch (IOException | InterruptedException ex) {
            getAlertWindow("Wystąpił problem przy otwieraniu raportu");
        }
    }

    /**
     * methods return application path
     * @param name of file which will be create in this location
     * @return path to object which will be creates
     */
    public String findPath(String name) {
        File f = new File(System.getProperty("java.class.path"));
        File dir = f.getAbsoluteFile().getParentFile();
        String path = dir.toString();
        return path.concat("\\" + name);
    }

    /**
     * methods creates new daily raport
     */
    public void generateRaport() {
        try {
            Document document = new Document();
            OutputStream file = new FileOutputStream(new File("Rooms raport.pdf"));
            PdfWriter.getInstance(document, file);
            document.open();
            document.add(new Paragraph("Occuaction raport", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));
            document.add(new Paragraph(new Date().toString()));
            document.addAuthor("HotelMaster 2000");
            document.addCreationDate();
            document.addTitle("Rooms raport");
            //Nowy paragraf
            Paragraph paragraph = new Paragraph("Rooms ready");
            paragraph.add(new Paragraph(" "));
            document.add(paragraph);
            //wolne pokoje
            String query1 = "Select numer, standard, typ, pietro from pokoje where status='0'";
            PdfPTable pdfTable1 = new PdfPTable(4);
            ArrayList<String> emptyRooms = new ArrayList<>();
            emptyRooms.add("numer");
            emptyRooms.add("standard");
            emptyRooms.add("typ");
            emptyRooms.add("pietro");
            makeRoomTable(document, pdfTable1, query1, emptyRooms, 4);
            //Nowy paragraf
            Paragraph paragraph2 = new Paragraph("Occupied rooms");
            paragraph2.add(new Paragraph(" "));
            document.add(paragraph2);
            //zajęte pokoje
            PdfPTable pdfTable2 = new PdfPTable(4);
            String query2 = "Select numer, standard, typ , pietro from pokoje where status='1'";
            makeRoomTable(document, pdfTable2, query2, emptyRooms, 4);
            //Nowy paragraf
            Paragraph paragraph3 = new Paragraph("Dirty rooms");
            paragraph3.add(new Paragraph(" "));
            document.add(paragraph3);
            //brudne pokoje
            PdfPTable pdfTable3 = new PdfPTable(4);
            String query3 = "Select numer, standard, typ , pietro from pokoje where status='2'";
            makeRoomTable(document, pdfTable3, query3, emptyRooms, 4);
            //Nowy paragraf
            Paragraph paragraph4 = new Paragraph("Departures");
            paragraph4.add(new Paragraph(" "));
            document.add(paragraph4);
            //wyjazdy
            PdfPTable pdfTable4 = new PdfPTable(4);
            String query4 = "Select p.numer, p.standard, p.typ , p.pietro from "
                    + "pokoje p, rezerwacje r where r.status=2 and r.data_k='"
                    + ObjectManager.GetInstance().currentData + "' and r.pokoj_id=p.pokoj_id";
            makeRoomTable(document, pdfTable4, query4, emptyRooms, 4);
            //Nowy paragraf
            Paragraph paragraph5 = new Paragraph("Arrivals");
            paragraph5.add(new Paragraph(" "));
            document.add(paragraph5);
            //przyjazdy
            PdfPTable pdfTable5 = new PdfPTable(4);
            String query5 = "Select p.numer, p.standard,"
                    + " p.typ , p.pietro from pokoje p, rezerwacje r where "
                    + "r.status=1 and r.data_p='" + ObjectManager.GetInstance().currentData
                    + "' and r.pokoj_id=p.pokoj_id";
            makeRoomTable(document, pdfTable5, query5, emptyRooms, 4);
            //nowy paragraf 
            Paragraph paragraph6 = new Paragraph("Employee working");
            paragraph6.add(new Paragraph(" "));
            document.add(paragraph6);
            //pracownicy w pracy
            List list = new List(true, false, 10);
            String query6 = "Select nazwisko from pracownicy where status=1";
            makePdfList(document, list, query6, "", "nazwisko");
            document.add(list);
            document.close();
            file.close();
        } catch (DocumentException | IOException e) {
            getAlertWindow("Wystąpił problem przy tworzeniu raportu");
        }
        openPdf(findPath("Rooms raport.pdf"));
    }

    /**
     * methods creates new statistics raport
     * @param startDate statistics raport start date
     * @param endDate statistics raport end date
     */
    public void generateStatistic(String startDate, String endDate) {
        try {
            Document document = new Document();
            OutputStream file = new FileOutputStream(new File("Statistics.pdf"));
            PdfWriter.getInstance(document, file);
            document.open();
            document.add(new Paragraph("Hotel statistics", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));
            document.add(new Paragraph(""));
            document.add(new Paragraph("Rooms statistics"));
            document.addAuthor("HotelMaster 2000");
            document.addCreationDate();
            document.addTitle("Statistics");
            Paragraph paragraph0 = new Paragraph("Statistics for days: " + startDate + " - " + endDate);
            paragraph0.add(new Paragraph(" "));
            document.add(paragraph0);

            LocalDate dateS = LocalDate.parse(startDate);
            LocalDate dateK = LocalDate.parse(endDate);

            while (dateK.plusDays(1).compareTo(dateS) != 0) {
                startDate = dateS.toString();
                //rooms statistics paragraph
                Paragraph paragraph = new Paragraph("");
                paragraph.add(new Paragraph("Statistics for day: " + startDate));
                document.add(paragraph);
                List list = new List(true, false, 10);
                //wszystkie zajete pokoje w danym daniu
                String query1 = "Select count( r.rezerwacja_id) as ilosc "
                        + "from rezerwacje r where '" + startDate + "' between r.data_p and r.data_k "
                        + "and (r.status=0 or r.status=2)";
                countAverageForSingleDay(document, list, query1, "ilosc");
                //wszystkie odwołane rezerwacje w danym dniu
                String query2 = "Select count( r.rezerwacja_id) as ilosc "
                        + "from rezerwacje r where '" + startDate + "'=r.data_p "
                        + "and r.status='-1'";
                makePdfList(document, list, query2, "Bookings canceled: ", "ilosc");
                //wszystkie rezerwacje w danym dniu
                String query3 = "Select count( r.rezerwacja_id) as ilosc "
                        + "from rezerwacje r where '" + startDate + "'=r.data_p "
                        + " and (r.status=0 or r.status=2)";
                makePdfList(document, list, query3, "Check-in: ", "ilosc");
                //wszystkie wymeldowaniew danym dniu
                String query4 = "Select count( r.rezerwacja_id) as ilosc "
                        + "from rezerwacje r where '" + startDate + "'=r.data_k "
                        + " and r.status=0";
                makePdfList(document, list, query4, "Check-out : ", "ilosc");
                document.add(list);
                Paragraph paragraph2 = new Paragraph("");
                paragraph2.add(new Paragraph(" "));
                document.add(paragraph2);
                dateS = dateS.plusDays(1);
                System.out.println(dateS);
            }
            document.close();
            file.close();
        } catch (DocumentException | IOException e) {
            getAlertWindow("Wystąpił problem przy tworzeniu statystyk");
        }
        openPdf(findPath("Statistics.pdf"));
    }

}
