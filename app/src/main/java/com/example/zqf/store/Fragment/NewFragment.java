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

import com.example.zqf.store.Adapter.NewAdapter;
import com.example.zqf.store.Adapter.OrderAdapter;
import com.example.zqf.store.Bean.New;
import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.NewActivity;
import com.example.zqf.store.OrderActivity;
import com.example.zqf.store.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {

    ListView listView;
    List<New> list=new ArrayList<>();
    public NewFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new, container, false);

        listView=view.findViewById(R.id.listView_news);

        BmobQuery<New> bmobQuery = new BmobQuery<New>();
        bmobQuery.order("-updatedAt");
        bmobQuery.findObjects(new FindListener<New>() {
            @Override
            public void done(List<New> object, BmobException e) {
                if(e==null){
                    list=object;
                    NewAdapter adapter=new NewAdapter(getActivity(),R.layout.item_new,list);
                    listView.setAdapter(adapter);
                } else{
                    toast("失败："+e.getMessage());
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity(),NewActivity.class);
                intent.putExtra("newobj",list.get(i));
                startActivity(intent);

            }
        });

        return view;
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }



}
