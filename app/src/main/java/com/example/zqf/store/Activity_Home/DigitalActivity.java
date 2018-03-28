package com.example.zqf.store.Activity_Home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Good_detailActivity;
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

import static android.graphics.Color.GRAY;
import static android.graphics.Color.WHITE;

public class DigitalActivity extends AppCompatActivity {
    public String[] goodsdata;             //左侧数据源
    BmobQuery<Good> query;
    public List<Good> goods0=null,goods1=null,goods2=null,goods3=null,goods4=null,goods5=null;
    GoodAdapter1 goodAdapter;
    ListView listView_de;
    TextView price1;
    Button button1;
    List<Good> last_list;
    ActionBar bar;
    Good good9;
    float sum=0;
    int good1=0,from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        price1=(TextView)findViewById(R.id.shop_price);
        price1.setTextColor(WHITE);
        price1.setBackgroundColor(GRAY);
        button1=(Button)findViewById(R.id.shop_sure);
        button1.setTextColor(WHITE);
        last_list=new ArrayList<>();

        listView_de=(ListView)findViewById(R.id.market_de_list);                    //右侧listview
        goodAdapter=new GoodAdapter1(this);                  //右侧适配器
        getData();          //正确,初始化界面

        ArrayAdapter<String> adapter=new ArrayAdapter <String>(DigitalActivity.this,
                android.R.layout.simple_list_item_1,goodsdata);                //左侧list的适配器
        final ListView listView=(ListView)findViewById(R.id.market_list);         //左侧listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {         //左侧点击
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position1, long l) {
                good1=position1;                      //ok
                listView_de.setAdapter(goodAdapter);

            }
        });

        listView_de.setOnItemClickListener(new AdapterView.OnItemClickListener() {         //右侧点击
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position1, long l) {

                //toast("11");
                if(good1==0)
                    good9=goods0.get(position1);
                if(good1==1)
                    good9=goods1.get(position1);
                if(good1==2)
                    good9=goods2.get(position1);
                if(good1==3)
                    good9=goods3.get(position1);
                if(good1==4)
                    good9=goods4.get(position1);
                if(good1==5)
                    good9=goods5.get(position1);

                Intent intent=new Intent(DigitalActivity.this, Good_detailActivity.class);
                intent.putExtra("keygood",good9);
                startActivity(intent);

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {         //提交购买
            @Override
            public void onClick(View view) {
                if(sum<5){
                    new AlertDialog.Builder(DigitalActivity.this).setTitle("提示")
                            .setMessage("够买未满5元！")
                            .setPositiveButton("确定", null).show();
                }else{
                    if(goods0!=null)
                    for(int i=0;i<goods0.size();i++){
                        if(goods0.get(i).getNumber()>0)
                            last_list.add(goods0.get(i));
                    }
                    if(goods1!=null)
                    for(int i=0;i<goods1.size();i++){
                        if(goods1.get(i).getNumber()>0)
                            last_list.add(goods1.get(i));
                    }
                    if(goods2!=null)
                    for(int i=0;i<goods2.size();i++){
                        if(goods2.get(i).getNumber()>0)
                            last_list.add(goods2.get(i));
                    }
                    if(goods3!=null)
                    for(int i=0;i<goods3.size();i++){
                        if(goods3.get(i).getNumber()>0)
                            last_list.add(goods3.get(i));
                    }
                    if(goods4!=null)
                    for(int i=0;i<goods4.size();i++){
                        if(goods4.get(i).getNumber()>0)
                            last_list.add(goods4.get(i));
                    }
                    if(goods5!=null)
                    for(int i=0;i<goods5.size();i++){
                        if(goods5.get(i).getNumber()>0)
                            last_list.add(goods5.get(i));
                    }
                    Intent intent=new Intent(DigitalActivity.this, SumActivity.class);
                    intent.putExtra("key",(Serializable)last_list);
                    intent.putExtra("sum",sum);
                    intent.putExtra("from",from);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void getData(){
        from=1;
        goodsdata=new String[6];        //左侧分类
        goodsdata[0]="手机";
        goodsdata[1]="电脑";
        goodsdata[2]="数码相机";
        goodsdata[3]="手机配件";
        goodsdata[4]="电脑配件";
        goodsdata[5]="路由器";

        query = new BmobQuery<Good>();              //按条件查找右侧list    (1)
        query.addWhereEqualTo("type","手机");  // 查询当前用户的所有日程
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
        query.addWhereEqualTo("type","电脑");  // 查询当前用户的所有日程
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
        query = new BmobQuery<Good>();              //按条件查找右侧list    (2)
        query.addWhereEqualTo("type","数码相机");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){;
                    goods2=object;                        //获取传递数据成功
                    //toast("success2");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });
        query = new BmobQuery<Good>();              //按条件查找右侧list    (3)
        query.addWhereEqualTo("type","手机配件");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){;
                    goods3=object;                        //获取传递数据成功
                    //toast("success3");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });
        query = new BmobQuery<Good>();              //按条件查找右侧list    (4)
        query.addWhereEqualTo("type","电脑配件");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){;
                    goods4=object;                        //获取传递数据成功
                    //toast("success4");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });
        query = new BmobQuery<Good>();              //按条件查找右侧list    (5)
        query.addWhereEqualTo("type","路由器");  // 查询当前用户的所有日程
        query.findObjects(new FindListener<Good>() {
            @Override
            public void done(List<Good> object, BmobException e) {
                if(e==null){;
                    goods5=object;                        //获取传递数据成功
                    //toast("success5");
                }else{
                    toast("失败："+e.getMessage());
                }
            }
        });

    }

    public class GoodAdapter1 extends BaseAdapter {           //适配器
        Bitmap pic;
        String path;//文件保存路径
        private LayoutInflater mInflater;
        private List<Good> good_list;
        public GoodAdapter1(Context context){
            this.mInflater=LayoutInflater.from(context);
        }

        @Override
        public Object getItem(int arg0){
            return null;
        }
        @Override
        public long getItemId(int arg0){
            return 0;
        }
        @Override
        public int getCount(){              //有变化

            switch (good1){
                case 0:
                    return goods0.size();
                case 1:
                    return goods1.size();
                case 2:
                    return goods2.size();
                case 3:
                    return goods3.size();
                case 4:
                    return goods4.size();
                case 5:
                    return goods5.size();
                default:
                    return goods0.size();
            }

        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent){        //有变化

            convertView=mInflater.inflate(R.layout.good_item,null);                 //有变化
            TextView name=(TextView)convertView.findViewById(R.id.good_name);
            TextView price=(TextView)convertView.findViewById(R.id.good_price);
            TextView number=(TextView)convertView.findViewById(R.id.good_num);
            ImageButton sub=(ImageButton)convertView.findViewById(R.id.good_sub);
            ImageButton add=(ImageButton)convertView.findViewById(R.id.good_add);
            ImageView good_pic=(ImageView)convertView.findViewById(R.id.good_pic);              //商品主图

            switch (good1){
                case 0:
                    name.setText((String) goods0.get(position).getName());
                    price.setText(goods0.get(position).getPrice()+"元");                  //bug
                    number.setText(goods0.get(position).getNumber()+"");
                    path="/data/user/0/com.example.zqf.store/cache/bmob/"+goods0.get(position).getName()+".jpg";
                    File F0=new File(path);
                    if(!F0.exists()) {
                        download(goods0.get(position).getPicGood());            //此路径下无图片
                        //toast("00");
                    }
                    pic= BitmapFactory.decodeFile(path);
                    good_pic.setImageBitmap(pic);
                    break;
                case 1:
                    name.setText((String) goods1.get(position).getName());
                    price.setText(goods1.get(position).getPrice()+"元");                  //bug
                    number.setText(goods1.get(position).getNumber()+"");
                    path="/data/user/0/com.example.zqf.store/cache/bmob/"+goods1.get(position).getName()+".jpg";
                    File F1=new File(path);
                    if(!F1.exists()) {
                        BmobQuery<Good> query = new BmobQuery<>();
                        query.getObject(goods1.get(position).getObjectId(), new QueryListener<Good>() {
                            @Override
                            public void done(Good good, BmobException e) {
                                if(e==null){
                                    download(good.getPicGood());
                                }
                            }
                        });
                    }
                    pic= BitmapFactory.decodeFile(path);
                    good_pic.setImageBitmap(pic);
                    break;
                case 2:
                    name.setText((String) goods2.get(position).getName());
                    price.setText(goods2.get(position).getPrice()+"元");                  //bug
                    number.setText(goods2.get(position).getNumber()+"");
                    path="/data/user/0/com.example.zqf.store/cache/bmob/"+goods2.get(position).getName()+".jpg";
                    File F2=new File(path);
                    if(!F2.exists()) {
                        BmobQuery<Good> query = new BmobQuery<>();
                        query.getObject(goods2.get(position).getObjectId(), new QueryListener<Good>() {
                            @Override
                            public void done(Good good, BmobException e) {
                                if(e==null){
                                    download(good.getPicGood());
                                }
                            }
                        });
                    }
                    pic= BitmapFactory.decodeFile(path);
                    good_pic.setImageBitmap(pic);
                    break;
                case 3:
                    name.setText((String) goods3.get(position).getName());
                    price.setText(goods3.get(position).getPrice()+"元");                  //bug
                    number.setText(goods3.get(position).getNumber()+"");
                    path="/data/user/0/com.example.zqf.store/cache/bmob/"+goods3.get(position).getName()+".jpg";
                    File F3=new File(path);
                    if(!F3.exists()) {
                        BmobQuery<Good> query = new BmobQuery<>();
                        query.getObject(goods3.get(position).getObjectId(), new QueryListener<Good>() {
                            @Override
                            public void done(Good good, BmobException e) {
                                if(e==null){
                                    download(good.getPicGood());
                                }
                            }
                        });
                    }
                    pic= BitmapFactory.decodeFile(path);
                    good_pic.setImageBitmap(pic);
                    break;
                case 4:
                    name.setText((String) goods4.get(position).getName());
                    price.setText(goods4.get(position).getPrice()+"元");                  //bug
                    number.setText(goods4.get(position).getNumber()+"");
                    path="/data/user/0/com.example.zqf.store/cache/bmob/"+goods4.get(position).getName()+".jpg";
                                                                                            //下载文件是后台文件名，这个路径是名字
                    File F4=new File(path);
                    if(!F4.exists()) {
                        BmobQuery<Good> query = new BmobQuery<>();
                        query.getObject(goods4.get(position).getObjectId(), new QueryListener<Good>() {
                            @Override
                            public void done(Good good, BmobException e) {
                                if(e==null){
                                    download(good.getPicGood());
                                }
                            }
                        });
                    }
                    pic= BitmapFactory.decodeFile(path);
                    good_pic.setImageBitmap(pic);
                    break;
                case 5:
                    name.setText((String) goods5.get(position).getName());
                    price.setText(goods5.get(position).getPrice()+"元");                  //bug
                    number.setText(goods5.get(position).getNumber()+"");
                    path="/data/user/0/com.example.zqf.store/cache/bmob/"+goods5.get(position).getName()+".jpg";
                    File F5=new File(path);
                    if(!F5.exists()) {
                        BmobQuery<Good> query = new BmobQuery<>();
                        query.getObject(goods5.get(position).getObjectId(), new QueryListener<Good>() {
                            @Override
                            public void done(Good good, BmobException e) {
                                if(e==null){
                                    download(good.getPicGood());
                                }
                            }
                        });
                    }
                    pic= BitmapFactory.decodeFile(path);
                    good_pic.setImageBitmap(pic);
                    break;

            }

            //减号响应
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    switch (good1){
                        case 0:
                            if(goods0.get(position).getNumber()>0){
                                goods0.get(position).setNumber(goods0.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods0.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case 1:
                            if(goods1.get(position).getNumber()>0){
                                goods1.get(position).setNumber(goods1.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods1.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case 2:
                            if(goods2.get(position).getNumber()>0){
                                goods2.get(position).setNumber(goods2.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods2.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case 3:
                            if(goods3.get(position).getNumber()>0){
                                goods3.get(position).setNumber(goods3.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods3.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case 4:
                            if(goods4.get(position).getNumber()>0){
                                goods4.get(position).setNumber(goods4.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods4.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case 5:
                            if(goods5.get(position).getNumber()>0){
                                goods5.get(position).setNumber(goods5.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods5.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        default:
                            goods0.get(position).setNumber(goods0.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum-goods0.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                    }

                }
            });
            add.setOnClickListener(new View.OnClickListener() {     //加号响应
                @Override
                public void onClick(View view) {

                    switch (good1){
                        case 0:
                            goods0.get(position).setNumber(goods0.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods0.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case 1:
                            goods1.get(position).setNumber(goods1.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods1.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case 2:
                            goods2.get(position).setNumber(goods2.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods2.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case 3:
                            goods3.get(position).setNumber(goods3.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods3.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case 4:
                            goods4.get(position).setNumber(goods4.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods4.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case 5:
                            goods5.get(position).setNumber(goods5.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods5.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        default:
                            goods0.get(position).setNumber(goods0.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods0.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                    }

                }
            });

            return convertView;
        }


    }
    public void download(BmobFile picUser) {
        picUser.download(new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    //Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
                }
                else
                    toast("失败："+e.getMessage());
            }

            @Override
            public void onProgress(Integer integer, long l) {

            }
        });
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
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
