package com.assignmenttest.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.assignmenttest.databinding.MenuFragmentChatBinding;

public class Chat extends Fragment {
    MenuFragmentChatBinding fragmentChatBinding;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentChatBinding = MenuFragmentChatBinding.inflate(inflater, container, false);
        return fragmentChatBinding.getRoot();
    }
}