package com.example.zqf.store.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zqf.store.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by admin on 2018/2/12.
 */

public class My extends Fragment{
    ImageView ivHead;//头像显示
    Bitmap head;//头像Bitmap
    AlertDialog.Builder builder;//AlertDialog构造器
    AlertDialog.Builder builder2;
    static String path = " Environment.getExternalStorageDirectory().getPath()";//sd路径
    public My (){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my, container, false);
        ivHead=view.findViewById(R.id.imageView4);
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
                                builder2=new AlertDialog.Builder(getActivity());
                                builder2.setMessage("相机无法启动，请先开启相机权限");
                                builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i){

                                    }
                                });
                                builder2.setCancelable(false);
                                AlertDialog dialog2=builder2.create();
                                dialog2.show();
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
                        /**  
                        * 上传服务器代码  
                        */
                        setPicToView(head);//保存在SD卡中  
                        ivHead.setImageBitmap(head);//用ImageView显示出来  
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //找到指定URI对应的资源图片  
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop","true");
        // aspectX aspectY 是宽高的比例  
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        // outputX outputY 是裁剪图片宽高  
        intent.putExtra("outputX",80);
        intent.putExtra("outputY",80);
        intent.putExtra("return-data",true);
        //进入系统裁剪图片的界面  
        startActivityForResult(intent,3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd卡是否可用  
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建以此File对象为名（path）的文件夹  
        String fileName = path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100,b);// 把数据写入文件（compress：压缩）
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流  
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
