package com.example.zqf.store.Bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by dell on 2018/3/25.
 */

public class New extends BmobObject implements Serializable {
    private String title;//标题
    private String content; //内容

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
