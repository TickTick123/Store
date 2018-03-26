package com.example.zqf.store.Activity_My;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zqf.store.Bean.User;
import com.example.zqf.store.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


public class AddressActivity extends AppCompatActivity {
    User user= BmobUser.getCurrentUser(User.class);
    private List<String> address=new ArrayList<>();
    ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        address=user.getAddress();
        final ArrayAdapter<String> adapter =new ArrayAdapter<String>(
                AddressActivity.this,android.R.layout.simple_list_item_1,address);
        final ListView listView=findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int i, long l) {
                new AlertDialog.Builder(AddressActivity.this).setTitle("确定删除？").setIcon(
                        android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                address.remove(i);
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消",null).show();
                return false;
            }
        });
    }

    protected void onDestroy(){
        super.onDestroy();
        user.setAddress(address);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e!=null)
                    Toast.makeText(getApplicationContext(),"地址更新失败", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_address,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.address:
                final EditText x=new EditText(this);
                new AlertDialog.Builder(this).setTitle("请输入地址").setIcon(
                        android.R.drawable.ic_dialog_map).setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                address.add(x.getText().toString());
                            }
                        })
                        .setNegativeButton("取消",null).show();
                return true;
            case android.R.id.home:
                this.finish();
                return false;
        }
        return false;
    }
}
