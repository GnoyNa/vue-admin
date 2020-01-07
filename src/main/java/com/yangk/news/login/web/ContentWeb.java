package com.yangk.news.login.web;

import com.yangk.news.login.Service.ContentServies;
import com.yangk.news.login.entity.Content;
import com.yangk.news.login.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 *      新闻Web层
 */

@Controller
public class ContentWeb {
    @Autowired
    private ContentServies contentServies;
    //新闻列表
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public JsonResponse<ArrayList<Content>> findAllContent(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        JsonResponse<ArrayList<Content>> jsonResponse = new JsonResponse<ArrayList<Content>>();
        jsonResponse.setMessage("成功");
        ArrayList<Content> contents = contentServies.findAllContent();
        ArrayList<Content> contents1=new ArrayList<>();
        int st=(offset-1)*20;
        contents1.addAll(contents.subList(st,st+limit));
        jsonResponse.setData(contents1);
        return jsonResponse;
    }
    //新闻详情
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    public JsonResponse<Content> findOneContentById(@PathVariable int id) {
        JsonResponse<Content> jsonResponse = new JsonResponse<>();
        jsonResponse.setMessage("成功");
        Content content = new Content();
        content.setId(id);
        jsonResponse.setData(contentServies.findOneContentById(content));
        return jsonResponse;
    }
    //添加新闻
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/article/create", method = RequestMethod.POST)
    public JsonResponse<Content> AddContent(@RequestBody Content content) {
        JsonResponse<Content> jsonResponse = new JsonResponse<>();
        contentServies.AddContent(content);
        jsonResponse.setMessage("成功");
        jsonResponse.setData(content);
        return jsonResponse;
    }

}
