package com.example.zqf.store.Activity_My;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zqf.store.AboutActivity;
import com.example.zqf.store.Activity_Home.Brand_FormActivity;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.LoginActivity;
import com.example.zqf.store.MainActivity;
import com.example.zqf.store.R;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class SettingActivity extends AppCompatActivity {
    User user;
    EditText ed1,ed2,ed3,ed4,ed5;
    ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        user = BmobUser.getCurrentUser(User.class);
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        ed3=findViewById(R.id.editText3);
        ed1.setText(user.getnicName());
        ed2.setText(user.getSex());
        ed3.setText(user.getMobilePhoneNumber());

        Button bu12=findViewById(R.id.button12);
        Button bu13=findViewById(R.id.button13);
        Button bu14=findViewById(R.id.button14);
        Button bu15=findViewById(R.id.button15);

        bu12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File F=new File("/data/user/0/com.example.zqf.store/cache/bmob/head.jpg");
                if(F.exists()) {
                    F.delete();
                }
                BmobUser.logOut();
                Intent mainIntent=new Intent(SettingActivity.this,LoginActivity.class);
                startActivity(mainIntent);
                MainActivity.mActivity.finish();                    //退出任意activity
                finish();
            }
        });

        bu13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User newUser = new User();
                if(ed3.getText().toString().equals(user.getMobilePhoneNumber())){
                    newUser.setNicName(ed1.getText().toString());
                    newUser.setSex(ed2.getText().toString());
                }else{
                    newUser.setNicName(ed1.getText().toString());
                    newUser.setSex(ed2.getText().toString());
                    newUser.setMobilePhoneNumber(ed3.getText().toString());
                }
                newUser.update(user.getObjectId(),new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            //toast("更新用户信息成功");
                            finish();
                        }else{
                            toast("更新用户信息失败:" + e.getMessage());
                        }
                    }
                });

            }
        });

        bu14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        bu15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dialog));
                ed4=layout.findViewById(R.id.editText4);
                ed5=layout.findViewById(R.id.editText5);
                new AlertDialog.Builder(SettingActivity.this).setTitle("修改密码").setView(layout)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                BmobUser.updateCurrentUserPassword(ed4.getText().toString(),ed5.getText().toString(), new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if(e==null){
                                            toast("密码修改成功，可以用新密码进行登录啦");
                                        }else{
                                            toast("失败:" + e.getMessage());
                                        }
                                    }
                                });
                                
                            }
                        })
                        .setNegativeButton("取消", null).show();
            }
        });

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

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }
}
