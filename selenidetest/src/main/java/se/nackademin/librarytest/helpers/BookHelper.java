/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import java.util.Random;
import java.util.UUID;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import se.nackademin.librarytest.model.Author;

import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.AddAuthorPage;
import se.nackademin.librarytest.pages.AddBookPage;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditBookPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 * @author testautomatisering
 */
public class BookHelper {

    public static Book addNewBook() {
        String user = "admin";
        String password = "1234567890";
        UserHelper.logInAsUser(user, password);

        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddBook();

        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String numberOfPages = "500";
        String isbn = "0-575-01587-X";
        String totalNumberOfCopiesAvailible = "55";
        String datePublished = "1999-10-15";

        AddBookPage addBookPage = page(AddBookPage.class);
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setNumberOfPages(numberOfPages);
        book.setIsbn(isbn);
        book.setTotalNumberOfCopiesAvailible(totalNumberOfCopiesAvailible);
        book.setDatePublished(datePublished);
        addBookPage.setTitleField(title);
        addBookPage.setDescriptionField(description);
        addBookPage.setNumberOfPagesField(numberOfPages);
        addBookPage.setIsbnField(isbn);
        addBookPage.setNumberInInventoryField(totalNumberOfCopiesAvailible);
        addBookPage.setDatePublishedField(datePublished);

        List list = new List($(".v-select-twincol-options"));
        int authorOption = 0;
        book.setAuthor(list.getOptionValue(authorOption));
        list.clickOption(authorOption);

        addBookPage.clickAddBookButton();
        return book;
    }

    public static Book searchAndFetchBook(String title) {
        page(MenuPage.class).navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(title);
        browseBooksPage.clickSearchBooksButton();
        Table table = new Table($(".v-grid-tablewrapper"));
        table.searchAndClick(title, 0);

        BookPage bookPage = page(BookPage.class);
        Book book = new Book();
        book.setTitle(bookPage.getTitle());
        book.setAuthor(bookPage.getAuthor());
        book.setDescription(bookPage.getDescription());
        book.setIsbn(bookPage.getIsbn());
        book.setDatePublished(bookPage.getPublishedDate());
        book.setCopiesAvailible(bookPage.getCopiesAvailible());
        book.setTotalNumberOfCopiesAvailible(bookPage.getTotalNumberOfCopiesAvailible());
        return book;
    }

    public static Book editAndReturnBook(String bookTitle) {
        page(MenuPage.class).navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(bookTitle);
        browseBooksPage.clickSearchBooksButton();
        Table table = new Table($(".v-grid-tablewrapper"));
        table.clickCell(0, 0);

        BookPage bookPage = page(BookPage.class);
        bookPage.navigateToEditBooks();

        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String numberOfPages = "400";
        String isbn = "9-575-55555-X";
        String numberInInventory = "65";
        String datePublished = "1985-10-15";

        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setNumberOfPages(numberOfPages);
        book.setIsbn(isbn);
        book.setTotalNumberOfCopiesAvailible(numberInInventory);

        book.setDatePublished(datePublished);
        List list = new List($(".v-select-twincol-selections"));
        int authorOption = 0;
        book.setAuthor(list.getOptionValue(authorOption));
        list.clickOption(authorOption);

        EditBookPage editBookPage = page(EditBookPage.class);
        editBookPage.setTitleField(title);
        editBookPage.setDescriptionField(description);
        editBookPage.setNumberOfPagesField(numberOfPages);
        editBookPage.setIsbnField(isbn);
        editBookPage.setNumberInInventoryField(numberInInventory);
        editBookPage.setDatePublishedField(datePublished);

        editBookPage.clickSaveBookButton();

        return book;
    }

    public static void deleteBook() {
        BookPage bookPage = page(BookPage.class);
        bookPage.deleteBookButton();
        bookPage.confirmByYesButton();
    }
//
//    public static Boolean confirmBookExist(String title) {
//        try {
//            page(MenuPage.class).navigateToBrowseBooks();
//            BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
//            browseBooksPage.setTitleField(title);
//            browseBooksPage.clickSearchBooksButton();
//            Table table = new Table($(".v-grid-tablewrapper"));
//            if (title.equals(table.getCellValue(0, 0)));
//            return true;
//        } catch (ElementNotVisibleException e) {
//            return false;
//        }
//
//    }
}
