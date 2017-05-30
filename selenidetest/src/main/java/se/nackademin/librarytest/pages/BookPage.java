/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author testautomatisering
 */
public class BookPage extends MenuPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement titleField;
    @FindBy(css = "#gwt-uid-5")
    SelenideElement authorField;
    @FindBy(css = "#gwt-uid-7")
    SelenideElement descriptionField;
    @FindBy(css = "#gwt-uid-9")
    SelenideElement isbnField;    
    @FindBy(css = "#gwt-uid-11")
    SelenideElement publishedDateField;
    @FindBy(css = "#gwt-uid-13")
    SelenideElement copiesAvailibleField;
    @FindBy(css = "#gwt-uid-15")
    SelenideElement totalNumberOfCopiesAvailibleField;
    
    @FindBy(css = "#edit-book-button")
    SelenideElement editBook;
    @FindBy(css = "#delete-book-button")
    SelenideElement deleteBook;
    @FindBy(css = "#borrow-book-button")            
    SelenideElement borrowBook;
    @FindBy(css = "#confirmdialog-ok-button")
    SelenideElement yesButtonDialogBox;
    @FindBy(css = "#return-book-button")
    SelenideElement returnBook;

    public String getTitle() {
        return getValue("title", titleField);         
    }

    public String getAuthor() {
        return getValue("author", authorField);        
    }

    public String getDescription() {
        return getValue("description", descriptionField);
    }

    public String getPublishedDate() {
        return getValue("published date",publishedDateField);
    }

    public String getCopiesAvailible() {
       return getValue("copies availible",copiesAvailibleField);
    }
    public String getTotalNumberOfCopiesAvailible() {
       return getValue("total copies availible",totalNumberOfCopiesAvailibleField);
    }
    
    public String getIsbn() {
       return getValue("isbn",isbnField);
    }
    public void navigateToEditBooks() {
        clickButton("edit book", editBook);
    }

    public void borrowBookButton() {
        clickButton("borrow book", borrowBook);
    }

    public void returnBookButton() {
        clickButton("return book", returnBook);
    }
    public void deleteBookButton() {
        clickButton("delete book", deleteBook);
    }
    public void confirmByYesButton() {
        clickButton("yes", yesButtonDialogBox);
    }
}
