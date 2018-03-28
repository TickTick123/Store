package com.example.zqf.store;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;

public class Good_detailActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    Good good;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);
        textView=findViewById(R.id.textView72);
        imageView=findViewById(R.id.good_big);
        Intent intent=getIntent();
        good=(Good) intent.getSerializableExtra("keygood");
        path="/data/user/0/com.example.zqf.store/cache/bmob/"+good.getName()+".jpg";
        textView.setText(good.getName()+"\n"+good.getDescribe());
        Bitmap bt= BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bt);
    }
    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }
}
