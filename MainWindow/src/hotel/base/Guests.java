/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import mainwindow.FXMLDocumentController;

/**
 *
 * @author Grzesiek
 */
public class Guests {
    private StringProperty name_guest;
    private StringProperty surname_guest;
    private StringProperty phone_guest;
    private StringProperty pesel_guest;
    private StringProperty edition_guest=null;
    
      public Guests( String name, String surname, String phone, String pesel, String edit) {
        this.name_guest = new SimpleStringProperty( name);
        this.surname_guest =new SimpleStringProperty( surname);
        this.phone_guest = new SimpleStringProperty( phone);
        this.pesel_guest =new SimpleStringProperty( pesel);
        this.edition_guest=new SimpleStringProperty(edit);
}

  

    public String getName_guest() {
        return name_guest.getValue();
    }

    public String getSurname_guest() {
        return surname_guest.getValue();
    }

    public String getPhone_guest() {
        return phone_guest.getValue();
    }

    public String getPesel_guest() {
        return pesel_guest.getValue();
    }

    public Button getEdition_guest() {
        return FXMLDocumentController.makeEditGuestsButton();
    }
 
    public static ObservableList<Guests> getData() {
        try {
        ObservableList<Guests> employee_list = FXCollections.observableArrayList();
        Statement statement = DataBase.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select imie, nazwisko, pesel, tel from klienci");
        while(result.next()){
            //int id = result.getInt("id");
            String imie = result.getString("imie");
            String naz = result.getString("nazwisko");
            String pesel= result.getString("pesel");
            String telefon = result.getString("tel");
            String ed = null;
            
            employee_list.add(new Guests(imie,naz,pesel,telefon,ed));
            
        }
        
            return FXCollections.observableArrayList(employee_list);
        }catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
   return null;
     } 
      
}
