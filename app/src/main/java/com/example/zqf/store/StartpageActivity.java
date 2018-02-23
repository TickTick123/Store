package com.example.zqf.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.zqf.store.Bean.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class StartpageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        Bmob.initialize(this, "14ad818ac5ca96d7f6166b841450afee");      //云数据库连接


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {                     //Intent跳转
                Intent mainIntent=new Intent(StartpageActivity.this,LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },3000);

    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    };  //Toast便捷使用方法

}
