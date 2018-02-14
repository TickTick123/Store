package com.example.zqf.store;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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

        findViewById(R.id.button_login).setOnClickListener(this);       //设置监听器
        findViewById(R.id.textView_forget).setOnClickListener(this);       //设置监听器
        findViewById(R.id.textView_reg).setOnClickListener(this);       //设置监听器
        findViewById(R.id.textView_phonereg).setOnClickListener(this);       //设置监听器
    }

    @Override
    public void onClick(View view){
        if(view.getId()==R.id.button_login){            //登录按钮
            Intent mainIntent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finish();
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
}
