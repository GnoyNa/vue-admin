package com.yangk.news.login.Service.Impl;

import com.yangk.news.login.Service.ChannelService;
import com.yangk.news.login.dao.ChannelDao;
import com.yangk.news.login.entity.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("ChannelService")
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private ChannelDao channelDao;

    public ArrayList<Channel> findChannels(){
        return channelDao.findChannels();
    }

    public  Channel findChannelById(int id){
        return channelDao.findChannelById(id);
    }


    public Channel AddChannel(Channel channel) {
        return channelDao.AddChannel(channel);
    }


    public void DeleteChannel(Channel channel) {
        channelDao.DeleteChannel(channel);
    }


    public void UpdateChannel(Channel channel) {
        channelDao.UpdateChannel(channel);
    }

}
