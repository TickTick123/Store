package com.example.zqf.store.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zqf.store.R;

/**
 * Created by admin on 2018/2/12.
 */

public class My extends Fragment {
    public My (){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }
}
