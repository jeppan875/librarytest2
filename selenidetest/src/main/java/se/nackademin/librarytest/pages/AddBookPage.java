/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author jesper
 */
public class AddBookPage extends MenuPage {
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement titleField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement descriptionField;
    @FindBy(css = "#gwt-uid-11")
    private SelenideElement numberOfPagesField;
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement isbnField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement numberInInventoryField;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement datePublishedField;
    @FindBy(css = "#add-book-button")
    private SelenideElement addBookButton;
    
    public void setTitleField(String title) {
        setTextFieldValue("title", title,titleField);
    }
    public void setDescriptionField(String description) {
        setTextFieldValue("description", description,descriptionField);
    }
    public void setNumberOfPagesField(String numberOfPages) {
        setTextFieldValue("numberOfPagesField", numberOfPages,numberOfPagesField);
    }
    public void setNumberInInventoryField(String numberInInventory) {
        setTextFieldValue("numberInInventoryField", numberInInventory,numberInInventoryField);
    }    
    public void setIsbnField(String isbn) {
        setTextFieldValue("isbn", isbn,isbnField);
    }
    public void setDatePublishedField(String datePublished) {
        setTextFieldValue("datePublished", datePublished,datePublishedField);
    }   
    public void clickAddBookButton() {
        clickButton("add book button", addBookButton);
    }      

}
