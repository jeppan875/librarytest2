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
public class EditMyProfilePage extends MenuPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement userNameField;
    @FindBy(css = "#gwt-uid-5")
    SelenideElement passwordField;
    @FindBy(css = "#gwt-uid-7")
    SelenideElement firstNameField;
    @FindBy(css = "#gwt-uid-9")
    SelenideElement lastNameField;
    @FindBy(css = "#gwt-uid-11")
    SelenideElement phoneField;
    @FindBy(css = "#gwt-uid-13")
    SelenideElement emailField;    
    @FindBy(css = "#save-user-button")
    SelenideElement saveUserButton;

    public void setUsername(String username) {
        setTextFieldValue("user name field", username, userNameField);
    }

    public void setPassword(String password) {
        setTextFieldValue("password field", password, passwordField);
    }
    public void setFirstName(String firstName) {
        setTextFieldValue("first name field", firstName, firstNameField);
    }
    public void setLastName(String lastName) {
        setTextFieldValue("last name field", lastName, lastNameField);
    }    
    public void setPhone(String phone) {
        setTextFieldValue("phone field", phone, phoneField);
    }
    public void setEmail(String email) {
        setTextFieldValue("password field", email, emailField);
    }    
    public void saveUserButton() {
        clickButton("add user button", saveUserButton);
    }
}
