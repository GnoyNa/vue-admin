package com.yangk.news.login.entity;

import java.io.Serializable;

/**
 *      频道
 */
public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String channelname;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", channelname='" + channelname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }
}
