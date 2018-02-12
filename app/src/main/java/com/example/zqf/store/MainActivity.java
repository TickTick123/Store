package com.example.zqf.store;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.zqf.store.Fragment.Home;
import com.example.zqf.store.Fragment.My;

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

}
