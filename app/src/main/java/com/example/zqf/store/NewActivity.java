package com.example.zqf.store;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Bean.New;
import com.example.zqf.store.Bean.Order;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class NewActivity extends AppCompatActivity {

    private New anew;
    ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        anew=(New)intent.getSerializableExtra("newobj");

        TextView tx22=findViewById(R.id.textView_all_title);
        tx22.setText(anew.getTitle());
        TextView tx27=findViewById(R.id.textView_all_content);
        tx27.setText(anew.getContent());
        TextView tx28=findViewById(R.id.textView68);
        tx28.setText(anew.getUpdatedAt());
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
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
