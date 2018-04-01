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
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.Good_detailActivity;
import com.example.zqf.store.R;
import com.example.zqf.store.SumActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by admin on 2018/3/23.
 */

public class Brand_DetailActivity extends AppCompatActivity {
    TextView tx51,tx54,tx58,tx61,tx65,tx63,tx81;
    Button button;
    ImageView imageView;
    Good good=new Good();
    List<Good> goods=new ArrayList<>();
    String path;
    ActionBar bar;
    User user= BmobUser.getCurrentUser(User.class);
    int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse_form);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        good=(Good) intent.getSerializableExtra("good");
        state=intent.getIntExtra("state",-1);
        goods=user.getGoods();
        path="/data/user/0/com.example.zqf.store/cache/bmob/"+good.getName()+".jpg";

        BmobQuery<Good> query = new BmobQuery<>();
        query.getObject(good.getObjectId(), new QueryListener<Good>() {
            @Override
            public void done(Good good, BmobException e) {
                if(e==null){
                    download(good.getPicGood());
                }
            }
        });

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
        tx65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Brand_DetailActivity.this, Good_detailActivity.class);
                intent.putExtra("keygood",good);
                startActivity(intent);
            }
        });
        tx63=findViewById(R.id.textView63);
        tx63.setText(good.getPrice()+"");
        tx81=findViewById(R.id.textView81);
        tx81.setText(good.getMasterClass());

        imageView=findViewById(R.id.imageView3);

        Bitmap bt= BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bt);

        button=findViewById(R.id.button21);
        button.setVisibility(View.INVISIBLE);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.like,menu);
        MenuItem item=menu.findItem(R.id.like);
        if(state==0)
            item.setTitle("收藏");
        else if(state==1)
            item.setTitle("已收藏");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return false;
            case R.id.like:
                if(item.getTitle().toString().equals("收藏")){
                    goods.add(good);
                    user.setGoods(goods);
                    user.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                toast("已收藏");
                            }else{
                                toast(e.getMessage());
                            }
                        }
                    });
                    item.setTitle("已收藏");
                }if(item.getTitle().toString().equals("已收藏")){
                    goods.remove(good);
                    user.setGoods(goods);
                    user.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                toast("已取消");
                            }else{
                                toast(e.getMessage());
                            }
                        }
                    });
                    item.setTitle("收藏");
                }
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }
    }

    public void download(BmobFile picUser) {
        picUser.download(new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    //Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"失败！", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgress(Integer integer, long l) {

            }
        });
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }
}
