package com.yangk.news.login.web;

import com.yangk.news.login.Service.ChannelService;
import com.yangk.news.login.entity.Channel;
import com.yangk.news.login.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 *      频道Web层
 */

@Controller
public class ChannelWeb {
    @Autowired
    private ChannelService channelService;
    //频道管理响应
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/channel")
    public JsonResponse<ArrayList<Channel>> channel(@RequestParam("offset") int offset, @RequestParam("limit") int limit){
        JsonResponse<ArrayList<Channel>> json=new JsonResponse<>();
        System.out.println("limit"+limit);
        System.out.println("offset"+offset);
        ArrayList<Channel> channels=new ArrayList<>();
        channels=channelService.findChannels();
        json.setMessage("成功");
        json.setData(channels);
        System.out.println("jason:"+json);
        return json;
    }
    //频道详情
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="/channel/{id}",method = RequestMethod.OPTIONS)
    public JsonResponse<Channel> changeChannle(@PathVariable int id)
    {
        JsonResponse<Channel> json=new JsonResponse<>();
        json.setMessage("成功");
        Channel channel=new Channel();
        channel=channelService.findChannelById(id);
        if(channel!=null)
        {
            json.setData(channel);
            return json;
        }
        return json;
    }
    //新增频道
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/channel" ,method = RequestMethod.POST)
    public JsonResponse<Channel> Addchannel(@RequestBody Channel channel){
        JsonResponse<Channel> json=new JsonResponse<>();
        channelService.AddChannel(channel);
        json.setMessage("成功");
        json.setData(channel);
        return json;
    }
    //删除频道
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/channel/{id}" ,method = RequestMethod.DELETE)
    public JsonResponse Deletechannel(@PathVariable int id){
        if(id!=0){
            JsonResponse json=new JsonResponse();
            json.setMessage("成功");
            Channel channel = new Channel();
            channel.setId(id);
            channelService.DeleteChannel(channel);
            return json;
        }
        return null;
    }
    //修改频道
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/channel" ,method = RequestMethod.PUT)
    public JsonResponse<Channel> Updatechannel(@RequestBody Channel channel){
            JsonResponse<Channel> json=new JsonResponse<>();
            channelService.UpdateChannel(channel);
            json.setMessage("成功");
            json.setData(channel);
            return json;

    }
}
