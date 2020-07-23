package pers.xp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.xp.bean.Content;
import pers.xp.bean.ContentExample;
import pers.xp.bean.Star;
import pers.xp.bean.StarExample;
import pers.xp.dao.ContentMapper;
import pers.xp.dao.StarMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class StarServiceImpl implements StarService {

    @Autowired
    StarMapper starMapper;

    @Autowired
    ContentMapper contentMapper;

    public Star getStarById(Integer memberId, Integer news) {
        StarExample starExample = new StarExample();
        StarExample.Criteria criteria = starExample.createCriteria();
        criteria.andMemberIdEqualTo(memberId.longValue());
        criteria.andNewsEqualTo(news.longValue());
        List<Star> lists = starMapper.selectByExample(starExample);
        if (lists.isEmpty()) {
            return null;
        }
        return lists.get(0);
    }

    public void insertStar(Integer memberId, Integer news) {
        starMapper.insertSelective(new Star(memberId.longValue(),news.longValue()));
    }

    public List<Content> getContentsByUid(Integer uid) {
        StarExample starExample = new StarExample();
        StarExample.Criteria criteria = starExample.createCriteria();
        criteria.andMemberIdEqualTo(uid.longValue());
        List<Star> lists = starMapper.selectByExample(starExample);
        List<Content> contentList = new ArrayList<Content>();

        for (Star i:lists) {
            Content content = contentMapper.selectByPrimaryKey(i.getNews().intValue());
            if (content != null)
                contentList.add(content);
        }
        return contentList;
    }
}
