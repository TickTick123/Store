package com.example.zqf.store;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zqf.store.Bean.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        User bu = new User();                   //  bmob注册
        bu.setUsername("sendiss");
        bu.setPassword("1");
        //bu.setEmail("sendi@163.com");
        bu.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if(e==null){
                    toast("注册成功:" +s.toString());
                }else{
                    toast("注册失败:" +e.toString());
                }
            }
        });

    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    };  //Toast便捷使用方法
}
