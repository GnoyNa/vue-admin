package com.yangk.news.login.util;

/**
 *      保存会员数据第一层
 */


public class MemberData <T> {
    private String type;
    private int id = 1;
    private T Attributes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getAttributes() {
        return Attributes;
    }

    public void setAttributes(T attributes) {
        Attributes = attributes;
    }
}
