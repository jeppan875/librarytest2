/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import com.codeborne.selenide.SelenideElement;

/**
 *
 * @author jesper
 */
public class List {
    SelenideElement rootElement;//".v-select-twincol-options"

    public List(SelenideElement rootElement) {
        this.rootElement = rootElement;    
    }
    private SelenideElement getOption(int option) {
        return rootElement.$("option", option);

    }
    public void clickOption(int option) {
        getOption(option).doubleClick();
    }
    public String getOptionValue(int option) {
        return getOption(option).getText();
    }    
    
}
