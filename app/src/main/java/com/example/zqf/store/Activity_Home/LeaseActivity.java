package com.example.zqf.store.Activity_Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zqf.store.Bean.Good;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LeaseActivity extends DigitalActivity {

    @Override
    public void getData(){
        from=6;
        goodsdata=new String[2];        //左侧分类
        goodsdata[0]="西装";
        goodsdata[1]="电动车";

        query = new BmobQuery<Good>();              //按条件查找右侧list    (1)
        query.addWhereEqualTo("type","西装");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){;
                    goods0=object;                        //获取传递数据成功
                    //toast("success0");
                    listView_de.setAdapter(goodAdapter);            //初始化
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });

        query = new BmobQuery<Good>();              //按条件查找右侧list    (1)
        query.addWhereEqualTo("type","电动车");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){;
                    goods1=object;                        //获取传递数据成功
                    //toast("success1");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });


    }
}
