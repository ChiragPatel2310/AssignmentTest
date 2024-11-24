package com.assignmenttest.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.assignmenttest.R;
import com.assignmenttest.databinding.QuestionBinding;
import com.assignmenttest.utils.Util;


public class QuestionActivity extends AppCompatActivity {
    QuestionBinding questionBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        questionBinding = DataBindingUtil.setContentView(QuestionActivity.this, R.layout.question);

        questionBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionBinding.etAbout.getText().toString().isEmpty()) {
                    Util.getInstance(QuestionActivity.this).ShowToast(getResources().getString(R.string.error_about));
                } else {
                    finish();
                }
            }
        });
        questionBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        MyCountDown();
    }

    private void MyCountDown() {
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                questionBinding.txtTimer.setText("Seconds Remaining: " + millisUntilFinished / 1000);
                questionBinding.txtTimer.setVisibility(View.VISIBLE);
            }

            public void onFinish() {
                questionBinding.txtTimer.setVisibility(View.GONE);
                questionBinding.btnSubmit.setVisibility(View.GONE);
                questionBinding.etAbout.setEnabled(false);
            }
        }.start();
    }
}
