package com.assignmenttest.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignmenttest.BR;
import com.assignmenttest.MyApplication;
import com.assignmenttest.adapters.UserListAdapter;
import com.assignmenttest.databinding.MenuFragmentProfileBinding;
import com.assignmenttest.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends Fragment {
    MenuFragmentProfileBinding fragmentProfileBinding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentProfileBinding = MenuFragmentProfileBinding.inflate(inflater, container, false);

        return fragmentProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUserData();
        init();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        fragmentProfileBinding.rvUserList.setLayoutManager(layoutManager);
        UserListAdapter userListAdapter = new UserListAdapter(MyApplication.usersList, getActivity());
        fragmentProfileBinding.setVariable(BR.userListAdapter, userListAdapter);
        fragmentProfileBinding.setUserListAdapter(userListAdapter);
    }

    private void getUserData() {
        // Read all user data
        MyApplication.usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MyApplication.usersList = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user != null) {
                        String name = user.name;
                        String email = user.email;
                        String phone_number = user.phone_number;
                        String age = String.valueOf(user.age);
                        String premium = String.valueOf(user.premium);
                        Log.d("TAG", "name: " + name + ", email: " + email + ", phone_number: " + phone_number + ", age: " + age + ", premium: " + premium);
                        MyApplication.usersList.add(user);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TAG", "Error reading user data");
            }
        });
    }
}