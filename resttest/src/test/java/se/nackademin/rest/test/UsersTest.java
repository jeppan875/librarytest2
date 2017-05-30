/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import com.jayway.restassured.response.Response;
import java.util.UUID;
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.UserOperation;
import nackademin.se.rest.test.models.Container;
import nackademin.se.rest.test.models.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jesper
 */
public class UsersTest {
final static String BASE_URL = "http://localhost:8080/librarytest-rest/users";


@Test
public void testGetAllUsers()
{
        Response response = new ResponseOperation().getResponse(BASE_URL);
        assertEquals("should return status code 200",200, response.getStatusCode()); 
}

@Test
public void testAddUser()
{
        UserOperation userOperation = new UserOperation();
        Container container = new Container(userOperation.createRandomUser());
                
        Response postResponse = new ResponseOperation().postResponse(BASE_URL, container);
        
        assertEquals("should return status code 201",201, postResponse.getStatusCode());
        assertEquals(container.getUser().getFirstName(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("users.user[-1].firstName") );
        assertEquals(container.getUser().getLastName(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("users.user[-1].lastName") );    
        assertEquals(container.getUser().getDisplayName(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("users.user[-1].displayName") ); 
        assertEquals(container.getUser().getPassword(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("users.user[-1].password") ); 
        assertEquals(container.getUser().getPhone(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("users.user[-1].phone") ); 
        assertEquals(container.getUser().getEmail(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("users.user[-1].email") );         

}
@Test
public void testUpdateUser()
{
        UserOperation userOperation = new UserOperation();
        Container postContainer = new Container(userOperation.createRandomUser());
                
        Response postResponse = new ResponseOperation().postResponse(BASE_URL, postContainer);
        assertEquals("should return status code 201",201, postResponse.getStatusCode());
        assertEquals(postContainer.getUser().getDisplayName(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("users.user[-1].displayName") );    
        
        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("users.user[-1].id");  
        
        User user = userOperation.getUser(id);
        user.setDisplayName(UUID.randomUUID().toString());
        Container container = new Container(user);
                 
        Response putResponse = new ResponseOperation().putResponse(BASE_URL, container);
        assertEquals("should return status code 200",200, putResponse.getStatusCode());
        assertNotEquals(postContainer.getUser().getDisplayName(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("users.user[-1].displayName") );

        container.getUser().setId(new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("users.user[-1].id")+1);
        Response putBookNotFoundResponse = new ResponseOperation().putResponse(BASE_URL, container);
        assertEquals("should return status code 404",404, putBookNotFoundResponse.getStatusCode());    
}
}
