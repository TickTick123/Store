package com.example.zqf.store.Bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by zqf on 2018/2/9.
 */

public class Good extends BmobObject implements Serializable {
    private String name;  //商品名称
    private String type;  //商品类型
    private Float price;  //商品价格
    private String describe;  //商品描述
    private BmobFile PicGood;  //商品主图
    private Integer number;       //数量
    private String masterneme;  //商品所属用户姓名(个人商品发布时使用)
    private String masterphone;  //商品所属用户手机号(个人商品发布时使用)
    private String masterQQ;  //商品所属用户QQ(个人商品发布时使用)
    private String masterClass; //商品所属用户学院班级(个人商品发布时使用)

    public String getMasterClass() {
        return masterClass;
    }

    public void setMasterClass(String masterClass) {
        this.masterClass = masterClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public BmobFile getPicGood() {
        return PicGood;
    }

    public void setPicGood(BmobFile picGood) {
        PicGood = picGood;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getMasterneme() {
        return masterneme;
    }

    public void setMasterneme(String masterneme) {
        this.masterneme = masterneme;
    }

    public String getMasterphone() {
        return masterphone;
    }

    public void setMasterphone(String masterphone) {
        this.masterphone = masterphone;
    }

    public String getMasterQQ() {
        return masterQQ;
    }

    public void setMasterQQ(String masterQQ) {
        this.masterQQ = masterQQ;
    }
}

//User类：抽象出用户的属性和操作
//        属性：objectid：bmob自定义的ID
//        username：用户名（可用于登录）
//        password：密码
//        mobilePhoneNumber：手机号（可用于登录）
//        email：邮箱 （可用于登录）
//        address:收货地址
//        state：状态（可用，停用）
//        picuser：头像
//        方法：构造方法，各属性set、get方法，toString()方法。
