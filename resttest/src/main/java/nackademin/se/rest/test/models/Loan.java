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
public class Loan {
    
     private Integer id;
     private Object book;
     private String dateBorrowed;
     private String dateDue;
     private Object user;

    /**
     * @return the book
     */
    public Object getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Object book) {
        this.book = book;
    }

    /**
     * @return the dateBorrowed
     */
    public String getDateBorrowed() {
        return dateBorrowed;
    }

    /**
     * @param dateBorrowed the dateBorrowed to set
     */
    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    /**
     * @return the dateDue
     */
    public String getDateDue() {
        return dateDue;
    }

    /**
     * @param dateDue the dateDue to set
     */
    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    /**
     * @return the user
     */
    public Object getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Object user) {
        this.user = user;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
  
}
