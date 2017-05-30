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
public class BrowseAuthorPage extends MenuPage{
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement authorField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement countryField;    
    @FindBy(css = "#search-authors-button")
    private SelenideElement searchAuthorsButton;
    @FindBy(css = "td.v-grid-cell:nth-child(1) > a:nth-child(1)")
    private SelenideElement firstResultAuthor;

    public void clickFirstResultAuthor() {
         clickButton("first result",firstResultAuthor);
    }

    public void setAuthorField(String author) {
        setTextFieldValue("author field",author, authorField);

    }
    public void setCountryField(String country) {
        setTextFieldValue("country field",country, countryField);
    }
    public void clickSearchAuthorsButton() {
        clickButton("search author",searchAuthorsButton);
    }    
}
