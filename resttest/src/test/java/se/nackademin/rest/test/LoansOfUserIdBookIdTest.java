/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import com.jayway.restassured.response.Response;
import nackademin.se.rest.test.AuthorOperation;
import nackademin.se.rest.test.BookOperation;
import nackademin.se.rest.test.LoanOperation;
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.UserOperation;
import nackademin.se.rest.test.models.Container;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jesper
 */
public class LoansOfUserIdBookIdTest {
final static String BASE_URL = "http://localhost:8080/librarytest-rest/loans/ofuser/";    

@Test
public void testGetBookBorrowedByUser(){
   
        LoanOperation loanOperation =new LoanOperation();
        loanOperation.postNewLoan();
        
        String userUrl = "http://localhost:8080/librarytest-rest/users";    
        String bookUrl = "http://localhost:8080/librarytest-rest/books";
        int bookId = new ResponseOperation().getResponse(bookUrl).jsonPath().getInt("books.book[-1].id");       
        int userId = new ResponseOperation().getResponse(userUrl).jsonPath().getInt("users.user[-1].id");
        
        Response response = new ResponseOperation().getResponse(BASE_URL+userId+"/ofbook/"+bookId);
        assertEquals("should return status code 200",200, response.getStatusCode());  
}
@Test
public void testGetInvalidBookBorrowedByUser(){
        LoanOperation loanOperation =new LoanOperation();
        loanOperation.postNewLoan();
        
        String userUrl = "http://localhost:8080/librarytest-rest/users";
        int userId = new ResponseOperation().getResponse(userUrl).jsonPath().getInt("users.user[-1].id");
        int bookId=9999;
        Response response = new ResponseOperation().getResponse(BASE_URL+userId+"/ofbook/"+bookId);
        assertEquals("should return status code 404",404, response.getStatusCode());  
}  
}
