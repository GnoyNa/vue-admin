package pers.xp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xp.bean.Content;
import pers.xp.dto.Result;
import pers.xp.service.ContentService;
import pers.xp.util.PicUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"}, allowCredentials = "true")
@RestController
public class ContentController {
    @Autowired
    ContentService contentService;

//    GET /api/content?oﬀset=2&limit=5?cid=7
    @GetMapping("/api/content")
    public Result<List<Content>> getContents(Integer offset, Integer limit, Integer cid) {
        Result<List<Content>> r = new Result<List<Content>>();

        if (offset == null || limit == null) {
            r.setCode(50000);
            r.setMessage("失败");
            return r;
        }
        r.setCode(20000);
        r.setMessage("成功");
        r.setData(contentService.getContentsByCidLimitPage(offset,limit,cid));
        return r;
    }

    @PutMapping("/api/content")
    public Result<Content> updateContent(@RequestBody Content content) {
        Result<Content> r = new Result<Content>();

        if (content == null) {
            r.setCode(50000);
            r.setMessage("失败");
            return r;
        }
        r.setCode(20000);
        r.setMessage("成功");
        contentService.updateContent(content);
        r.setData(content);
        return r;
    }

//    GET /api/content/165
    @GetMapping("/api/content/{id}")
    public Result<Content> getContentById(@PathVariable("id") Integer id) {
        Result<Content> r = new Result<Content>();
//        System.out.println(id);
        if (id == null) {
            r.setCode(50000);
            r.setMessage("失败");
            return r;
        }
        r.setCode(20000);
        r.setMessage("成功");
        r.setData(contentService.getContentById(id));
        return r;
    }

//      POST  http://39.105.84.76:8888/api/detail
    @PostMapping("/api/article/create")
    public Result<Content> addNews(@RequestBody Content content, HttpServletRequest request) {
        Result<Content> r = new Result<Content>();

        if (content == null) {
            r.setCode(50000);
            r.setMessage("失败");
            return r;
        }
        r.setCode(20000);
        r.setMessage("成功");
        r.setData(content);
        content.setCid(7);
        content.setImg("http://"+request.getRemoteHost()+":"+request.getServerPort()+PicUtil.getPicUrl(content.getImg()));
        content.setUrl("https://k.sina.com.cn/article_7018132577_p1a250346100100p13w.html?from=mil&cre=tianyi&mod=wmil&loc=1&r=0&rfunc=59&tj=cxvertical_wap_wmil&tr=12");
        contentService.insertContent(content);
        return r;
    }

//    DELETE  http://39.105.84.76:8888/api/detail/{id}
    @DeleteMapping("/api/detail/{id}")
    public Result<Content> deleteNews(@PathVariable("id") Integer id) {
        Result<Content> r = new Result<Content>();

        if (id == null) {
            r.setCode(50000);
            r.setMessage("失败");
        }
        r.setCode(20000);
        r.setMessage("成功");
        contentService.deleteContentById(id);
        return r;
    }

}
