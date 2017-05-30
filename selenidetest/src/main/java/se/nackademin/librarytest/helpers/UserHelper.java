/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import java.util.UUID;
import se.nackademin.librarytest.model.User;

import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.EditMyProfilePage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.MyProfilePage;
import se.nackademin.librarytest.pages.SignInPage;

/**
 * @author testautomatisering
 */
public class UserHelper {
    public static User createNewRandomUser() {
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddUser();
        
        
        String displayName=UUID.randomUUID().toString();
        String password=UUID.randomUUID().toString();
        String firstName=UUID.randomUUID().toString();
        String lastName=UUID.randomUUID().toString();
        String phone=UUID.randomUUID().toString();
        String email=UUID.randomUUID().toString(); 
        
        User newUser = new User();
        newUser.setDisplayName(displayName);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPhone(phone);
        newUser.setEmail(email);
        
        AddUserPage addUserPage = page(AddUserPage.class);
        addUserPage.setUsername(displayName);
        addUserPage.setPassword(password);
        addUserPage.setFirstName(firstName);
        addUserPage.setLastName(lastName);
        addUserPage.setPhone(phone);
        addUserPage.setEmail(email);
        
        addUserPage.clickAddUserButton();
        
        return newUser;
    }
    public static User createNewSimpleRandomUser() {
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddUser();
              
        String displayName=UUID.randomUUID().toString();
        String password=UUID.randomUUID().toString();
       
        User newUser = new User();
        newUser.setDisplayName(displayName);
        newUser.setPassword(password);
       
        AddUserPage addUserPage = page(AddUserPage.class);
        addUserPage.setUsername(displayName);
        addUserPage.setPassword(password);
        
        addUserPage.clickAddUserButton();
        
        return newUser;
    }    
    public static void logInAsUser(String username, String password) {
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToSignIn();
        SignInPage signInPage = page(SignInPage.class);
        signInPage.setUsername(username);
        signInPage.setPassword(password);
        signInPage.clickLogIn();

    }
    public static User fetchUser(){
         page(MenuPage.class).navigateToMyProfile();
        MyProfilePage myProfilePage  = page(MyProfilePage.class);
        
        User user = new User();
        user.setDisplayName(myProfilePage.getUserName());
        user.setFirstName(myProfilePage.getFirstName());
        user.setLastName(myProfilePage.getLastName());
        user.setPhone(myProfilePage.getPhone());
        user.setEmail(myProfilePage.getEmail());
        
        return user;
        }    
    public static User updateUser(){
        page(MenuPage.class).navigateToMyProfile();
        MyProfilePage myProfilePage  = page(MyProfilePage.class);
        myProfilePage.navigateToEditUser();

        String displayName=UUID.randomUUID().toString();
        String password=UUID.randomUUID().toString();
        String firstName=UUID.randomUUID().toString();
        String lastName=UUID.randomUUID().toString();
        String phone=UUID.randomUUID().toString();
        String email=UUID.randomUUID().toString(); 
        
        User updatedUser = new User();
        updatedUser.setDisplayName(displayName);
        updatedUser.setPassword(password);
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);
        updatedUser.setPhone(phone);
        updatedUser.setEmail(email);
        
        EditMyProfilePage editMyProfilePage = page(EditMyProfilePage.class);
        editMyProfilePage.setUsername(displayName);
        editMyProfilePage.setPassword(password);
        editMyProfilePage.setFirstName(firstName);
        editMyProfilePage.setLastName(lastName);
        editMyProfilePage.setPhone(phone);
        editMyProfilePage.setEmail(email);
        editMyProfilePage.saveUserButton();
        
        page(MenuPage.class).navigateToMyProfile();
        return updatedUser;
        }
    public static String booksBorrowed(){
        page(MenuPage.class).navigateToMyProfile();
        Table table = new Table($(".v-grid-tablewrapper"));
        return table.getCellValue(0, 0);
    }
}
