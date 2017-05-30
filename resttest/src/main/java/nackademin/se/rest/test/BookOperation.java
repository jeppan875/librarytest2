/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nackademin.se.rest.test;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.http.ContentType.JSON;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;
import nackademin.se.rest.test.models.Author;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.Container;

/**
 *
 * @author jesper
 */
public class BookOperation {




    public Book getBook(int id) {
        Book book = given().accept(ContentType.JSON).get("http://localhost:8080/librarytest-rest/books/"+id).jsonPath().getObject("book",Book.class);
 
        return book;
    }    
    public Book createRandomBook(int authorId){
        
        Book book = new Book();
               
        AuthorOperation authorOperation = new AuthorOperation();
        
        book.setAuthor(authorOperation.getAuthor(authorId));
        book.setDescription(UUID.randomUUID().toString());
        book.setTitle(UUID.randomUUID().toString());
        book.setIsbn("0-575-01587-X");
        book.setNbrPages(new Random().nextInt(500));
        book.setTotalNbrCopies(50);
        book.setPublicationDate("1990-08-05");
        
        return book;
    }
    
    public void postNewBook(){
        String authorUrl ="http://localhost:8080/librarytest-rest/authors/";
        AuthorOperation authorOperation = new AuthorOperation();
        Container authorContainer = new Container(authorOperation.createRandomAuthor());
        new ResponseOperation().postResponse(authorUrl, authorContainer);

        int authorId = new ResponseOperation().getResponse(authorUrl).jsonPath().getInt("authors.author[-1].id"); 
        
        BookOperation bookOperation = new BookOperation();
        Container bookContainer = new Container(bookOperation.createRandomBook(authorId));
        new ResponseOperation().postResponse("http://localhost:8080/librarytest-rest/books", bookContainer);    
    }
 

}
