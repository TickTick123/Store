package com.example.zqf.store.Bean;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by zqf on 2018/2/14.
 */

public class User extends BmobUser{
    // 父类中已经存在的属性
    // private String objectid;
    // private String username;手机号（可用于登录）
    // private String password;
    // private String mobilePhoneNumber  手机号（可用于登录）
    // private String email;手机号（可用于登录）
    private String nicName;             //昵称
    private List<String> address;           //收货地址
    private String state; 		     // 状态
    private BmobFile picUser; 	// 头像

    public String getnicName() {return nicName;}

    public void setNicName(String nicName) { this.nicName = nicName; }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BmobFile getPicUser() { return picUser; }

    public void setPicUser(BmobFile picUser) {
        this.picUser = picUser;
    }

}
