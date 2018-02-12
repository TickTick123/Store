package com.example.zqf.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cn.bmob.v3.Bmob;

public class StartpageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        Bmob.initialize(this, "14ad818ac5ca96d7f6166b841450afee");      //云数据库连接

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {                     //Intent跳转
                Intent mainIntent=new Intent(StartpageActivity.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },3000);

    }
}
