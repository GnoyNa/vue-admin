package com.yangk.news.login.dao;


import com.yangk.news.login.entity.Channel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 *      频道数据访问
 */
@Repository
public interface ChannelDao {
    //查找所有频道
    @Select("select * from channel")
    public ArrayList<Channel> findChannels();

    //按ID查找频道
    @Select("select * from channel where id=#{id}")
    public Channel findChannelById(int id);
    //新增频道
    @Select("insert into channel(channelname) values(#{channelname})")
    public Channel AddChannel(Channel channel);
    //删除频道
    @Delete("delete from channel where id=#{id}")
    public void DeleteChannel(Channel channel);
    //修改频道
    @Update("update channel set channelname=#{channelname} where id=#{id}")
    public void UpdateChannel(Channel channel);
}
