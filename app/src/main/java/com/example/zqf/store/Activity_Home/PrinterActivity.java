package com.example.zqf.store.Activity_Home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zqf.store.Bean.Good;
import com.example.zqf.store.Bean.Order;
import com.example.zqf.store.Bean.User;
import com.example.zqf.store.OrderActivity;
import com.example.zqf.store.R;
import com.example.zqf.store.SumActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import rx.functions.Action1;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.WHITE;
import static cn.bmob.v3.Bmob.getApplicationContext;

public class PrinterActivity extends AppCompatActivity {
    TextView tx44,tx46,tx48,tx55;
    Button but1,but2;
    Order order=new Order();
    List<Good> goodlist=new ArrayList<>();
    User user= BmobUser.getCurrentUser(User.class);
    String obj,path,tip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer);
        tx44=findViewById(R.id.textView44);
        tx44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = user.getAddress().size();
                final String[] address =user.getAddress().toArray(new String[size]);
                AlertDialog.Builder builder=new AlertDialog.Builder(PrinterActivity.this);
                builder.setTitle("请选择地址").setIcon(android.R.drawable.ic_dialog_map)
                        .setItems(address, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tx44.setText(address[i]);
                            }
                        }).show();
            }
        });
        tx46=findViewById(R.id.textView46);
        tx46.setText(user.getnicName());
        tx48=findViewById(R.id.textView48);
        tx48.setText(user.getMobilePhoneNumber());

        tx55=findViewById(R.id.textView55);
        tx55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(PrinterActivity.this).setTitle("详情")
                        .setMessage(path)
                        .setPositiveButton("确定", null).show();
            }
        });
        but1=findViewById(R.id.button19);
        but1.setTextColor(WHITE);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //final BmobFile bmobFile = new BmobFile(new File(path));
                final BmobFile bmobFile = new BmobFile(new File("/data/user/0/com.example.zqf.store/cache/bmob/info.docx"));
                bmobFile.uploadblock(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null) {
                            tip=bmobFile.getFileUrl();
                            order.setTips(tip);
                            //toast("上传文件成功:" + bmobFile.getFileUrl());
                        }
                        else
                            toast("上传失败"+ e.getMessage());
                    }
                    @Override
                    public void onProgress(Integer value) {
                        // 返回的上传进度（百分比）
                    }
                });


                if(tx44.getText().equals("请选择地址")||tx55.getText().equals("")){
                    new AlertDialog.Builder(PrinterActivity.this).setTitle("提示")
                            .setIcon(android.R.drawable.ic_dialog_info).setMessage("有信息未填完!")
                            .setPositiveButton("确定", null).show();
                }else{
                    order.setUser(user);
                    order.setState("配送中");
                    order.setAddress(tx44.getText().toString());
                    order.setFrom(3);
                    order.setSum(2.f);
                    order.setGoods(goodlist);
                    order.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId,BmobException e) {
                            if(e==null){
                                obj=objectId;
                                Intent intent=new Intent(PrinterActivity.this,OrderActivity.class);
                                intent.putExtra("obj",obj);
                                intent.putExtra("order",order);
                                startActivity(intent);
                                finish();
                            }else{
                                toast("创建数据失败：" + e.getMessage());
                            }
                        }
                    });
                }
            }
        });

        but2=findViewById(R.id.button20);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType(“image/*”);//选择图片
                //intent.setType(“audio/*”); //选择音频
                //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                //intent.setType(“video/*;image/*”);//同时选择视频和图片
                intent.setType("*/*");//无类型限制
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
                path = uri.getPath();
                tx55.setText(path);

                try{
                    fileCopy(path,"/data/user/0/com.example.zqf.store/cache/bmob/info.docx");//有异常，允许拒绝
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    toast(e.toString());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    toast(e.toString());
                }

                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                path = getPath(this, uri);
                tx55.setText(path);
                try{
                    fileCopy(path,"/data/user/0/com.example.zqf.store/cache/bmob/info.docx");
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    toast(e.toString());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    toast(e.toString());
                }

            } else {//4.4以下下系统调用方法
                path = getRealPathFromURI(uri);
                tx55.setText(path);
                try{
                    fileCopy(path,"/data/user/0/com.example.zqf.store/cache/bmob/info.docx");
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    toast(e.toString());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    toast(e.toString());
                }

            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(null!=cursor&&cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }

    @SuppressLint("NewApi")
     public String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean fileCopy(String oldFilePath,String newFilePath) throws IOException {
        //如果原文件不存在
        if(fileExists(oldFilePath) == false){
            return false;
        }
        //获得原文件流
        FileInputStream inputStream = new FileInputStream(new File(oldFilePath));
        byte[] data = new byte[1024];
        //输出流
        FileOutputStream outputStream =new FileOutputStream(new File(newFilePath));
        //开始处理流
        while (inputStream.read(data) != -1) {
            outputStream.write(data);
        }
        inputStream.close();
        outputStream.close();
        return true;
    }

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


    public void toast(String toast) {           //Toast便捷使用方法
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }
}
