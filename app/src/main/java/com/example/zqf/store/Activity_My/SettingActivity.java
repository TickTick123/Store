package com.example.zqf.store.Activity_My;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    EditText ed1,ed2,ed3;
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

                user.setMobilePhoneNumber(ed3.getText().toString());
                user.setNicName(ed1.getText().toString());
                user.setSex(ed2.getText().toString());

                user.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"更新失败！", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        bu14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bu15.setVisibility(View.INVISIBLE);
//        bu15.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
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
