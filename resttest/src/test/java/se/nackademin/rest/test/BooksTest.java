/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.http.ContentType.JSON;
import com.jayway.restassured.response.Response;
import java.util.UUID;
import nackademin.se.rest.test.BookOperation;
import static org.junit.Assert.assertEquals;
import static com.jayway.restassured.path.json.JsonPath.*;
import nackademin.se.rest.test.AuthorOperation;
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.models.Author;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.Container;
import static org.junit.Assert.assertNotEquals;
import org.junit.Ignore;

import org.junit.Test;

/**
 *
 * @author jesper
 */
public class BooksTest {

     final static String BASE_URL = "http://localhost:8080/librarytest-rest/books";
    
    public BooksTest() {
    }

    @Test
    public void testGetAllBooks() {
        Response response = new ResponseOperation().getResponse(BASE_URL);
        assertEquals("should return status code 200",200, response.getStatusCode());        
    } 


    @Test
    public void testAddNewBook() {
        
        String authorUrl ="http://localhost:8080/librarytest-rest/authors/";
        AuthorOperation authorOperation = new AuthorOperation();
        Container authorContainer = new Container(authorOperation.createRandomAuthor());
        Response response = new ResponseOperation().postResponse(authorUrl, authorContainer);
        assertEquals("should return status code 201",201, response.getStatusCode());

        int authorId = new ResponseOperation().getResponse(authorUrl).jsonPath().getInt("authors.author[-1].id"); 
        
        BookOperation bookOperation = new BookOperation();
        Container bookContainer = new Container(bookOperation.createRandomBook(authorId));
                
        Response postResponse = new ResponseOperation().postResponse(BASE_URL, bookContainer);
        
        assertEquals("should return status code 201",201, postResponse.getStatusCode());
        assertEquals(bookContainer.getBook().getTitle(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("books.book[-1].title") );
        assertEquals(bookContainer.getBook().getDescription(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("books.book[-1].description") );    
        assertEquals(bookContainer.getBook().getIsbn(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("books.book[-1].isbn") ); 
        assertEquals((long)bookContainer.getBook().getNbrPages(),(long) new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].nbrPages") ); 
        assertEquals((long)bookContainer.getBook().getTotalNbrCopies(),(long) new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].totalNbrCopies") ); 
        assertEquals(bookContainer.getBook().getPublicationDate(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("books.book[-1].publicationDate") ); 
        
        bookContainer.getBook().setId(new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].id"));
        Response postExistingBookIdResponse = new ResponseOperation().postResponse(BASE_URL, bookContainer);
        assertEquals("should return status code 400",400, postExistingBookIdResponse.getStatusCode());        
  
    }

    @Test
    public void testUpdateBook() {

        BookOperation bookOperation = new BookOperation();
        bookOperation.postNewBook();
        
        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].id");  
        
        Book book = bookOperation.getBook(id);
        String descriptionBefore = bookOperation.getBook(id).getDescription();
        book.setDescription("dgfgd");
        book.setAuthor(book.getAuthor());
        Container singleBook = new Container(book);
                 
        Response putResponse = new ResponseOperation().putResponse(BASE_URL, singleBook);
        assertEquals("should return status code 200",200, putResponse.getStatusCode());
        assertNotEquals(descriptionBefore, new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("books.book[-1].description") );

        singleBook.getBook().setId(new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].id")+1);
        Response putBookNotFoundResponse = new ResponseOperation().putResponse(BASE_URL, singleBook);
        assertEquals("should return status code 404",404, putBookNotFoundResponse.getStatusCode());
    }    
}
