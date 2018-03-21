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

public class MarketActivity extends AppCompatActivity {
    public static MarketActivity A;
    private String[] goodsdata;             //左侧数据源
    public String good="汽水饮料";
    BmobQuery<Good> query;
    private List<Good> goods0,goods1,goods2,goods3,goods4,goods5;
    GoodAdapter goodAdapter;
    ListView listView_de;
    TextView price1;
    Button button1;
    List<Good> last_list;
    float sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        A=this;
        setContentView(R.layout.activity_market);
        price1=(TextView)findViewById(R.id.shop_price);
        button1=(Button)findViewById(R.id.shop_sure);
        last_list=new ArrayList <>();

        listView_de=(ListView)findViewById(R.id.market_de_list);     //右侧listview
        goodAdapter=new GoodAdapter(this);                  //右侧适配器
        getData();          //正确,初始化界面

        ArrayAdapter<String> adapter=new ArrayAdapter <String>(MarketActivity.this,
                android.R.layout.simple_list_item_1,goodsdata);                //左侧list的适配器
        final ListView listView=(ListView)findViewById(R.id.market_list);         //左侧listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {         //左侧点击
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position1, long l) {
                good=goodsdata[position1];                      //ok
//                switch (good){                                    //没图片
//                    case "汽水饮料":
//                        goodAdapter=new GoodAdapter(getBaseContext(),goods0);
//                    case "休闲零食":
//                        goodAdapter=new GoodAdapter(getBaseContext(),goods1);
//                    case "牛奶乳品":
//                        goodAdapter=new GoodAdapter(getBaseContext(),goods2);
//                    case "饼干糕点":
//                        goodAdapter=new GoodAdapter(getBaseContext(),goods3);
//                    case "坚果蜜饯":
//                        goodAdapter=new GoodAdapter(getBaseContext(),goods4);
//                    case "糖果冲饮":
//                        goodAdapter=new GoodAdapter(getBaseContext(),goods5);
//                    default:
//                        goodAdapter=new GoodAdapter(getBaseContext(),goods0);
//                }
                listView_de.setAdapter(goodAdapter);

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {         //提交购买
            @Override
            public void onClick(View view) {
                for(int i=0;i<goods0.size();i++){
                    if(goods0.get(i).getNumber()>0)
                        last_list.add(goods0.get(i));
                }
                for(int i=0;i<goods1.size();i++){
                    if(goods1.get(i).getNumber()>0)
                        last_list.add(goods1.get(i));
                }
                for(int i=0;i<goods2.size();i++){
                    if(goods2.get(i).getNumber()>0)
                        last_list.add(goods2.get(i));
                }
                for(int i=0;i<goods3.size();i++){
                    if(goods3.get(i).getNumber()>0)
                        last_list.add(goods3.get(i));
                }
                for(int i=0;i<goods4.size();i++){
                    if(goods4.get(i).getNumber()>0)
                        last_list.add(goods4.get(i));
                }
                for(int i=0;i<goods5.size();i++){
                    if(goods5.get(i).getNumber()>0)
                        last_list.add(goods5.get(i));
                }
//                for(int i=0;i<last_list.size();i++)         //ok,结果集在last_list中
//                    toast(last_list.get(i).getName());
                Intent intent=new Intent(MarketActivity.this, SumActivity.class);
                intent.putExtra("key",(Serializable)last_list);
                intent.putExtra("sum",sum);
                startActivity(intent);
            }
        });

    }

    private void getData(){
        goodsdata=new String[6];        //左侧分类
        goodsdata[0]="汽水饮料";
        goodsdata[1]="休闲零食";
        goodsdata[2]="牛奶乳品";
        goodsdata[3]="饼干糕点";
        goodsdata[4]="坚果蜜饯";
        goodsdata[5]="糖果冲饮";

        query = new BmobQuery<Good>();              //按条件查找右侧list    (1)
        query.addWhereEqualTo("type","汽水饮料");  // 查询当前用户的所有日程
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
        query.addWhereEqualTo("type","休闲零食");  // 查询当前用户的所有日程
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
        query.addWhereEqualTo("type","牛奶乳品");  // 查询当前用户的所有日程
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
        query.addWhereEqualTo("type","饼干糕点");  // 查询当前用户的所有日程
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
        query.addWhereEqualTo("type","坚果蜜饯");  // 查询当前用户的所有日程
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
        query.addWhereEqualTo("type","糖果冲饮");  // 查询当前用户的所有日程
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

    public class GoodAdapter extends BaseAdapter{           //适配器
        Bitmap pic;
        String path;//文件保存路径
        private LayoutInflater mInflater;
        private List<Good> good_list;
        public GoodAdapter(Context context){
            this.mInflater=LayoutInflater.from(context);
        }
        public GoodAdapter(Context context,List<Good> list){
            this.mInflater=LayoutInflater.from(context);
            good_list=list;
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

            switch (good){
                case "汽水饮料":
                    return goods0.size();
                case "休闲零食":
                    return goods1.size();
                case "牛奶乳品":
                    return goods2.size();
                case "饼干糕点":
                    return goods3.size();
                case "坚果蜜饯":
                    return goods4.size();
                case "糖果冲饮":
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

            switch (good){
                case "汽水饮料":
                    name.setText((String) goods0.get(position).getName());
                    price.setText(goods0.get(position).getPrice()+"元");                  //bug
                    number.setText(goods0.get(position).getNumber()+"");
                    path="/data/user/0/com.example.zqf.store/cache/bmob/"+goods0.get(position).getName()+".jpg";
                    File F0=new File(path);
                    if(!F0.exists()) {
                        BmobQuery<Good> query = new BmobQuery<>();
                        query.getObject(goods0.get(position).getObjectId(), new QueryListener<Good>() {
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
                case "休闲零食":
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
                case "牛奶乳品":
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
                case "饼干糕点":
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
                case "坚果蜜饯":
                    name.setText((String) goods4.get(position).getName());
                    price.setText(goods4.get(position).getPrice()+"元");                  //bug
                    number.setText(goods4.get(position).getNumber()+"");
                    path="/data/user/0/com.example.zqf.store/cache/bmob/"+goods4.get(position).getName()+".jpg";
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
                case "糖果冲饮":
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

                    switch (good){
                        case "汽水饮料":
                            if(goods0.get(position).getNumber()>0){
                                goods0.get(position).setNumber(goods0.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods0.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case "休闲零食":
                            if(goods1.get(position).getNumber()>0){
                                goods1.get(position).setNumber(goods1.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods1.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case "牛奶乳品":
                            if(goods2.get(position).getNumber()>0){
                                goods2.get(position).setNumber(goods2.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods2.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case "饼干糕点":
                            if(goods3.get(position).getNumber()>0){
                                goods3.get(position).setNumber(goods3.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods3.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case "坚果蜜饯":
                            if(goods4.get(position).getNumber()>0){
                                goods4.get(position).setNumber(goods4.get(position).getNumber()-1);
                                goodAdapter.notifyDataSetChanged();
                                sum=sum-goods4.get(position).getPrice();
                                price1.setText("总计："+sum+"元");
                            }
                            break;
                        case "糖果冲饮":
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

                    switch (good){
                        case "汽水饮料":
                            goods0.get(position).setNumber(goods0.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods0.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case "休闲零食":
                            goods1.get(position).setNumber(goods1.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods1.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case "牛奶乳品":
                            goods2.get(position).setNumber(goods2.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods2.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case "饼干糕点":
                            goods3.get(position).setNumber(goods3.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods3.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case "坚果蜜饯":
                            goods4.get(position).setNumber(goods4.get(position).getNumber()+1);
                            goodAdapter.notifyDataSetChanged();
                            sum=sum+goods4.get(position).getPrice();
                            price1.setText("总计："+sum+"元");
                            break;
                        case "糖果冲饮":
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
    }

    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }
}
