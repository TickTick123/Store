package com.example.zqf.store.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Activity_My.AddressActivity;
import com.example.zqf.store.Activity_My.SettingActivity;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.MainActivity;
import com.example.zqf.store.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UploadFileListener;
import rx.functions.Action1;


import static android.app.Activity.RESULT_OK;
import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by admin on 2018/2/12.
 */

public class My extends Fragment {

    ImageView ivHead;//头像显示
    Bitmap head;//头像Bitmap
    AlertDialog.Builder builder;//AlertDialog构造器
    User user;
    Button but8,but9,but10,but11;
    TextView Text,Text2;
    String path;//头像文件路径
    static String path0="/data/user/0/com.example.zqf.store/cache/bmob/head.jpg";

    public My (){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my, container, false);

        user = BmobUser.getCurrentUser(User.class);
        ivHead=view.findViewById(R.id.imageView4);

        Text=view.findViewById(R.id.textView);
        Text.setText(user.getMobilePhoneNumber());
        Text2=view.findViewById(R.id.textView2);
        Text2.setText(user.getnicName());

        but8=view.findViewById(R.id.button8);
        but9=view.findViewById(R.id.button9);
        but10=view.findViewById(R.id.button10);
        but11=view.findViewById(R.id.button11);
        but8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent address =new Intent(getApplicationContext(), AddressActivity.class);
                startActivity(address);
            }
        });
        but9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        but10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.support.v7.app.AlertDialog.Builder(getActivity()).setTitle("客服电话")
                        .setIcon(android.R.drawable.ic_menu_call).
                        setItems(new String[] { "17352709806", "电话2" },null)
                        .show();
            }
        });
        but11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting =new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(setting);
            }
        });

        //若本地没有头像从数据库下载头像保存到本地
        File F=new File(path0);
        if(!F.exists()) {
            BmobQuery<User> query = new BmobQuery<>();
            query.getObject(user.getObjectId(), new QueryListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if (e == null) {
                        download(user.getPicUser());
                    }
                }
            });
        }

        Bitmap bt=BitmapFactory.decodeFile(path0);
        ivHead.setImageBitmap(bt);

        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder=new AlertDialog.Builder(getActivity());
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
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        return view;
    }

    private void download(BmobFile picUser) {
        picUser.download(new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    //Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
                    path=s;
                }
            }

            @Override
            public void onProgress(Integer integer, long l) {

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
                    File temp = new File(Environment.getExternalStorageDirectory()+"/head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片  
                }
                break;
            //调用系统裁剪图片后  
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        // 上传服务器代码
                        saveBitmap(head);
                        final BmobFile bmobFile = new BmobFile(new File(path0));
                        bmobFile.uploadblock(new UploadFileListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null) {
                                    user.setPicUser(bmobFile);
                                    user.updateObservable().subscribe(new Action1<Void>() {
                                        @Override
                                        public void call(Void aVoid) {
                                            //Toast.makeText(getApplicationContext(), "上传成功", Toast.LENGTH_LONG).show();
                                        }
                                    },new Action1<Throwable>(){
                                        @Override
                                        public void call(Throwable throwable) {
                                            Toast.makeText(getApplicationContext(), "上传失败", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"上传失败", Toast.LENGTH_LONG).show();
                            }
                        });
                        ivHead.setImageBitmap(head);//用ImageView显示出来
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
        File f = new File(path0);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
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

}
