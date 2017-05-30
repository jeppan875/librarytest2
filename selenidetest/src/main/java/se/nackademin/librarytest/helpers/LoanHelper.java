/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 * @author jesper
 */
public class LoanHelper {
    public static void borrowBook(String book) {
        page(MenuPage.class).navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(book);
        browseBooksPage.clickSearchBooksButton();
        Table table = new Table($(".v-grid-tablewrapper"));
        table.clickCell(0, 0);

        BookPage bookPage = page(BookPage.class);
        bookPage.borrowBookButton();
        bookPage.confirmByYesButton();
            
    }

    public static void returnBook(String book) {

        page(MenuPage.class).navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(book);
        browseBooksPage.clickSearchBooksButton();
        Table table = new Table($(".v-grid-tablewrapper"));
        table.clickCell(0, 0);

        BookPage bookPage = page(BookPage.class);
        bookPage.returnBookButton();
        bookPage.confirmByYesButton();
    }
    public static String copiesAvailible() {
//        page(MenuPage.class).navigateToBrowseBooks();
//        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
//        browseBooksPage.setTitleField(book);
//        browseBooksPage.clickSearchBooksButton();
//        Table table = new Table($(".v-grid-tablewrapper"));
//        table.clickCell(0, 0);

        BookPage bookPage = page(BookPage.class);
        return bookPage.getCopiesAvailible();

    }    
}
