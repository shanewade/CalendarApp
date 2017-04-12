/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.time.LocalTime;

/**
 *
 * @author swade
 */
public final class GlobalDataStore {
    private static GlobalDataStore instance = null;
    private String loggedInUser;
    private LocalTime businessHoursStart  = LocalTime.parse("08:00");
    private LocalTime businessHoursEnd = LocalTime.parse("17:00");
    
    
    public GlobalDataStore() {
    }
    
    public static GlobalDataStore getInstance(){
        if (instance == null) {
            instance = new GlobalDataStore();
        }
        return instance;
        
    }
    
    public String getLoggedInUser() {
        return loggedInUser;
    }
    
    public void setLoggedInUser(String username) {
        loggedInUser = username;
    }
    
    public LocalTime getBusinessStartTime() {
        return businessHoursStart;
        
    }
    
    public LocalTime getBusinessEndTime() {
        return businessHoursEnd;
        
    }

}
