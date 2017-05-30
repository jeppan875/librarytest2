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
public class MyProfilePage extends MenuPage {
    @FindBy(css = "#gwt-uid-5")
    SelenideElement userNameField;
    @FindBy(css = "#gwt-uid-7")
    SelenideElement firstNameField;
    @FindBy(css = "#gwt-uid-9")
    SelenideElement lastNameField; 
    @FindBy(css = "#gwt-uid-11")
    SelenideElement phoneField;     
    @FindBy(css = "#gwt-uid-13")
    SelenideElement emailField;    
    @FindBy(css = "#edit-user")
    SelenideElement editUserButton;
    
    public String getUserName() {
       return getValue("User name",userNameField);
    }
    public String getFirstName() {
       return getValue("first name",firstNameField);
    }
    public String getLastName() {
       return getValue("last name",lastNameField);
    }
    public String getPhone() {
       return getValue("phone",phoneField);
    }    
    public String getEmail() {
       return getValue("Email",emailField);
    }    
    public void navigateToEditUser() {
        clickButton("edit user",editUserButton);
    }
    
}
