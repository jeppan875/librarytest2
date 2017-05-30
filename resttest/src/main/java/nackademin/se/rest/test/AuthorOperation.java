/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nackademin.se.rest.test;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import java.util.UUID;
import nackademin.se.rest.test.models.Author;

/**
 *
 * @author jesper
 */
public class AuthorOperation {
     public Author getAuthor(int id) {
        Author author = given().accept(ContentType.JSON).get("http://localhost:8080/librarytest-rest/authors/"+id).jsonPath().getObject("author",Author.class);
 
        return author;
    }   
    public Author createRandomAuthor(){

        Author author = new Author();
        author.setFirstName(UUID.randomUUID().toString().substring(0, 10));
        author.setLastName(UUID.randomUUID().toString().substring(0, 15));
        author.setCountry(UUID.randomUUID().toString().substring(0, 17));
        author.setBio(UUID.randomUUID().toString());
        
        return author;       
                
    }     
}
