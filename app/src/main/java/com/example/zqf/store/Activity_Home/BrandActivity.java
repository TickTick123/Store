package com.example.zqf.store.Activity_Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zqf.store.Adapter.FinalGoodAdapter;
import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by admin on 2018/3/23.
 */

public class BrandActivity extends AppCompatActivity {
    List<Good> goodList=new ArrayList<>();
    ListView listView;
    FinalGoodAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse);
        listView=findViewById(R.id.reuse_list);
        BmobQuery<Good> query = new BmobQuery<Good>();              //按条件查找右侧list    (1)
        query.addWhereEqualTo("type","潮牌");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goodList=object;
                    adapter=new FinalGoodAdapter(BrandActivity.this,R.layout.item_finalgood,goodList);
                    listView.setAdapter(adapter);
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_reuse,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.reuse_my:
                Intent intent=new Intent(BrandActivity.this, Brand_FormActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }

    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
