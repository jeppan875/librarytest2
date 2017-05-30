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
public class Author {
 
    private String firstName;
    private String bio;
    private String lastName;
    private Integer id;
    private String country;

    public Author(){}
    public Author(Integer id, String country, String firstName, String lastName){
    this.id= id;
    this.country= country;
    this.firstName= firstName;
    this.lastName= lastName;
    }
    
    /**
     * @return the name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param name the name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }


}
