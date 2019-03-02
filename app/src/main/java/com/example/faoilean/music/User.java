package com.example.faoilean.music;

/**
 * Created by Faoilean on 21/11/2017.
 */

/*
Music app
Student: Faoilean Fortunefay
Student Number: C15380201
Mobile Software Development*/

public class User {


    private int user_id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    //default constructor
    public User(){

    }

    public User(String userName,String passWord,String fName, String lName){
        this.username = userName;
        this.firstName = fName;
        this.lastName = lName;
        this.password = passWord;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
