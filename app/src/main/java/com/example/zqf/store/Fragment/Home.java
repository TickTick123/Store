package com.example.zqf.store.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zqf.store.Activity_Home.MarketActivity;
import com.example.zqf.store.Activity_My.SettingActivity;
import com.example.zqf.store.Adapter.HomePageAdapter;
import com.example.zqf.store.R;

import java.util.ArrayList;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by admin on 2018/2/12.
 */

public class Home extends Fragment {
    Button but1,but2,but3,but4,but5,but6,but7;
    HomePageAdapter mAdapter;
    ArrayList<View> aList;
    ViewPager vpager_one;
    Handler adHandler;


    public Home (){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        vpager_one = view.findViewById(R.id.mPager);
        adHandler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                vpager_one.setCurrentItem(msg.arg1);
            }
        };


        aList = new ArrayList<View>();
        aList.add(inflater.inflate(R.layout.view_one,null,false));
        aList.add(inflater.inflate(R.layout.view_two,null,false));
        aList.add(inflater.inflate(R.layout.view_three,null,false));
        mAdapter = new HomePageAdapter(aList);
        vpager_one.setAdapter(mAdapter);
        vpager_one.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        but1=view.findViewById(R.id.button);
        but2=view.findViewById(R.id.button2);
        but3=view.findViewById(R.id.button3);
        but4=view.findViewById(R.id.button4);
        but5=view.findViewById(R.id.button5);
        but6=view.findViewById(R.id.button6);
        but7=view.findViewById(R.id.button7);
        but1.setOnClickListener(new View.OnClickListener() {            //零食
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MarketActivity.class);
                startActivity(intent);
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}
