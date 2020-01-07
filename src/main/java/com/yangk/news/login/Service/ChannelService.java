package com.yangk.news.login.Service;

import com.yangk.news.login.entity.Channel;

import java.util.ArrayList;

public interface ChannelService {
        //获取频道列表
        public ArrayList<Channel> findChannels();
        //获取频道详情
        public Channel findChannelById(int id);
        //新增频道
        public Channel AddChannel(Channel channel);
        //删除频道
        public void DeleteChannel(Channel channel);
        //修改频道信息
        public void UpdateChannel(Channel channel);
}
