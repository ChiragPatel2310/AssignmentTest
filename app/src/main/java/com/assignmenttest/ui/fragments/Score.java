package com.assignmenttest.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.assignmenttest.databinding.MenuFragmentScoreBinding;

public class Score extends Fragment {
    MenuFragmentScoreBinding fragmentScoreBinding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentScoreBinding = MenuFragmentScoreBinding.inflate(inflater, container, false);
        return fragmentScoreBinding.getRoot();
    }
}