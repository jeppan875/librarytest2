/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import com.jayway.restassured.response.Response;
import nackademin.se.rest.test.AuthorOperation;
import nackademin.se.rest.test.BookOperation;
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.models.Author;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.Container;
import org.junit.Test;
import static org.junit.Assert.*;
import static se.nackademin.rest.test.BooksTest.BASE_URL;

/**
 *
 * @author jesper
 */
public class BooksIdTest {
    
    final static String BASE_URL = "http://localhost:8080/librarytest-rest/books/";     
    public BooksIdTest() {
    }
    @Test
    public void testFetchBook() {
        BookOperation bookOperation = new BookOperation();
        bookOperation.postNewBook();       
        int bookId = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].id");
        
        Book book = new BookOperation().getBook(bookId);
        System.out.println("Title: "+book.getTitle()); 
        System.out.println("Author: "+book.getAuthor());        
        System.out.println("Description: "+book.getDescription());
        System.out.println("Number of pages: "+book.getNbrPages());
        System.out.println("Isbn: "+book.getIsbn());  
        
        Response response = new ResponseOperation().getResponse(BASE_URL+bookId);
        assertEquals("should return status code 200",200, response.getStatusCode());    
    }    
      @Test
    public void testGetInvalidBook() {        
        int id = 9999;
        
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, response.getStatusCode());    
    }
      @Test
    public void testDeleteBook() {
        BookOperation bookOperation = new BookOperation();
        bookOperation.postNewBook();       
        int bookId = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].id");
        
        Response response = new ResponseOperation().deleteResponse(BASE_URL+bookId);
        assertEquals("should return status code 204",204, response.getStatusCode());
        
        Response getResponse = new ResponseOperation().getResponse(BASE_URL+bookId);
        assertEquals("should return status code 404",404, getResponse.getStatusCode());          
    }   
}
