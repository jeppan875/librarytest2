/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import com.jayway.restassured.response.Response;
import java.util.UUID;
import nackademin.se.rest.test.AuthorOperation;
import nackademin.se.rest.test.BookOperation;
import nackademin.se.rest.test.LoanOperation;
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.UserOperation;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.Container;
import nackademin.se.rest.test.models.Loan;
import nackademin.se.rest.test.models.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Ignore;
import org.junit.Test;
import static se.nackademin.rest.test.UsersTest.BASE_URL;


/**
 *
 * @author jesper
 */
public class LoansTest {
    final static String BASE_URL = "http://localhost:8080/librarytest-rest/loans/"; 

        @Test
    public void testGetAllLoans() {
        Response response = new ResponseOperation().getResponse(BASE_URL);
        assertEquals("should return status code 200",200, response.getStatusCode());        
    }
    
    
    @Test
    public void testNewLoan(){
        
        
        String authorUrl ="http://localhost:8080/librarytest-rest/authors/";
        AuthorOperation authorOperation = new AuthorOperation();
        Container authorContainer = new Container(authorOperation.createRandomAuthor());
        Response postAuthorResponse = new ResponseOperation().postResponse(authorUrl, authorContainer);
        int authorId = new ResponseOperation().getResponse(authorUrl).jsonPath().getInt("authors.author[-1].id"); 
        
        String bookUrl = "http://localhost:8080/librarytest-rest/books";
        BookOperation bookOperation = new BookOperation();
        Container bookContainer = new Container(bookOperation.createRandomBook(authorId));
        Response postBookResponse = new ResponseOperation().postResponse(bookUrl, bookContainer);
        int bookId = new ResponseOperation().getResponse(bookUrl).jsonPath().getInt("books.book[-1].id"); 
                
        String userUrl = "http://localhost:8080/librarytest-rest/users";
        UserOperation userOperation = new UserOperation();
        Container userContainer = new Container(userOperation.createRandomUser());
        Response postUserResponse = new ResponseOperation().postResponse(userUrl, userContainer);
        
        assertEquals("should return status code 201",201, postUserResponse.getStatusCode());
        assertEquals(userContainer.getUser().getDisplayName(), new ResponseOperation().getResponse(userUrl).jsonPath().getString("users.user[-1].displayName") );    
        
        int userId = new ResponseOperation().getResponse(userUrl).jsonPath().getInt("users.user[-1].id"); 
        
        LoanOperation loanOperation = new LoanOperation();
        Container container = new Container(loanOperation.newLoan(bookId,userId));   
        
        Response postResponse = new ResponseOperation().postResponse(BASE_URL, container);        
        assertEquals("should return status code 201",201, postResponse.getStatusCode());  
        
    }
    @Ignore 
    @Test
    public void testUpdateLoan(){
        
        LoanOperation loanOperation =new LoanOperation();
        loanOperation.postNewLoan();

        String userUrl = "http://localhost:8080/librarytest-rest/users";    
        int userId = new ResponseOperation().getResponse(userUrl).jsonPath().getInt("users.user[-1].id");        

        UserOperation userOperation = new UserOperation();
        userOperation.getUser(userId).setDisplayName("nisse");
        User user = userOperation.getUser(userId);
        Container Usercontainer = new Container(user);
                 
        Response putResponse = new ResponseOperation().putResponse(userUrl, Usercontainer);
        assertEquals("should return status code 200",200, putResponse.getStatusCode());
        
        int loanId = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("loans.loan[-1].id");  
        
        Loan loan = loanOperation.getLoan(loanId);;
//        loan.setUser(userId);
        Container container = new Container(loan);
                 
        Response response = new ResponseOperation().putResponse(BASE_URL, container);
        assertEquals("should return status code 200",200, response.getStatusCode());
//        assertNotEquals(dateBefore,new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("loans.loan.user[-1].dateDue"));

//        container.getLoan().setId(new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("loans.loan[-1].id")+1);
//        Response putAuthorNotFoundResponse = new ResponseOperation().putResponse(BASE_URL, container);
//        assertEquals("should return status code 404",404, putAuthorNotFoundResponse.getStatusCode());
    
    }    
}
