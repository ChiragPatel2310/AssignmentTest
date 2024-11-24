package com.assignmenttest.ui;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.assignmenttest.R;
import com.assignmenttest.utils.Constants;
import com.assignmenttest.utils.SharedPrefUtils;
import com.assignmenttest.utils.Util;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefUtils.getBooleanData(SplashActivity.this, Constants.IS_LOGIN)) {
                    Util.getInstance(SplashActivity.this).NavigationActivity(HomeActivity.class, "", "", true);
                } else {
                    Util.getInstance(SplashActivity.this).NavigationActivity(LoginActivity.class, "", "", true);
                }
            }
        }, 3000);
    }
}
