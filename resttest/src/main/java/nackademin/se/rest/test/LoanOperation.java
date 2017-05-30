/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nackademin.se.rest.test;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import java.util.UUID;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.Container;
import nackademin.se.rest.test.models.Loan;

/**
 *
 * @author jesper
 */
public class LoanOperation {
    
    public Loan newLoan(int bookId, int userId ){
                
    Loan loan = new Loan();
    
    BookOperation bookOperation = new BookOperation();
    UserOperation userOperation = new UserOperation();
    
    loan.setBook(bookOperation.getBook(bookId));
    loan.setDateBorrowed(UUID.randomUUID().toString());
    loan.setDateDue(UUID.randomUUID().toString());
    loan.setUser(userOperation.getUser(userId));
    
    return loan;
    
    }
    public Loan getLoan(int id) {
        
        Loan loan = given().accept(ContentType.JSON).get("http://localhost:8080/librarytest-rest/loans/"+id).jsonPath().getObject("loan",Loan.class);
 
        return loan;
    }
    public void postNewLoan(){
        String authorUrl ="http://localhost:8080/librarytest-rest/authors/";
        AuthorOperation authorOperation = new AuthorOperation();
        Container authorContainer = new Container(authorOperation.createRandomAuthor());
        new ResponseOperation().postResponse(authorUrl, authorContainer);
        int authorId = new ResponseOperation().getResponse(authorUrl).jsonPath().getInt("authors.author[-1].id"); 
        
        String bookUrl = "http://localhost:8080/librarytest-rest/books";
        BookOperation bookOperation = new BookOperation();
        Container bookContainer = new Container(bookOperation.createRandomBook(authorId));
        new ResponseOperation().postResponse(bookUrl, bookContainer);
        int bookId = new ResponseOperation().getResponse(bookUrl).jsonPath().getInt("books.book[-1].id"); 
                
        String userUrl = "http://localhost:8080/librarytest-rest/users";
        UserOperation userOperation = new UserOperation();
        Container userContainer = new Container(userOperation.createRandomUser());
        new ResponseOperation().postResponse(userUrl, userContainer);        
        int userId = new ResponseOperation().getResponse(userUrl).jsonPath().getInt("users.user[-1].id");

        LoanOperation loanOperation = new LoanOperation();
        Container container = new Container(loanOperation.newLoan(bookId,userId));   
        
        new ResponseOperation().postResponse("http://localhost:8080/librarytest-rest/loans/", container); 
    
    }
}
