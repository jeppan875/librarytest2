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
public class AddAuthorPage extends MenuPage{
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement firstNameField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement lastNameField;
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement countryField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement biographyField;
    @FindBy(css = "#add-author-button")
    private SelenideElement addAuthorButton;
    
    public void setFirstNameField(String firstName) {
        setTextFieldValue("first name", firstName,firstNameField);
    }
    public void setLastNameField(String lastName) {
        setTextFieldValue("last name", lastName,lastNameField);
    }
    public void setCountryField(String country) {
        setTextFieldValue("country name", country,countryField);
    }
    public void setBiographyField(String biography) {
        setTextFieldValue("biography name", biography,biographyField);
    }
    public void clickAddAuthorButton() {
        clickButton("add author button", addAuthorButton);
    }    
}
