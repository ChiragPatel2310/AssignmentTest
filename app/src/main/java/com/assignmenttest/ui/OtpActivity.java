package com.assignmenttest.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.assignmenttest.MyApplication;
import com.assignmenttest.R;
import com.assignmenttest.databinding.AuthOtpBinding;
import com.assignmenttest.utils.Constants;
import com.assignmenttest.utils.SharedPrefUtils;
import com.assignmenttest.utils.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
    String verificationId = "";
    AuthOtpBinding authOtpBinding;
    String PhoneNumber = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PhoneNumber = getIntent().getStringExtra(Constants.PHONE_NUMBER);
        init();
        editTextInput();
    }

    private void init() {
        authOtpBinding = DataBindingUtil.setContentView(OtpActivity.this, R.layout.auth_otp);

        authOtpBinding.txtMobile.setText(Constants.COUNTRY_CODE + PhoneNumber);

        authOtpBinding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp1 = authOtpBinding.etOtp1.getText().toString();
                String otp2 = authOtpBinding.etOtp2.getText().toString();
                String otp3 = authOtpBinding.etOtp3.getText().toString();
                String otp4 = authOtpBinding.etOtp4.getText().toString();
                String otp5 = authOtpBinding.etOtp5.getText().toString();
                String otp6 = authOtpBinding.etOtp6.getText().toString();
                if (otp1.isEmpty() || otp2.isEmpty() || otp3.isEmpty() || otp4.isEmpty() || otp5.isEmpty() || otp6.isEmpty()) {
                    Util.getInstance(OtpActivity.this).ShowToast(getResources().getString(R.string.error_otp));
                } else {
                    SharedPrefUtils.saveData(OtpActivity.this, Constants.IS_LOGIN, true);
                    Util.getInstance(OtpActivity.this).ClearAllPreviousActivity(HomeActivity.class);
                }
            }
        });
        authOtpBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        authOtpBinding.txtResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();
            }
        });
        sendVerificationCode();
    }

    private void editTextInput() {

        authOtpBinding.etOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                authOtpBinding.etOtp2.requestFocus();
            }
        });
        authOtpBinding.etOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                authOtpBinding.etOtp3.requestFocus();
            }
        });
        authOtpBinding.etOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                authOtpBinding.etOtp4.requestFocus();
            }
        });
        authOtpBinding.etOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                authOtpBinding.etOtp5.requestFocus();
            }
        });
        authOtpBinding.etOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                authOtpBinding.etOtp6.requestFocus();
            }
        });
        authOtpBinding.etOtp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private void sendVerificationCode() {
        // this method is used for getting OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(MyApplication.mAuth)
                        .setPhoneNumber(Constants.COUNTRY_CODE + PhoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallBack)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
//            Log.e("onCodeSent...", verificationId+"...."+forceResendingToken);
//            Util.getInstance(OtpActivity.this).ShowToast("onCodeSent..."+verificationId);
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            Util.getInstance(OtpActivity.this).ShowToast("onVerificationCompleted..."+code);
            Log.e("onVerificationCompleted...", code);
            if (code != null) {
//                edtOTP.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.e("onVerificationFailed...", e.getMessage());
            Util.getInstance(OtpActivity.this).ShowToast("onVerificationFailed..."+e.getMessage());
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
        MyCountDown();
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        MyApplication.mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Util.getInstance(OtpActivity.this).ShowToast("signInWithCredential...if..."+task.getResult().toString());
                            Log.e("signInWithCredential...if..", task.getResult().toString());
                        } else {
                            Log.e("signInWithCredential...else..", task.getException().getMessage());
//                            Util.getInstance(OtpActivity.this).ShowToast("isSuccessful..."+task.getResult().toString());
                            Util.getInstance(OtpActivity.this).ShowToast(task.getException().getMessage());
                        }
                    }
                });
    }

    private void MyCountDown() {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                authOtpBinding.txtResendOtp.setText("seconds remaining: " + millisUntilFinished / 1000);
                isVisible(false);
            }

            public void onFinish() {
                authOtpBinding.txtResendOtp.setText(getResources().getString(R.string.resend_otp));
                isVisible(true);
            }
        }.start();
    }

    private void isVisible(boolean isVisible) {
        authOtpBinding.txtResendOtp.setClickable(isVisible);
        authOtpBinding.txtResendOtp.setEnabled(isVisible);
    }
}
