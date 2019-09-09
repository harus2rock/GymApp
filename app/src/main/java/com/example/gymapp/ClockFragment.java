package com.example.gymapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

class ClockFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup layout_clock,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flagment_clock,
                layout_clock, false);
    }
}
