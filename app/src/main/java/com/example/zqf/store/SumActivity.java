package com.example.zqf.store;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Activity_Home.MarketActivity;
import com.example.zqf.store.Activity_My.AddressActivity;
import com.example.zqf.store.Activity_My.SettingActivity;
import com.example.zqf.store.Adapter.FinalGoodAdapter;
import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.Bean.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.WHITE;

public class SumActivity extends AppCompatActivity {
    TextView tx6,tx7,tx9,tx11,tx13;
    Button but16;
    ListView listView;
    User user= BmobUser.getCurrentUser(User.class);
    List<Good> goodlist=new ArrayList<>();
    Order order=new Order();
    ActionBar bar;
    float sum;
    int from;
    String obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);

        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        goodlist= (List<Good>) intent.getSerializableExtra("key");//接收商品表
        sum=intent.getFloatExtra("sum",-1f);//接收总价
        from=intent.getIntExtra("from",-1);

        tx6=findViewById(R.id.textView6);
        tx6.setTextColor(WHITE);
        tx6.setBackgroundColor(GRAY);
        tx6.setText("总计："+sum+"元");
        tx7=findViewById(R.id.textView7);
        tx7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = user.getAddress().size();
                final String[] address =user.getAddress().toArray(new String[size]);
                AlertDialog.Builder builder=new AlertDialog.Builder(SumActivity.this);
                builder.setTitle("请选择地址").setIcon(android.R.drawable.ic_dialog_map)
                        .setItems(address, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx7.setText(address[i]);
                            }
                        }).show();
            }
        });
        tx9=findViewById(R.id.textView9);
        tx9.setText(user.getnicName());
//        tx9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final EditText x=new EditText(SumActivity.this);
//                x.setText(tx9.getText());
//                new AlertDialog.Builder(SumActivity.this).setTitle("修改收件人").setView(x)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                tx9.setText(x.getText().toString());
//                            }
//                        }).show();
//            }
//        });
        tx11=findViewById(R.id.textView11);
        tx11.setText(user.getMobilePhoneNumber());
//        tx11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final EditText x=new EditText(SumActivity.this);
//                x.setText(tx11.getText());
//                new AlertDialog.Builder(SumActivity.this).setTitle("修改手机号").setView(x)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                tx11.setText(x.getText().toString());
//                            }
//                        }).show();
//            }
//        });
        tx13=findViewById(R.id.textView13);
        tx13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(SumActivity.this);
                new AlertDialog.Builder(SumActivity.this).setTitle("添加备注").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx13.setText(x.getText().toString());
                            }
                        }).show();
            }
        });

        FinalGoodAdapter adapter=new FinalGoodAdapter(SumActivity.this,R.layout.item_finalgood,goodlist);
        listView=findViewById(R.id.shoplastlist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(SumActivity.this, Good_detailActivity.class);
                intent.putExtra("keygood",goodlist.get(i));
                startActivity(intent);
            }
        });
        but16=findViewById(R.id.button16);
        but16.setTextColor(WHITE);
        but16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tx7.getText().equals("请选择地址")){
                    new AlertDialog.Builder(SumActivity.this).setTitle("提示")
                            .setIcon(android.R.drawable.ic_dialog_info).setMessage("请选择地址！")
                            .setPositiveButton("确定", null).show();
                }else{
                    order.setFrom(from);
                    order.setGoods(goodlist);
                    order.setUser(user);
                    order.setSum(sum);
                    order.setAddress(tx7.getText().toString());
                    order.setState("配送中");//状态
                    order.setTips(tx13.getText().toString());

                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.pay_dialog, (ViewGroup) findViewById(R.id.pay_dialog));
                    ImageView im=layout.findViewById(R.id.imageView_pay);
                    AlertDialog.Builder ab= new AlertDialog.Builder(SumActivity.this);
                    ab.setView(layout).show();
                    im.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final String[] Items={"扫描图中二维码"};
                            new AlertDialog.Builder(SumActivity.this).setItems(Items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(i==0){
                                        order.save(new SaveListener<String>() {
                                            @Override
                                            public void done(String objectId,BmobException e) {
                                                if(e==null){
                                                    obj=objectId;
                                                    Intent intent=new Intent(SumActivity.this,OrderActivity.class);
                                                    intent.putExtra("obj",obj);
                                                    intent.putExtra("order",order);
                                                    startActivity(intent);
                                                    finish();
                                                }else{
                                                    toast("创建数据失败：" + e.getMessage());
                                                }
                                            }
                                        });
                                    }
                                }
                            }).show();
                        }
                    });
                }
            }
        });
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
