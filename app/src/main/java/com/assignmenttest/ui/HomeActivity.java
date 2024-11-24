package com.assignmenttest.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.assignmenttest.MyApplication;
import com.assignmenttest.R;
import com.assignmenttest.databinding.HomeBinding;
import com.assignmenttest.models.User;
import com.assignmenttest.ui.fragments.Certificate;
import com.assignmenttest.ui.fragments.Chat;
import com.assignmenttest.ui.fragments.Profile;
import com.assignmenttest.ui.fragments.Score;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {
    HomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new Certificate());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.certificate:
                    replaceFragment(new Certificate());
                    break;
                case R.id.profile:
                    replaceFragment(new Profile());
                    break;
                case R.id.chat:
                    replaceFragment(new Chat());
                    break;
                case R.id.score:
                    replaceFragment(new Score());
                    break;
            }
            return true;
        });
        userListData();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void userListData() {
        MyApplication.usersList = new ArrayList<>();
        User user = new User("Alpesh Dave", "alpesh@gmail.com", "9824332998", 24, false);
        MyApplication.usersList.add(user);

        user = new User("Kalpesh Patel", "kalpeshpatel@gmail.com", "9824332998", 36, true);
        MyApplication.usersList.add(user);

        user = new User("Ramesh Joshi", "rameshjoshi@gmail.com", "9824332998", 42, true);
        MyApplication.usersList.add(user);

        user = new User("Rohit Sharma", "rohitsharma@gmail.com", "9824332998", 28, false);
        MyApplication.usersList.add(user);

        user = new User("Sachin Modi", "sachinmodi@gmail.com", "9824332998", 39, true);
        MyApplication.usersList.add(user);

        user = new User("Jigar Nayak", "jigarnayak@gmail.com", "9824332998", 30, false);
        MyApplication.usersList.add(user);

        user = new User("Mohit Verma", "mohitverma@gmail.com", "9824332998", 48, true);
        MyApplication.usersList.add(user);
        saveUser();

    }


    private void saveUser() {
        for (User newUser : MyApplication.usersList) {
            // User newUser = new User("Alpesh Dave", "alpesh@gmail.com", "9824332998");
            String userId = MyApplication.usersRef.push().getKey();
            MyApplication.usersRef.child(userId).setValue(newUser)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Data is successfully written
                            Log.e("saveUser..if..", task.toString());
                        } else {
                            Log.e("saveUser..else..", task.getException().toString());
                        }
                    });
        }
    }


}
