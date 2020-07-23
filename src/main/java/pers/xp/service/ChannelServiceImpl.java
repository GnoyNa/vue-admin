package pers.xp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.xp.bean.Channel;
import pers.xp.bean.ChannelExample;
import pers.xp.dao.ChannelMapper;

import java.util.List;

import static java.lang.Math.max;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ChannelServiceImpl implements  ChannelService {

    @Autowired
    ChannelMapper channelMapper;

    public List<Channel> getChannels(Integer offset, Integer limit) {
        ChannelExample channelExample = new ChannelExample();
        channelExample.setOffset(max(0,offset));
        channelExample.setLimit(max(1,limit));
        return channelMapper.selectByExample(channelExample);
    }

    public Channel getChannelById(Integer id) {
        return  channelMapper.selectByPrimaryKey(id);
    }

    public int deleteChannelById(Integer id) {
        Channel channel = channelMapper.selectByPrimaryKey(id);
        if(channel != null) channelMapper.deleteByPrimaryKey(id);
        return channel != null ? 1 : -1;
    }

    public boolean putChannelById(Channel channel) {
        Channel channeltmp = channelMapper.selectByPrimaryKey(channel.getId());
        if(channeltmp != null) {
            channelMapper.updateByPrimaryKey(channel);
        }
        return channeltmp != null;
    }

    @Override
    public boolean createChannelByName(Channel channel) {
        ChannelExample channelExample = new ChannelExample();
        channelExample.createCriteria().andChannelnameEqualTo(channel.getChannelname());
        List<Channel> channels = channelMapper.selectByExample(channelExample);
        if(channels.size() == 0) channelMapper.insertSelective(channel);
        return channels.size() == 0;
    }
}
