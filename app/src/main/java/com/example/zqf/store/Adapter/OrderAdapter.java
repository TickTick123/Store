package com.example.zqf.store.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.R;

import java.util.List;

/**
 * Created by admin on 2018/3/21.
 */

public class OrderAdapter extends ArrayAdapter<Order> {
    private int resourceId;
    public OrderAdapter(Context context, int textViewResourceId, List<Order> list){
        super(context,textViewResourceId,list);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Order order=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView imageView=view.findViewById(R.id.imageView2);
        TextView tx22=view.findViewById(R.id.textView22);
        tx22.setText(order.getState());
        TextView tx26=view.findViewById(R.id.textView26);
        if(order.getFrom()==0){
            tx26.setText("便利超市");
            imageView.setImageResource(R.drawable.shop);
        }
        if(order.getFrom()==1){
            tx26.setText("数码商城");
            imageView.setImageResource(R.drawable.music);
        }
        if(order.getFrom()==2){
            tx26.setText("快递送货");
            imageView.setImageResource(R.drawable.box);
        }
        if(order.getFrom()==3){
            tx26.setText("快速打印");
            imageView.setImageResource(R.drawable.printer);
        }
        if(order.getFrom()==4){
            tx26.setText("跳蚤市场");
            imageView.setImageResource(R.drawable.recycle);
        }
        if(order.getFrom()==5){
            tx26.setText("潮牌代购");
            imageView.setImageResource(R.drawable.badge);
        }
        if(order.getFrom()==6){
            tx26.setText("便捷租赁");
            imageView.setImageResource(R.drawable.myspace);
        }
        TextView tx27=view.findViewById(R.id.textView27);
        tx27.setText("下单时间："+order.getCreatedAt());
        TextView tx28=view.findViewById(R.id.textView28);
        tx28.setText("总价："+order.getSum()+"");
        return view;
    }
}
