package com.example.zqf.store.Activity_Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/3/23.
 */

public class ReuseActivity extends AppCompatActivity {
    List<Good> goodList=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse);
        listView=findViewById(R.id.reuse_list);

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
        }
        return false;
    }
}
