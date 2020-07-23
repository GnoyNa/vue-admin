package pers.xp.service;

import pers.xp.bean.Channel;

import java.util.List;

public interface ChannelService {

    public List<Channel> getChannels(Integer offset, Integer limit);

    public Channel getChannelById(Integer id);

    public int deleteChannelById(Integer id);

    public boolean putChannelById(Channel channel);

    public boolean createChannelByName(Channel channel);
}
