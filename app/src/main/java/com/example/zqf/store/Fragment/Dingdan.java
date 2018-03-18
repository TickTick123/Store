package com.example.zqf.store.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zqf.store.R;

/**
 * Created by admin on 2018/3/17.
 */

public class Dingdan extends Fragment {
    ListView listView;
    public Dingdan(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dingdan, container, false);
        listView=view.findViewById(R.id.listView2);

        return view;
    }
}
