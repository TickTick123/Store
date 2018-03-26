package com.example.zqf.store.Activity_Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.OrderActivity;
import com.example.zqf.store.R;
import com.example.zqf.store.SumActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.WHITE;

public class CourierActivity extends AppCompatActivity {
    TextView tx30,tx31,tx33,tx37,tx39,tx41;
    Button button;
    Order order=new Order();
    ActionBar bar;
    List<Good> goodlist=new ArrayList<>();
    User user= BmobUser.getCurrentUser(User.class);
    String obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        tx30=findViewById(R.id.textView30);
        tx30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(CourierActivity.this);
                new AlertDialog.Builder(CourierActivity.this).setTitle("修改取货地址").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx30.setText(x.getText().toString());
                            }
                        }).show();
            }
        });
        tx31=findViewById(R.id.textView31);
        tx31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(CourierActivity.this);
                new AlertDialog.Builder(CourierActivity.this).setTitle("修改取货号").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx31.setText(x.getText().toString());
                            }
                        }).show();
            }
        });
        tx33=findViewById(R.id.textView33);
        tx33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(CourierActivity.this);
                new AlertDialog.Builder(CourierActivity.this).setTitle("修改收件人").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx33.setText(x.getText().toString());
                            }
                        }).show();
            }
        });
        tx37=findViewById(R.id.textView37);
        tx37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = user.getAddress().size();
                final String[] address =user.getAddress().toArray(new String[size]);
                AlertDialog.Builder builder=new AlertDialog.Builder(CourierActivity.this);
                builder.setTitle("请选择地址").setIcon(android.R.drawable.ic_dialog_map)
                        .setItems(address, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx37.setText(address[i]);
                            }
                        }).show();
            }
        });
        tx39=findViewById(R.id.textView39);
        tx39.setText(user.getnicName());
        tx41=findViewById(R.id.textView41);
        tx41.setText(user.getMobilePhoneNumber());

        button=findViewById(R.id.button18);
        button.setTextColor(WHITE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tx37.getText().equals("请选择地址")||
                        tx30.getText().equals("请输入取货地址")||
                        tx31.getText().equals("请输入取货号")||
                        tx33.getText().equals("请输入取件人")){
                    new AlertDialog.Builder(CourierActivity.this).setTitle("提示")
                            .setIcon(android.R.drawable.ic_dialog_info).setMessage("有信息未填完!")
                            .setPositiveButton("确定", null).show();
                }else{
                    order.setUser(user);
                    order.setState("配送中");
                    order.setAddress(tx37.getText().toString());
                    order.setFrom(2);
                    order.setSum(2.f);
                    order.setGoods(goodlist);
                    order.setTips(tx30.getText().toString()+"|取货号："+tx31.getText().toString()+"|取件人："+tx33.getText().toString());
                    order.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId,BmobException e) {
                            if(e==null){
                                obj=objectId;
                                Intent intent=new Intent(CourierActivity.this,OrderActivity.class);
                                intent.putExtra("obj",obj);
                                intent.putExtra("order",order);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }

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
}
