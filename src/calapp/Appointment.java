/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author swade
 */
public class Appointment {
    SimpleStringProperty apptTitle;
    SimpleStringProperty customerName;
    SimpleStringProperty customerContact;
    SimpleStringProperty startDate;
    SimpleStringProperty endDate;
    
    Appointment (String title, String name, String contact, String start, String end) {
        this.apptTitle = new SimpleStringProperty(title);
        this.customerName = new SimpleStringProperty(name);
        this.customerContact = new SimpleStringProperty(contact);
        this.startDate = new SimpleStringProperty(start);
        this.endDate = new SimpleStringProperty(end);
    }

    public String getApptTitle() {
        return apptTitle.get();
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public String getCustomerContact() {
        return customerContact.get();
    }

    public String getStartDate() {
        return startDate.get();
    }

    public String getEndDate() {
        return endDate.get();
    }
    
    
}
