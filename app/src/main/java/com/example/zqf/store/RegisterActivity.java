package com.example.zqf.store;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Bean.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button regButton;
    private BmobFile bmobfile =new BmobFile("head.jpg","","http://bmob-cdn-17080.b0.upaiyun.com/2018/03/06/3d413e4bbfd848aeb536e40839106872.jpg");
    private List<String> ad=new ArrayList<>();
    ActionBar bar;
    List<Good> goods=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        name =(EditText) findViewById(R.id.editText_register_name);             //初始化
        password=(EditText)findViewById(R.id.editText_register_password);
        regButton=(Button)findViewById(R.id.button_register_Reg);


        regButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){                        //注册按钮相应
                User bu = new User();                   //  bmob注册
                bu.setUsername(name.getText().toString());
                bu.setPassword(password.getText().toString());
                bu.setPicUser(bmobfile);
                bu.setNicName("请输入昵称");
                bu.setSex("待定。。。");
                bu.setAddress(ad);
                bu.setGoods(goods);
                bu.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User s, BmobException e) {
                        if(e==null){
                            toast("注册成功");
                            finish();
                        }
                        if(e.getErrorCode()==202)
                            toast("该用户名已被占用，请输入一个新的用户名");       //异常处理1
                        if(e.getErrorCode()==304)
                            toast("用户名和密码不能为空");                    //异常处理2
                    }
                });
            }
        });


    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.empty,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
