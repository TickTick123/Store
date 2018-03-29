package com.example.zqf.store.Activity_Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;

import com.example.zqf.store.MainActivity;
import com.example.zqf.store.R;
import com.example.zqf.store.SumActivity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class MarketActivity extends DigitalActivity {
    //public static MarketActivity A;

    @Override
    public void getData(){
        from=0;
        goodsdata=new String[8];        //左侧分类
        goodsdata[0]="汽水饮料";
        goodsdata[1]="休闲零食";
        goodsdata[2]="牛奶乳品";
        goodsdata[3]="饼干糕点";
        goodsdata[4]="坚果蜜饯";
        goodsdata[5]="糖果冲饮";
        goodsdata[6]="日用百货";
        goodsdata[7]="时令鲜果";


        query = new BmobQuery<Good>();              //按条件查找右侧list    (1)
        query.addWhereEqualTo("type","汽水饮料");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goods0=object;                        //获取传递数据成功
                    //toast("success0");
                    listView_de.setAdapter(goodAdapter);            //初始化
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });

        query = new BmobQuery<Good>();              //按条件查找右侧list    (1)
        query.addWhereEqualTo("type","休闲零食");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goods1=object;                        //获取传递数据成功
                    //toast("success1");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });
        query = new BmobQuery<Good>();              //按条件查找右侧list    (2)
        query.addWhereEqualTo("type","牛奶乳品");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goods2=object;                        //获取传递数据成功
                    //toast("success2");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });
        query = new BmobQuery<Good>();              //按条件查找右侧list    (3)
        query.addWhereEqualTo("type","饼干糕点");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goods3=object;                        //获取传递数据成功
                    //toast("success3");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });
        query = new BmobQuery<Good>();              //按条件查找右侧list    (4)
        query.addWhereEqualTo("type","坚果蜜饯");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goods4=object;                        //获取传递数据成功
                    //toast("success4");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });
        query = new BmobQuery<Good>();              //按条件查找右侧list    (5)
        query.addWhereEqualTo("type","糖果冲饮");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goods5=object;                        //获取传递数据成功
                    //toast("success5");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });

        query = new BmobQuery<Good>();              //按条件查找右侧list    (5)
        query.addWhereEqualTo("type","日用百货");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goods6=object;                        //获取传递数据成功
                    //toast("success5");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });

        query = new BmobQuery<Good>();              //按条件查找右侧list    (5)
        query.addWhereEqualTo("type","时令鲜果");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){
                    goods7=object;                        //获取传递数据成功
                    //toast("success5");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });


    }


}
