package com.example.zqf.store.Bean;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by zqf on 2018/2/9.
 */

public class Order extends BmobObject implements Serializable {
    private List<Good> goods;   //商品集合
    private User user;   //购买者
    private String address;   //收货地址
    private String state;   //订单状态（配送中，待评价，已完成）
    private String tips;   //备注
    private String evaluate;   //评价
    private Integer from;   //0:超市，1：数码，2：快递，3：打印，4：租赁
    private Float sum;//总价

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }
}
