/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import com.jayway.restassured.response.Response;
import nackademin.se.rest.test.AuthorOperation;
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.models.Author;
import nackademin.se.rest.test.models.Container;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jesper
 */
public class AuthorsIdTest {
    
    final static String BASE_URL = "http://localhost:8080/librarytest-rest/authors/";   
    
    public AuthorsIdTest() {
    }
    @Test
    public void testFetchAuthor() {
        int id = 5;
        
        Author author = new AuthorOperation().getAuthor(id);
        System.out.println("Name"+author.getFirstName());
        
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 200",200, response.getStatusCode());    
    }
    @Test    
    public void testGetInvalidAuthorReturn404() {
        int id = 9999;
        
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, response.getStatusCode());    
    }
      @Test
    public void deleteBook() {        
        AuthorOperation authorOperation = new AuthorOperation();
        Container singleBook = new Container(authorOperation.createRandomAuthor());
                
        Response postResponse = new ResponseOperation().postResponse(BASE_URL, singleBook);
        assertEquals("should return status code 201",201, postResponse.getStatusCode());
        
        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("authors.author[-1].id");
        
        Response response = new ResponseOperation().deleteResponse(BASE_URL+id);
        assertEquals("should return status code 204",204, response.getStatusCode());
        
        Response getResponse = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, getResponse.getStatusCode());          
    }    
}
