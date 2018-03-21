package com.example.zqf.store.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.R;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.QueryListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by admin on 2018/3/19.
 */

public class FinalGoodAdapter extends ArrayAdapter<Good> {
    private int resourceId;
    Bitmap pic;
    String path;//文件保存路径
    public FinalGoodAdapter(Context context,int textViewResourceId,List<Good> list){
        super(context,textViewResourceId,list);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Good good2=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        path="/data/user/0/com.example.zqf.store/cache/bmob/"+good2.getName()+".jpg";
        ImageView good_pic2=view.findViewById(R.id.good_pic2);
        File F=new File(path);
        if(!F.exists()) {
            BmobQuery<Good> query = new BmobQuery<>();
            query.getObject(good2.getObjectId(), new QueryListener<Good>() {
                @Override
                public void done(Good good, BmobException e) {
                    if(e==null){
                        download(good.getPicGood());
                    }
                }
            });
        }
        pic= BitmapFactory.decodeFile(path);
        good_pic2.setImageBitmap(pic);

        TextView good_name2=view.findViewById(R.id.good_name2);
        TextView good_price2=view.findViewById(R.id.good_price2);
        TextView good_num2=view.findViewById(R.id.good_num2);
        good_name2.setText(good2.getName());
        good_price2.setText(good2.getPrice()+"元");
        good_num2.setText(good2.getNumber()+"");
        return view;
    }

    private void download(BmobFile picUser) {
        picUser.download(new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    //Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
                }
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
