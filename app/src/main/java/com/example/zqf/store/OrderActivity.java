package com.example.zqf.store;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Activity_Home.DigitalActivity;
import com.example.zqf.store.Adapter.FinalGoodAdapter;
import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.Bean.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.WHITE;

/**
 * Created by admin on 2018/3/20.
 */

public class OrderActivity extends AppCompatActivity {
    TextView tx17,tx19,tx21,tx24,tx16;
    Button but17;
    ListView listView;
    List<Good> goodlist=new ArrayList<>();
    Order order=new Order();
    User user= BmobUser.getCurrentUser(User.class);
    ActionBar bar;

    String objectid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        order=(Order)intent.getSerializableExtra("order");
        objectid=intent.getStringExtra("obj");

        tx16=findViewById(R.id.textView16);
        tx17=findViewById(R.id.textView17);
        tx19=findViewById(R.id.textView19);
        tx21=findViewById(R.id.textView21);
        tx24=findViewById(R.id.textView24);

        tx16.setText(order.getEvaluate());
        tx17.setText(order.getAddress());
        tx19.setText(user.getnicName()+"("+user.getMobilePhoneNumber()+")");
        tx21.setText(order.getTips());
        tx24.setText(order.getState());
        goodlist=order.getGoods();

        listView=findViewById(R.id.ordershoplist);
        FinalGoodAdapter adapter=new FinalGoodAdapter(OrderActivity.this,R.layout.item_finalgood,goodlist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(OrderActivity.this, Good_detailActivity.class);
                intent.putExtra("keygood",goodlist.get(i));
                startActivity(intent);
            }
        });

        but17=findViewById(R.id.button17);
        but17.setTextColor(WHITE);
        if(tx24.getText().equals("配送中")){
            but17.setText("确认收货");
        }else if(tx24.getText().equals("待评价")){
            but17.setText("写评价");
        }else if(tx24.getText().equals("已完成")){
            but17.setVisibility(View.INVISIBLE);
        }

        but17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(but17.getText().equals("确认收货")){
                    tx24.setText("待评价");
                    but17.setText("写评价");
                }else if(but17.getText().equals("写评价")){
                    final EditText x = new EditText(OrderActivity.this);
                    new AlertDialog.Builder(OrderActivity.this).setTitle("请填写评价").setView(x)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    tx16.setText(x.getText().toString());
                                    tx24.setText("已完成");
                                }
                            }).show();
                    but17.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    protected void onDestroy(){//用于向数据库中更新数据
        super.onDestroy();
        order.setEvaluate(tx16.getText().toString());//评价
        order.setState(tx24.getText().toString());//状态
        order.update(objectid ,new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){

                }else{
                    toast("更新失败：" + e.getMessage());
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
                setResult(RESULT_OK);
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
