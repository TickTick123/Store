package com.example.zqf.store.Activity_Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zqf.store.Adapter.FinalGoodAdapter;
import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.OrderActivity;
import com.example.zqf.store.R;
import com.example.zqf.store.SumActivity;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by admin on 2018/3/23.
 */

public class ReuseActivity extends AppCompatActivity {
    List<Good> goodList=new ArrayList<>();
    ListView listView;
    FinalGoodAdapter adapter;
    ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        listView=findViewById(R.id.reuse_list);
        BmobQuery<Good> query = new BmobQuery<Good>();              //按条件查找右侧list    (1)
        query.addWhereEqualTo("type","二手");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goodList=object;
                    adapter=new FinalGoodAdapter(ReuseActivity.this,R.layout.item_finalgood,goodList);
                    listView.setAdapter(adapter);
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ReuseActivity.this,Reuse_DetailActivity.class);
                intent.putExtra("good",goodList.get(i));
                intent.putExtra("state",0);
                startActivity(intent);
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
                Intent intent=new Intent(ReuseActivity.this, Reuse_FormActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                this.finish();
                return false;
        }
        return false;
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }



}
