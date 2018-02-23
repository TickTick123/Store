package com.example.zqf.store;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.zqf.store.Bean.User;
import com.example.zqf.store.Fragment.Home;
import com.example.zqf.store.Fragment.My;

import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity {

    Home homefragment;                 //首页fragment
    My myfragment;                     //我的fragment

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fm=getFragmentManager();
            FragmentTransaction transaction=fm.beginTransaction();      //fragment控制器

            switch (item.getItemId()) {
                case R.id.navigation_home:                  //首页导航页

                    if(homefragment==null) {
                        homefragment=new Home();
                    }
                    transaction.replace(R.id.content,homefragment);
                    transaction.commit();


                    return true;
                case R.id.navigation_discover:             //发现导航页
                    //mTextMessage.setText(R.string.title_discover);
                    return true;
                case R.id.navigation_order:                // 订单导航页
                    //mTextMessage.setText(R.string.title_order);
                    return true;
                case R.id.navigation_my:                    //我的导航页

                    BmobUser.logOut();   //清除缓存用户对象，既退出，

                    if(myfragment==null) {
                        myfragment=new My();
                    }
                    transaction.replace(R.id.content,myfragment);
                    transaction.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            User user = BmobUser.getCurrentUser(User.class);          //测试本地缓存，有效
            toast(user.getUsername());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setDefaultFragment();          //设置初始fragment
    }

    private void setDefaultFragment() {
        FragmentManager fm =getFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        homefragment=new Home();
        transaction.replace(R.id.content,homefragment);
        transaction.commit();
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    };

}
