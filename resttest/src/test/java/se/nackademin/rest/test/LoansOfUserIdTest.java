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
public class LoansOfUserIdTest {

final static String BASE_URL = "http://localhost:8080/librarytest-rest/loans/ofuser/";    

@Test
public void testGetAllLoansByUser(){
        LoanOperation loanOperation =new LoanOperation();
        loanOperation.postNewLoan();

        String userUrl = "http://localhost:8080/librarytest-rest/users";         
        int userId = new ResponseOperation().getResponse(userUrl).jsonPath().getInt("users.user[-1].id");
        
        Response response = new ResponseOperation().getResponse(BASE_URL+userId);
        assertEquals("should return status code 200",200, response.getStatusCode());  
}
@Test
public void testGetLoansByUserInvalid(){

        int id=9999;
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, response.getStatusCode());  
}
    
}
