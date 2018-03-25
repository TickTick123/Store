package com.example.zqf.store.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zqf.store.Bean.New;
import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.R;

import java.util.List;

/**
 * Created by dell on 2018/3/25.
 */

public class NewAdapter extends ArrayAdapter<New> {
    private int resourceId;
    public NewAdapter(Context context, int textViewResourceId, List<New> list){
        super(context,textViewResourceId,list);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        New anew=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView tx22=view.findViewById(R.id.textView_title);
        tx22.setText(anew.getTitle());
        TextView tx27=view.findViewById(R.id.textView_content);
        tx27.setText(anew.getContent());
        return view;
    }
}
