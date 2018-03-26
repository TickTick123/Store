package com.example.zqf.store.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zqf.store.Adapter.OrderAdapter;
import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.OrderActivity;
import com.example.zqf.store.R;
import com.example.zqf.store.SumActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by admin on 2018/3/17.
 */

public class Dingdan extends Fragment {
    ListView listView;
    List<Order> orderlist=new ArrayList<>();
    User user= BmobUser.getCurrentUser(User.class);
    public Dingdan(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dingdan, container, false);

        listView=view.findViewById(R.id.listView2);

        BmobQuery<Order> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("user",user);  // 查询当前订单的所有日程
        bmobQuery.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> object, BmobException e) {
                if(e==null){
                    orderlist=object;
                    OrderAdapter adapter=new OrderAdapter(getActivity(),R.layout.item_order,orderlist);
                    listView.setAdapter(adapter);
                } else{
                    toast("失败："+e.getMessage());
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),OrderActivity.class);
                intent.putExtra("obj",orderlist.get(i).getObjectId());
                intent.putExtra("order",orderlist.get(i));
                startActivity(intent);
            }
        });

        return view;
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }
}
