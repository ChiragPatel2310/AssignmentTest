package com.assignmenttest.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.assignmenttest.R;
import com.assignmenttest.databinding.AuthLoginBinding;
import com.assignmenttest.utils.Constants;
import com.assignmenttest.utils.Util;

public class LoginActivity extends AppCompatActivity {
    private String verificationId;
    AuthLoginBinding authLoginBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        authLoginBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.auth_login);


        authLoginBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authLoginBinding.etPhone.getText().toString().isEmpty() ||
                        authLoginBinding.etPhone.getText().toString().length() < 10) {
                    Util.getInstance(LoginActivity.this).ShowToast(getResources().getString(R.string.error_phone));
                } else {
                    Util.getInstance(LoginActivity.this).NavigationActivity(OtpActivity.class, Constants.PHONE_NUMBER, authLoginBinding.etPhone.getText().toString(), false);
                }
            }
        });

        authLoginBinding.txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.getInstance(LoginActivity.this).NavigationActivity(SignupActivity.class, Constants.PHONE_NUMBER, authLoginBinding.etPhone.getText().toString(), false);

            }
        });
    }


}
