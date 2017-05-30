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
public class AuthorPage extends MenuPage {

    @FindBy(css = "#gwt-uid-3")
    private SelenideElement nameField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement countryField;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement biographyField;
    
    @FindBy(css = "#edit-author-button")
    private SelenideElement editAuthorButton;
    @FindBy(css = "#delete-author-button")
    private SelenideElement deleteAuthorButton;    
    @FindBy(css = "#confirmdialog-ok-button")
    SelenideElement yesButtonDialogBox; 
    
    public String getName() {
        return getValue("name", nameField);
    }

    public String getCountry() {
        return getValue("country", countryField);

    }

    public String getBiography() {
        return getValue("biography", biographyField);

    }
    public void clickEditAuthorButton() {
        clickButton("edit author button", editAuthorButton);
    }
    public void clickDeleteAuthorButton() {
        clickButton("delete author button", deleteAuthorButton);
    }
    public void confirmByYesButton() {
        clickButton("yes", yesButtonDialogBox);
    }    
}
