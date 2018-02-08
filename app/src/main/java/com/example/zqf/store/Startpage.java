package com.example.zqf.store;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Startpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        Bmob.initialize(this, "14ad818ac5ca96d7f6166b841450afee");

        Good p2 = new Good();
        p2.setName("北京3环豪宅");
        p2.setType("住宅");
        p2.setDescribe("好东西");
        p2.setPrice("100000");
        p2.setMasterneme("张三");
        p2.setMasterphone("110");
        p2.setMasterQQ("2323123");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    toast("添加数据成功，返回objectId为："+objectId);
                }else{
                    toast("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    };  //Toast的快捷使用
}
