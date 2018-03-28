package com.example.zqf.store.Activity_Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.graphics.Color.WHITE;

/**
 * Created by admin on 2018/3/23.
 */

public class Reuse_FormActivity extends AppCompatActivity {
    TextView tx51,tx54,tx58,tx61,tx65,tx63,tx81;
    Button button;
    ImageView imageView;
    android.app.AlertDialog.Builder builder;
    Bitmap pic;
    ActionBar bar;
    Good good=new Good();
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse_form);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        tx51=findViewById(R.id.textView51);
        tx51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(Reuse_FormActivity.this);
                new AlertDialog.Builder(Reuse_FormActivity.this).setTitle("添加姓名").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx51.setText(x.getText().toString());
                            }
                        }).show();
            }
        });
        tx54=findViewById(R.id.textView54);
        tx54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(Reuse_FormActivity.this);
                new AlertDialog.Builder(Reuse_FormActivity.this).setTitle("添加QQ号").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx54.setText(x.getText().toString());
                            }
                        }).show();
            }
        });
        tx58=findViewById(R.id.textView58);
        tx58.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(Reuse_FormActivity.this);
                new AlertDialog.Builder(Reuse_FormActivity.this).setTitle("添加手机号").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx58.setText(x.getText().toString());
                            }
                        }).show();
            }
        });
        tx61=findViewById(R.id.textView61);
        tx61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(Reuse_FormActivity.this);
                new AlertDialog.Builder(Reuse_FormActivity.this).setTitle("添加名称").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx61.setText(x.getText().toString());
                                path="/data/user/0/com.example.zqf.store/cache/bmob/"+tx61.getText().toString()+".jpg";
                            }
                        }).show();
            }
        });
        tx65=findViewById(R.id.textView65);
        tx65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(Reuse_FormActivity.this);
                new AlertDialog.Builder(Reuse_FormActivity.this).setTitle("添加描述").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx65.setText(x.getText().toString());
                            }
                        }).show();
            }
        });
        tx63=findViewById(R.id.textView63);
        tx63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(Reuse_FormActivity.this);
                new AlertDialog.Builder(Reuse_FormActivity.this).setTitle("添加价格").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx63.setText(x.getText().toString());
                            }
                        }).show();
            }
        });
        tx81=findViewById(R.id.textView81);
        tx81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText x=new EditText(Reuse_FormActivity.this);
                new AlertDialog.Builder(Reuse_FormActivity.this).setTitle("填写学院班级").setView(x)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx81.setText(x.getText().toString());
                            }
                        }).show();
            }
        });

        imageView=findViewById(R.id.imageView3);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder=new android.app.AlertDialog.Builder(Reuse_FormActivity.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("请选择获取图片方式");
                final String[] Items={"从相册中选择","使用相机拍摄"};
                builder.setItems(Items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0) {
                            Intent intent1 = new Intent(Intent.ACTION_PICK, null);//返回被选中项的URI  
                            intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//得到所有图片的URI
                            startActivityForResult(intent1,1);
                        }
                        if(i==1){
                            try{
                                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//开启相机应用程序获取并返回图片（capture：俘获）  
                                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"head.jpg")));//指明存储图片或视频的地址URI  
                                startActivityForResult(intent2,2);//采用ForResult打开  
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(),"相机无法启动，请先开启相机权限", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                builder.setCancelable(true);
                android.app.AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        button=findViewById(R.id.button21);
        button.setTextColor(WHITE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tx61.getText().equals("")||
                        tx63.getText().equals("")||
                        tx65.getText().equals("")||
                        tx51.getText().equals("")||
                        tx54.getText().equals("")||
                        tx58.getText().equals("")||
                        tx81.getText().equals("")){
                    new AlertDialog.Builder(Reuse_FormActivity.this).setTitle("提示")
                            .setIcon(android.R.drawable.ic_dialog_info).setMessage("有信息未填完!")
                            .setPositiveButton("确定", null).show();
                }else{
                    good.setName(tx61.getText().toString());
                    good.setPrice(Float.parseFloat(tx63.getText().toString()));
                    good.setDescribe(tx65.getText().toString());
                    good.setMasterneme(tx51.getText().toString());
                    good.setMasterphone(tx58.getText().toString());
                    good.setMasterQQ(tx54.getText().toString());
                    good.setMasterClass(tx81.getText().toString());
                    good.setType("二手");
                    good.setNumber(1);
                    good.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null) {
                                finish();
                            }else{
                                toast("no"+e.toString());
                            }
                        }
                    });
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //从相册里面取相片的返回结果  
            case 1:
                if(resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片  
                }
                break;
            //相机拍照后的返回结果
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()+"/good.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片  
                }
                break;
            //调用系统裁剪图片后  
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    pic = extras.getParcelable("data");
                    if (pic != null) {
                        saveBitmap(pic);
                        final BmobFile bmobFile = new BmobFile(new File(path));
                        bmobFile.uploadblock(new UploadFileListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null) {
                                    good.setPicGood(bmobFile);
                                    imageView.setImageBitmap(pic);//用ImageView显示出来
                                }
                                else {
                                    toast("失败！");
                                }
                            }
                        });
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    public void cropPhoto(Uri uri) {          //调用系统的裁剪功能
        Intent intent = new Intent("com.android.camera.action.CROP");
        //找到指定URI对应的资源图片  
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop","true");
        // aspectX aspectY 是宽高的比例  
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        // outputX outputY 是裁剪图片宽高  
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        //进入系统裁剪图片的界面  
        startActivityForResult(intent,3);
    }

    public void saveBitmap(Bitmap bm) {//bitmap保存到本地路径
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
