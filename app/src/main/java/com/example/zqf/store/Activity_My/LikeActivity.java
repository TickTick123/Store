package com.example.zqf.store.Activity_My;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zqf.store.Activity_Home.BrandActivity;
import com.example.zqf.store.Activity_Home.Brand_DetailActivity;
import com.example.zqf.store.Adapter.FinalGoodAdapter;
import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.OrderActivity;
import com.example.zqf.store.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class LikeActivity extends AppCompatActivity {
    ActionBar bar;
    List<Good> goodlist=new ArrayList<>();
    ListView listView;
    User user= BmobUser.getCurrentUser(User.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        listView=findViewById(R.id.LikeList);
        goodlist=user.getGoods();
        final FinalGoodAdapter adapter=new FinalGoodAdapter(LikeActivity.this,R.layout.item_finalgood,goodlist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(LikeActivity.this,Brand_DetailActivity.class);
                intent.putExtra("good",goodlist.get(i));
                intent.putExtra("state",1);
                startActivity(intent);
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

    protected void onDestroy(){
        super.onDestroy();
        user.setGoods(goodlist);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e!=null)
                    Toast.makeText(getApplicationContext(),"更新失败", Toast.LENGTH_LONG).show();
            }
        });
    }
}
