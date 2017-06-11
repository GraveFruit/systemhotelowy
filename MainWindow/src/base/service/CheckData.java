/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import java.time.LocalDate;

/**
 *
 * @author Grzesiek
 */
public class CheckData {
    
    /**
     * method checks if input value is number
     * @param number String value
     * @return true if String value is number
     */
    public boolean isNumber(String number){
     return number.matches("\\d*");   
    }
    
    /**
     * method checks if input value is name or surname
     * @param name String value
     * @return true if String value is name or surname
     */
    public boolean isName(String name){
        return name.matches("\\p{L}*");
    }
    
    /**
     * method checks if input value is pesel
     * @param pesel String value
     * @return true if String value is pesel 
     */
    public boolean isPesel(String pesel){
        return pesel.matches("[0-9]{11}");    
     }
     
    /**
     * method checks if input value is phone number
     * @param phone String value
     * @return true if String value is phone number
     */
    public boolean isPhone(String phone){
         return phone.matches("^[0-9]{7,15}$");
     }
     
    /**
     *method checks if  two input value are the same password
     * @param password1 first password
     * @param password2 second password
     * @return true if passwords are the same
     */
    public int checkPasswords(String password1, String password2){
         return password1.compareTo(password2);
     }
     
     
    /**
     * method checks if rooms is ready, return 1 when room is not ready, 2 when 
     * there is not done task for this room, 0 when room is ready
     * @param status rooms status
     * @param tasks tasks quantity for this room 
     * @return true if room is ready (status=0 and task count=0)
     */
    public boolean checkRoomReady(String status,String tasks){
         if(status.compareTo("0")!=0){
             return false;
         }else return tasks.isEmpty();
         
     }
     
    /**
     * method counts round to 2 decimal places for double number
     * @param number double number
     * @return rounded number
     */
    public double roundNumber(double number) {
        double przyb = Math.round(number * 100) / 100.0;
        return przyb;
    }
    
    /**
     * method counts average for sum of set
     * @param count the sum of numbers set
     * @param quantity set size
     * @return average number
     */
    public double countAverage(double count, double quantity){
        double average= count / quantity * 100;
        return average;
    }
}