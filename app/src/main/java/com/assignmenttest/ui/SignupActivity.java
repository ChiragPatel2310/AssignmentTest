package com.assignmenttest.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.assignmenttest.R;
import com.assignmenttest.databinding.AuthSignupBinding;
import com.assignmenttest.utils.Constants;
import com.assignmenttest.utils.Util;

public class SignupActivity extends AppCompatActivity {

    AuthSignupBinding authSignupBinding;
    String phone_number = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phone_number = getIntent().getStringExtra(Constants.PHONE_NUMBER);
        init();
    }

    private void init() {
        authSignupBinding = DataBindingUtil.setContentView(SignupActivity.this, R.layout.auth_signup);
        if (!phone_number.isEmpty()) {
            authSignupBinding.etPhone.setText(phone_number);
        }

        authSignupBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authSignupBinding.etName.getText().toString().isEmpty()) {
                    Util.getInstance(SignupActivity.this).ShowToast(getResources().getString(R.string.error_name));
                } else if (authSignupBinding.etEmail.getText().toString().isEmpty() || isValidEmail(authSignupBinding.etEmail.getText().toString())) {
                    Util.getInstance(SignupActivity.this).ShowToast(getResources().getString(R.string.error_email));
                } else if (authSignupBinding.etPhone.getText().toString().isEmpty()) {
                    Util.getInstance(SignupActivity.this).ShowToast(getResources().getString(R.string.error_phone));
                } else {
                    Util.getInstance(SignupActivity.this).NavigationActivity(OtpActivity.class, Constants.PHONE_NUMBER, authSignupBinding.etPhone.getText().toString(), false);
                }
            }
        });
        authSignupBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        authSignupBinding.txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
