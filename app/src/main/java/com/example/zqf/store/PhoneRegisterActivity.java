package com.example.zqf.store;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class PhoneRegisterActivity extends AppCompatActivity {


    private EditText phone;
    private EditText password,code;
    private Button codeRutton,regButton;
    private List<String> ad=new ArrayList<>();
    private BmobFile bmobfile =new BmobFile("head.jpg","","http://bmob-cdn-17080.b0.upaiyun.com/2018/03/06/3d413e4bbfd848aeb536e40839106872.jpg");
    List<Good> goods=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        phone =(EditText) findViewById(R.id.editText_phonereg_number);             //初始化
        code=(EditText)findViewById(R.id.editText_phonereg_code);
        codeRutton=(Button)findViewById(R.id.button_phonereg_code);
        password=(EditText)findViewById(R.id.editText_phonereg_password);
        regButton=(Button)findViewById(R.id.button_phonereg_reg);

        codeRutton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){                //发送验证码,成功
                toast("已发送验证码");
                BmobSMS.requestSMSCode(phone.getText().toString(), "store",new QueryListener<Integer>() {

                    @Override
                    public void done(Integer smsId,BmobException ex) {
                        if(ex==null){//验证码发送成功

                            Log.i("smile", "短信id："+smsId);//用于查询本次短信发送详情
                        }else
                            toast(ex.getMessage());
                    }
                });

            }
        });

        regButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){                        //注册按钮相应

                User user = new User();
                user.setMobilePhoneNumber(phone.getText().toString());//设置手机号码（必填）

                user.setPassword(password.getText().toString());                  //设置用户密码
                user.setNicName("请输入昵称");
                user.setSex("待定。。。");
                user.setPicUser(bmobfile);
                user.setGoods(goods);
                user.setAddress(ad);
                if(password.getText().toString().equals(""))
                    toast("密码不能为空");
                else{
                    user.signOrLogin(code.getText().toString(), new SaveListener<User>() {

                        @Override
                        public void done(User user,BmobException e) {
                            if(e==null){
                                toast("注册成功");
                                finish();
                            }else{
                                toast("失败:" + e.getMessage());
                            }

                        }

                    });
                }

            }
        });



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.empty, menu);                           //添加菜单项
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }
}