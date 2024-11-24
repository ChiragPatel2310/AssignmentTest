package com.assignmenttest;

import android.app.Application;

import com.assignmenttest.models.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyApplication extends Application {
    public static FirebaseAuth mAuth;
    public static ArrayList<User> usersList = new ArrayList<>();
    public static DatabaseReference usersRef;
    public static FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
    }
}