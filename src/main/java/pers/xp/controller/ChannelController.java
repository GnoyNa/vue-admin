package pers.xp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xp.bean.Channel;
import pers.xp.bean.UmsMember;
import pers.xp.dto.Result;
import pers.xp.service.ChannelService;
import pers.xp.util.FlagUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static java.lang.Math.max;

@CrossOrigin(origins = {"*"}, allowCredentials = "true")
@RestController
public class ChannelController {

    @Autowired
    ChannelService channelService;

    //GET /api/channel?offset=1&limit=5 HTTP/1.1
    @GetMapping("/api/channel")
    public Result<List<Channel>> getChannels(Integer offset, Integer limit){
        if(offset != null && limit != null){
            return new Result<List<Channel>>(20000,"成功",channelService.getChannels(max(0,offset - 1),max(1,limit)));
        }
        return new Result<List<Channel>>(50000,"失败",null);
    }

    //GET /api/channel/34
    @GetMapping("/api/channel/{id}")
    public Result<Channel> getChannelById(@PathVariable("id") Integer id){
        if(id != null){
            return new Result<Channel>(20000,"成功",channelService.getChannelById(id));
        }
        return new Result<Channel>(50000,"失败",null);
    }

    ///api/channel
    @PostMapping("/api/channel")
    public  Result<Channel> createChannelByName(@RequestBody Channel channel, HttpServletRequest request){
//        HttpSession session = FlagUtil.session;
        if(channel.getChannelname() != null){
            boolean id = channelService.createChannelByName(channel);
            return new Result<Channel>(id? 20000 : 50000,id ? "成功" : "失败" ,channel);
        }
        return  new Result<Channel>(50000,"失败" ,null);
    }
    //DELETE /api/channel/34
    @DeleteMapping("/api/channel/{id}")
    public  Result<Channel> deleteChannelById(@PathVariable("id") Integer id, HttpServletRequest request){
        if(id != null){
            id = channelService.deleteChannelById(id);
            return new Result<Channel>(id != -1? 20000 : 50000,id != -1 ? "成功" : "失败" ,null);
        }
        return  new Result<Channel>(50000,"失败" ,null);
    }
    //PUT /api/channel
    @PutMapping("/api/channel")
    public  Result<Channel> putChannelById(@RequestBody Channel channel, HttpServletRequest request){
        if(channel != null && channel.getId() != null){
            boolean id = channelService.putChannelById(channel);
            return new Result<Channel>(id? 20000 : 50000,id ? "成功" : "失败" ,channel);
        }
        return  new Result<Channel>(50000,"失败" ,null);
    }
}
