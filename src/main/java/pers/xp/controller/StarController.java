package pers.xp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xp.bean.Content;
import pers.xp.bean.Star;
import pers.xp.dto.Result;
import pers.xp.service.StarService;

import java.util.List;

@CrossOrigin(origins = {"*"}, allowCredentials = "true")
@RestController
public class StarController {

    @Autowired
    StarService starService;

//    GET http://39.105.84.76:8888/api/star
    @GetMapping("/api/star")
    public Result<Star> addStar(Star star) {//存在问题 GET无法获得请求体
        Result<Star> r = new Result<Star>();

        if (star == null) {
            r.setCode(50000);
            r.setMessage("失败");
            return r;
        }
        r.setCode(20000);
        r.setMessage("成功");
        starService.insertStar(star.getMemberId().intValue(),star.getNews().intValue());
        r.setData(starService.getStarById(star.getMemberId().intValue(),star.getNews().intValue()));

        return r;
    }

//    GET /api/star/1
    @GetMapping("/api/star/{id}")
    public Result<List<Content>> getStarById(@PathVariable("id") Integer id) {
        Result<List<Content>> r = new Result<List<Content>>();
        System.out.println(id);
        if (id == null) {
            r.setCode(50000);
            r.setMessage("失败");
            return r;
        }
        r.setCode(20000);
        r.setMessage("成功");
        r.setData(starService.getContentsByUid(id));
        return r;
    }

    @GetMapping("/api/star/create")
    public Result<Star> addStar(Long memberId, Long news) {//存在问题 GET无法获得请求体
        Star star = new Star(memberId,news);
        Result<Star> r = new Result<Star>();

        if (star == null) {
            r.setCode(50000);
            r.setMessage("失败");
            return r;
        }
        r.setCode(20000);
        r.setMessage("成功");
        starService.insertStar(star.getMemberId().intValue(),star.getNews().intValue());
        r.setData(starService.getStarById(star.getMemberId().intValue(),star.getNews().intValue()));

        return r;
    }
}
