package com.yangk.news.login.pojo;

/**
 *      Token数据
 */
public class Token {
    private static int id = 0;
    private String token = "05211295-8536-4fb4-9cc0-63cdedbdbad8" + id++;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
