/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

/**
 *
 * @author swade
 */
public class User {
    private String userName;
    private String password;
         
    
    User(String username) {
       String userName = username;
 
    }
    
    public String getUserName(){
        return userName;
    }
    
    public void setUserName(String username){
        this.userName = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}
