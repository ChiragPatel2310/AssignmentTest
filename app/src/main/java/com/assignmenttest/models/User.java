package com.assignmenttest.models;

public class User {
    //name (String),age (Integer), premium (Boolean) .
    public String name;
    public String email;
    public String phone_number;
    public int age = 0;
    public boolean premium = false;

    public User() {
    }

    public User(String name, String email, String phone_number, int age, boolean premium) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.age = age;
        this.premium = premium;

    }
}
