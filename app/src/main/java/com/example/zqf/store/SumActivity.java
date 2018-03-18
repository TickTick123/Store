package com.example.zqf.store;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zqf.store.Bean.User;

import cn.bmob.v3.BmobUser;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.LTGRAY;
import static android.graphics.Color.WHITE;

public class SumActivity extends AppCompatActivity {
    TextView tx6,tx8,tx10,tx12;
    Button but16;
    ListView listView;
    User user= BmobUser.getCurrentUser(User.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
        tx6=findViewById(R.id.textView6);
        tx6.setBackgroundColor(LTGRAY);
        tx6.setTextColor(WHITE);
        tx8=findViewById(R.id.textView8);
        tx10=findViewById(R.id.textView10);
        tx12=findViewById(R.id.textView12);
        tx10.setText(user.getnicName());
        tx12.setText(user.getMobilePhoneNumber());
        tx8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tx10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tx12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        listView=findViewById(R.id.listView);

        but16=findViewById(R.id.button16);
        but16.setBackgroundColor(GREEN);
        but16.setTextColor(WHITE);
        but16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
