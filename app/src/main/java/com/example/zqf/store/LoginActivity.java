package com.example.zqf.store;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private CheckBox ch_remember;
    private TextView te_forget;
    private TextView te_register;
    private TextView te_phone_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_name =(EditText) findViewById(R.id.editText_name);             //初始化
        et_password=(EditText)findViewById(R.id.editText_password);

        findViewById(R.id.button_login).setOnClickListener(this);       //设置监听器
        findViewById(R.id.textView_forget).setOnClickListener(this);       //设置监听器
        findViewById(R.id.textView_reg).setOnClickListener(this);       //设置监听器
        findViewById(R.id.textView_phonereg).setOnClickListener(this);       //设置监听器

    }

    @Override
    public void onClick(View view){
        if(view.getId()==R.id.button_login){            //登录按钮

            User bu = new User();                   //  bmob登录
            bu.loginByAccount(et_name.getText().toString(),et_password.getText().toString(),   //用户名登录
                    new LogInListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if(user!=null){
                        Intent mainIntent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                    if(e.getErrorCode()==9018)
                        toast("用户名和密码不能为空");
                    if(e.getErrorCode()==101)
                        toast("用户名获密码不正确");
                }
            });

        }

        if(view.getId()==R.id.textView_forget){         //忘记密码按钮
            Intent mainIntent=new Intent(LoginActivity.this,ForgetPasswordActivity.class);
            startActivity(mainIntent);
        }
        if(view.getId()==R.id.textView_reg){             //一键登录按钮
            Intent mainIntent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(mainIntent);
        }
        if(view.getId()==R.id.textView_phonereg){        //手机号登录按钮
            Intent mainIntent=new Intent(LoginActivity.this,PhoneRegisterActivity.class);
            startActivity(mainIntent);
        }
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    };
}
