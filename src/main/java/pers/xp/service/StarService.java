package pers.xp.service;

import pers.xp.bean.Content;
import pers.xp.bean.Star;

import java.util.List;

public interface StarService {
    public Star getStarById(Integer memberId, Integer news);
//    public Star getStarsByUid(Integer uid);
    public void insertStar(Integer memberId,Integer news);
    public List<Content> getContentsByUid(Integer uid);
}
