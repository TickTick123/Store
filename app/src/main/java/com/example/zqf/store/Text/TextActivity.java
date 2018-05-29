package com.example.zqf.store.Text;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.zqf.store.R;

//用于测试加载界面

public class TextActivity extends AppCompatActivity {

    private ImageView img_loading;

    private AnimationDrawable AniDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        img_loading = (ImageView)findViewById(R.id.img_loading);
        img_loading.setBackgroundResource(R.drawable.imgloading);
        AniDraw = (AnimationDrawable)img_loading.getBackground();
        AniDraw.start();
    }
}
