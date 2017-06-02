package se.nackademin.librarytest;

import static com.codeborne.selenide.Selenide.*;

import java.util.UUID;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.BookHelper;
import se.nackademin.librarytest.helpers.LoanHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.model.User;


public class SelenideTest extends TestBase {

    public SelenideTest() {
    }

    @Test
    public void createNewBookTest() throws InterruptedException {

    Book newBook= BookHelper.addNewBook();
    String titleOfNewBook = newBook.getTitle();
    sleep(1000);
    Book confirmBook = BookHelper.searchAndFetchBook(titleOfNewBook);
        
    assertEquals("equals ",newBook.getTitle(),confirmBook.getTitle());
    assertEquals("equals ",newBook.getAuthor(),confirmBook.getAuthor());
    assertEquals("equals ",newBook.getDescription(),confirmBook.getDescription());
    assertEquals("equals ",newBook.getIsbn(),confirmBook.getIsbn());
    assertEquals("equals ",newBook.getTotalNumberOfCopiesAvailible(),confirmBook.getTotalNumberOfCopiesAvailible());
    
    Book editBook = BookHelper.editAndReturnBook(titleOfNewBook);
    String editBookTitle = editBook.getTitle();
    sleep(3000);
    confirmBook = BookHelper.searchAndFetchBook(editBookTitle);
    
    assertEquals("equals ",editBook.getTitle(),confirmBook.getTitle());
    assertEquals("equals ",editBook.getAuthor(),confirmBook.getAuthor());
    assertEquals("equals ",editBook.getDescription(),confirmBook.getDescription());
    assertEquals("equals ",editBook.getIsbn(),confirmBook.getIsbn());
    assertEquals("equals ",editBook.getTotalNumberOfCopiesAvailible(),confirmBook.getTotalNumberOfCopiesAvailible()); 
    BookHelper.deleteBook();

    
    }
  
    @Test
    public void createNewAuthorTest() throws InterruptedException {

    Author newAuthor= AuthorHelper.addNewRandomAuthor();
    String nameNewAuthor = newAuthor.getFirstName()+" "+newAuthor.getLastName();
    Author confirmAuthor = AuthorHelper.searchAndFetchAuthor(nameNewAuthor);
    
    assertEquals("equals ",newAuthor.getFirstName(),confirmAuthor.getFirstName());
    assertEquals("equals ",newAuthor.getLastName(),confirmAuthor.getLastName());
    assertEquals("equals ",newAuthor.getCountry(),confirmAuthor.getCountry());
    assertEquals("equals ",newAuthor.getBiography(),confirmAuthor.getBiography());    
 
    Author editAuthor = AuthorHelper.editAndReturnAuthor(nameNewAuthor);
    String editAuthorName = editAuthor.getFirstName()+" "+editAuthor.getLastName();
    confirmAuthor = AuthorHelper.searchAndFetchAuthor(editAuthorName); 
    
    assertEquals("equals ",editAuthor.getFirstName(),confirmAuthor.getFirstName());
    assertEquals("equals ",editAuthor.getLastName(),confirmAuthor.getLastName());
    assertEquals("equals ",editAuthor.getCountry(),confirmAuthor.getCountry());
    assertEquals("equals ",editAuthor.getBiography(),confirmAuthor.getBiography()); 
    
    sleep(2000);
    AuthorHelper.deleteAuthor();
    
    }

    @Test
    public void createNewUserTest() {
        User newUser = UserHelper.createNewRandomUser();

        UserHelper.logInAsUser(newUser.getDisplayName(), newUser.getPassword());
        User confirmUser = UserHelper.fetchUser();

        assertEquals("equals ", newUser.getDisplayName(), confirmUser.getDisplayName());
        assertEquals("equals ", newUser.getFirstName(), confirmUser.getFirstName());
        assertEquals("equals ", newUser.getLastName(), confirmUser.getLastName());
        assertEquals("equals ", newUser.getPhone(), confirmUser.getPhone());
        assertEquals("equals ", newUser.getEmail(), confirmUser.getEmail());
        
        User updateUser = UserHelper.updateUser();
        UserHelper.logInAsUser(updateUser.getDisplayName(), updateUser.getPassword());
        confirmUser = UserHelper.fetchUser();
        
        assertEquals("equals ", updateUser.getDisplayName(), confirmUser.getDisplayName());
        assertEquals("equals ", updateUser.getFirstName(), confirmUser.getFirstName());
        assertEquals("equals ", updateUser.getLastName(), confirmUser.getLastName());
        assertEquals("equals ", updateUser.getPhone(), confirmUser.getPhone());
        assertEquals("equals ", updateUser.getEmail(), confirmUser.getEmail());        
    } 

        @Test
    public void borrowAndReturnBookTest() {
        
        Book newBook= BookHelper.addNewBook();
        sleep(3000);
        
        User newUser = UserHelper.createNewSimpleRandomUser();
        UserHelper.logInAsUser(newUser.getDisplayName(), newUser.getPassword());
                
        int copiesBeforeBorrow = Integer.parseInt(newBook.getTotalNumberOfCopiesAvailible());
        String titleNewBook = newBook.getTitle();
        
        LoanHelper.borrowBook(titleNewBook);
        sleep(5000);
        int copiesAfterBorrow = Integer.parseInt(LoanHelper.copiesAvailible());
        
        assertEquals(" equals",copiesBeforeBorrow-1 , copiesAfterBorrow);
        
        LoanHelper.returnBook(titleNewBook);
        sleep(2000);
        
        int copiesAfterReturn= Integer.parseInt(LoanHelper.copiesAvailible());
        
        assertEquals(" equals",copiesBeforeBorrow , copiesAfterReturn);
        
    }
     
}
