/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import java.util.UUID;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.pages.AddAuthorPage;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BrowseAuthorPage;
import se.nackademin.librarytest.pages.EditAuthorPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 *
 * @author jesper
 */
public class AuthorHelper {

    public static Author addNewRandomAuthor() {
        String user = "admin";
        String password = "1234567890";
        UserHelper.logInAsUser(user, password);

        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddAuthor();

        String firstName = UUID.randomUUID().toString();
        String lastName = UUID.randomUUID().toString();
        String country = UUID.randomUUID().toString();
        String biography = UUID.randomUUID().toString();

        AddAuthorPage addAuthorPage = page(AddAuthorPage.class);
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setCountry(country);
        author.setBiography(biography);
        addAuthorPage.setFirstNameField(firstName);
        addAuthorPage.setLastNameField(lastName);
        addAuthorPage.setCountryField(country);
        addAuthorPage.setBiographyField(biography);

        addAuthorPage.clickAddAuthorButton();
        return author;
    }

    public static Author searchAndFetchAuthor(String name) {
        page(MenuPage.class).navigateToBrowseAuthors();
        BrowseAuthorPage browseAuthorPage = page(BrowseAuthorPage.class);
        browseAuthorPage.setAuthorField(name);
        browseAuthorPage.clickSearchAuthorsButton();
        Table table = new Table($(".v-grid-tablewrapper"));
        sleep(3000);
        table.searchAndClick(name, 0);

        AuthorPage AuthorPage = page(AuthorPage.class);
        int spaceNr= AuthorPage.getName().indexOf(" ");
        Author author = new Author();
        author.setFirstName(AuthorPage.getName().substring(0, spaceNr));
        author.setLastName(AuthorPage.getName().substring(spaceNr+1));
        author.setCountry(AuthorPage.getCountry());
        author.setBiography(AuthorPage.getBiography());

        return author;
    }
    public static Author editAndReturnAuthor(String name) {
        page(MenuPage.class).navigateToBrowseAuthors();
        BrowseAuthorPage browseAuthorPage = page(BrowseAuthorPage.class);
        browseAuthorPage.setAuthorField(name);
        browseAuthorPage.clickSearchAuthorsButton();
        Table table = new Table($(".v-grid-tablewrapper"));
        sleep(3000);
        table.searchAndClick(name, 0);

        AuthorPage AuthorPage = page(AuthorPage.class);
        AuthorPage.clickEditAuthorButton();
        
        String firstName = UUID.randomUUID().toString();
        String lastName = UUID.randomUUID().toString();
        String country = UUID.randomUUID().toString();
        String biography = UUID.randomUUID().toString();

        EditAuthorPage editAuthorPage = page(EditAuthorPage.class);
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setCountry(country);
        author.setBiography(biography);
        editAuthorPage.setFirstNameField(firstName);
        editAuthorPage.setLastNameField(lastName);
        editAuthorPage.setCountryField(country);
        editAuthorPage.setBiographyField(biography);

        editAuthorPage.clickSaveAuthorButton();
        return author;
    } 
    public static void deleteAuthor(){
    AuthorPage AuthorPage = page(AuthorPage.class);
    AuthorPage.clickDeleteAuthorButton();
    AuthorPage.confirmByYesButton();
    }
            
}
