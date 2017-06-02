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
import nackademin.se.rest.test.models.Loan;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jesper
 */
public class LoansIdTest {

    final static String BASE_URL = "http://localhost:8080/librarytest-rest/loans/";
    
    @Test
    public void testGetLoansOfUserById()
    {

        LoanOperation loanOperation =new LoanOperation();
        loanOperation.postNewLoan();
                
        int loanId = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("loans.loan[-1].id");        

        Loan loan = new LoanOperation().getLoan(loanId);
        System.out.println("book: "+loan.getBook()); 
        System.out.println("user: "+loan.getUser());        
        System.out.println("date borrowed: "+loan.getDateBorrowed());
        System.out.println("date due: "+loan.getDateDue());
          
        
        Response response = new ResponseOperation().getResponse(BASE_URL+loanId);
        assertEquals("should return status code 200",200, response.getStatusCode());         
    }
      @Test
    public void testGetInvalidLoanReturn404() {        
        int id = 9999;
        
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, response.getStatusCode());    
    }
      @Test
    public void testDeleteLoan() {        
        
        LoanOperation loanOperation =new LoanOperation();
        loanOperation.postNewLoan();
        loanOperation.postNewLoan();
        
        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("loans.loan[-1].id");
        
        Response response = new ResponseOperation().deleteResponse(BASE_URL+id);
        assertEquals("should return status code 204",204, response.getStatusCode());
        
        Response getResponse = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, getResponse.getStatusCode());          
    }     
    
}
