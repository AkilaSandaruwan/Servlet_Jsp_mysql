package com.akila.epic.model;

import java.util.HashMap;

public class Employee {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
    private String password;
    private String confirmPassword;
    private HashMap<String, String> errors=new HashMap<String, String>();;

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(String errorName,String err) {
        this.errors.put(errorName,err) ;
    }



    public Employee() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
