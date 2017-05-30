/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nackademin.se.rest.test.models;

/**
 *
 * @author jesper
 */
public class Container {


    private Book book;
    private Author author;
    private User user;
    private Loan loan;
    
    public Container(Book book){
    this.book= book;
    }
    public Container(Author author){
    this.author= author;
    }
    public Container(User user){
    this.user= user;
    }
    public Container(Loan loan){
    this.loan= loan;
    }     
    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }
    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }    

    /**
     * @return the users
     */
    public User getUser() {
        return user;
    }

    /**
     * @param users the users to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the loan
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * @param loan the loan to set
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
