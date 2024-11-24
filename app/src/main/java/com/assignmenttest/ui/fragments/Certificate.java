package com.assignmenttest.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.assignmenttest.databinding.MenuFragmentCertificateBinding;
import com.assignmenttest.ui.QuestionActivity;
import com.assignmenttest.utils.Util;

public class Certificate extends Fragment {
    MenuFragmentCertificateBinding menuFragmentCertificateBinding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        menuFragmentCertificateBinding = MenuFragmentCertificateBinding.inflate(inflater, container, false);
        init();
        return menuFragmentCertificateBinding.getRoot();
    }

    private void init() {
        menuFragmentCertificateBinding.btnWinCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.getInstance(getActivity()).NavigationActivity(QuestionActivity.class, "", "", false);
            }
        });
    }
}