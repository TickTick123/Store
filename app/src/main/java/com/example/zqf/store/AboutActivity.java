package com.example.zqf.store;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("帮助与关于");
        TextView textView=findViewById(R.id.textView69);
        textView.setText("    这是一款多方位服务大学生的综合性线上服务APP，服务内容包括数码商城，便捷超市，快递取送，快速打印，跳蚤市场，潮牌代购，便捷租赁等。所有的服务内容都是以方便同学们的生活为出发点，争取为同学们提供最优最便捷的服务！\n" +
                "声明:\n" +
                "    数码商城的3C产品均通过正当渠道与生产厂商达成合作，全场正品，原装出售，支持验货，假一赔十。质量问题15天包退换，手机一年保修，电脑两年保修。\n" +
                "    便捷超市营业时间为21:00到00:00，在线下单，9分钟必达。\n" +
                "    跳蚤市场和潮牌代购服务，本团队只为商家提供消息发布平台，用户通过线上浏览，线下沟通的方式与卖家进行交易。");
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
