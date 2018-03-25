package com.example.zqf.store;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.zqf.store.Activity_Home.MarketActivity;
import com.example.zqf.store.Bean.New;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.Fragment.Dingdan;
import com.example.zqf.store.Fragment.Home;
import com.example.zqf.store.Fragment.My;
import com.example.zqf.store.Fragment.NewFragment;

import java.lang.reflect.Field;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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
                    replaceFragment(new NewFragment());
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
        disableShiftMode(navigation);               //关闭导航动画
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setDefaultFragment();          //设置初始fragment

//        New p2 = new New();
//        p2.setTitle("lucky");
//        p2.setContent("北京海淀");
//        p2.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId,BmobException e) {
//                if(e==null){
//                    toast("添加数据成功，返回objectId为："+objectId);
//                }else{
//                    toast("创建数据失败：" + e.getMessage());
//                }
//            }
//        });

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

    public static void disableShiftMode(BottomNavigationView view) {        //去除特效
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

}
