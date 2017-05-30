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
import static se.nackademin.rest.test.LoansIdTest.BASE_URL;


/**
 *
 * @author jesper
 */
public class LoansOfBookIdTest {

final static String BASE_URL = "http://localhost:8080/librarytest-rest/loans/ofbook/";  

@Test
public void testGetAllLoansByBook(){
        LoanOperation loanOperation =new LoanOperation();
        loanOperation.postNewLoan();

        int bookId = new ResponseOperation().getResponse("http://localhost:8080/librarytest-rest/books").jsonPath().getInt("books.book[-1].id");  
        
        Response response = new ResponseOperation().getResponse(BASE_URL+bookId);
        assertEquals("should return status code 200",200, response.getStatusCode());  
}

}
