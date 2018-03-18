package com.example.zqf.store;


import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.zqf.store.Bean.User;
import com.example.zqf.store.Fragment.Dingdan;
import com.example.zqf.store.Fragment.Home;
import com.example.zqf.store.Fragment.My;

import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity {
    User user = BmobUser.getCurrentUser(User.class);
    public static MainActivity mActivity;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:                  //首页导航页
                    replaceFragment(new Home());
                    return true;
                case R.id.navigation_discover:             //发现导航页
                    //mTextMessage.setText(R.string.title_discover);
                    return true;
                case R.id.navigation_order:                // 订单导航页
                    replaceFragment(new Dingdan());
                    return true;
                case R.id.navigation_my:                    //我的导航页
                    replaceFragment(new My());
                    //BmobUser.logOut();   //清除缓存用户对象，既退出，
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_main);
            toast(user.getUsername());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setDefaultFragment();          //设置初始fragment
    }

    private void setDefaultFragment() {
        replaceFragment(new Home());
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }

    private void replaceFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.replace(R.id.content,fragment);
        transaction.commit();
    }

}
