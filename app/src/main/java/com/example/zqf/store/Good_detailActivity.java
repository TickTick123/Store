package com.example.zqf.store;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;

public class Good_detailActivity extends AppCompatActivity {
    TextView textView,textView2;
    ImageView imageView;
    Good good;
    String path;
    ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        textView=findViewById(R.id.textView72);
        textView2=findViewById(R.id.textView73);
        imageView=findViewById(R.id.good_big);
        Intent intent=getIntent();
        good=(Good) intent.getSerializableExtra("keygood");
        path="/data/user/0/com.example.zqf.store/cache/bmob/"+good.getName()+".jpg";
        textView.setText(good.getName());
        textView2.setText(good.getDescribe());
        Bitmap bt= BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bt);
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
