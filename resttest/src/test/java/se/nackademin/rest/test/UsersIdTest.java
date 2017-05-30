/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import com.jayway.restassured.response.Response;
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.UserOperation;
import nackademin.se.rest.test.models.Container;
import nackademin.se.rest.test.models.User;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jesper
 */
public class UsersIdTest {
final static String BASE_URL = "http://localhost:8080/librarytest-rest/users/";    
    @Test
    public void testFetchUser() {
        int id = 14;
        
        User user = new UserOperation().getUser(id);
        
        System.out.println("FirstName: "+user.getFirstName()); 
        System.out.println("LastName: "+user.getLastName());        
        System.out.println("Email: "+user.getEmail());
        System.out.println("DisplayName: "+user.getDisplayName());
        System.out.println("Role: "+user.getRole());  
        System.out.println("Phone: "+user.getPhone());
        System.out.println("Password: "+user.getPassword());
        
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 200",200, response.getStatusCode());    
    }
    @Test
    public void testFetchInvalidUser() {
        int id = 9999;
                
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, response.getStatusCode());    
    }
      @Test
    public void testDeleteUser() {        
        UserOperation userOperation = new UserOperation();
        Container container = new Container(userOperation.createRandomUser());
        Response postResponse = new ResponseOperation().postResponse(BASE_URL, container);
        assertEquals("should return status code 201",201, postResponse.getStatusCode());
        
        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("users.user[-1].id");
        
        Response response = new ResponseOperation().deleteResponse(BASE_URL+id);
        assertEquals("should return status code 204",204, response.getStatusCode());
        
        Response getResponse = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, getResponse.getStatusCode());          
    }     
}
