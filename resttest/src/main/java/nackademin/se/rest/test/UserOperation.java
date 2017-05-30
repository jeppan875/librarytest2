/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nackademin.se.rest.test;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import java.util.UUID;
import nackademin.se.rest.test.models.User;

/**
 *
 * @author jesper
 */
public class UserOperation {
    public User getUser(int id) {
        String url = "http://localhost:8080/librarytest-rest/";        
        User user = given().accept(ContentType.JSON).get(url+"users/"+id).jsonPath().getObject("user",User.class);
 
        return user;
    }    
    
    public User createRandomUser(){

        User users = new User();
        
        users.setFirstName(UUID.randomUUID().toString());
        users.setLastName(UUID.randomUUID().toString());
        users.setEmail(UUID.randomUUID().toString());
        users.setPassword(UUID.randomUUID().toString());
        users.setDisplayName(UUID.randomUUID().toString());
        users.setRole("LOANER");
        users.setPhone(UUID.randomUUID().toString());
        
        return users;       
                
    }     
}
