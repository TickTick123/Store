package com.example.zqf.store.Activity_Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.R;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * Created by admin on 2018/3/23.
 */

public class Brand_DetailActivity extends AppCompatActivity {
    TextView tx51,tx54,tx58,tx61,tx65,tx63;
    Button button;
    ImageView imageView;
    Good good=new Good();
    String path;
    ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse_form);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        good=(Good) intent.getSerializableExtra("good");
        path="/data/user/0/com.example.zqf.store/cache/bmob/"+good.getName()+".jpg";

        tx51=findViewById(R.id.textView51);
        tx51.setText(good.getMasterneme());
        tx54=findViewById(R.id.textView54);
        tx54.setText(good.getMasterQQ());
        tx58=findViewById(R.id.textView58);
        tx58.setText(good.getMasterphone());
        tx61=findViewById(R.id.textView61);
        tx61.setText(good.getName());
        tx65=findViewById(R.id.textView65);
        tx65.setText(good.getDescribe());
        tx63=findViewById(R.id.textView63);
        tx63.setText(good.getPrice()+"");

        imageView=findViewById(R.id.imageView3);
        Bitmap bt= BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bt);

        button=findViewById(R.id.button21);
        button.setVisibility(View.INVISIBLE);
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
