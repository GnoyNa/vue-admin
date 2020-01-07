package com.yangk.news.login.util;

/**
 *      保存会员数据第二层
 */

public class MemberJson <T> {
    private String links="self";
    private T data;

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
