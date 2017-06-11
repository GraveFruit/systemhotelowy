/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainwindow;

import base.service.BookingService;
import base.service.CheckData;
import base.service.DataService;
import base.service.EmployeeService;
import base.service.GuestService;
import base.service.LoginService;
import base.service.OfferService;
import base.service.RaportService;
import base.service.RoomService;
import base.service.TaskService;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Grzesiek
 */
public class ObjectManager {

    private static ObjectManager objectmanager = null;

    public OfferService offerservice;
    public GuestService guestservice;
    public EmployeeService employeeservice;
    public BookingService bookingservice;
    public TaskService taskservice;
    public RoomService roomservice;
    public String currentData;
    public LoginService loginservice;
    public RaportService raportservice;
    public DataService dataservice;
    public CheckData checkdata;

    private ObjectManager() {
        offerservice = new OfferService();
        guestservice = new GuestService();
        employeeservice = new EmployeeService();
        taskservice = new TaskService();
        bookingservice = new BookingService();
        roomservice = new RoomService();
        currentData = LocalDate.now().toString();
        loginservice = new LoginService();
        raportservice = new RaportService();
        dataservice= new DataService();
        checkdata= new CheckData();
    }

    public static ObjectManager GetInstance() {
        if (objectmanager == null) {
            objectmanager = new ObjectManager();
        }

        return objectmanager;
    }
}
