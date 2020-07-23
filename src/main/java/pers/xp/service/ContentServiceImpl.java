package pers.xp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.xp.bean.ChannelExample;
import pers.xp.bean.Content;
import pers.xp.bean.ContentExample;
import pers.xp.dao.ContentMapper;

import javax.security.auth.login.Configuration;
import java.util.List;

import static java.lang.Math.max;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ContentServiceImpl implements ContentService{

    @Autowired
    ContentMapper contentMapper;

    @Autowired
    public List<Content> getContents() {
        return contentMapper.selectByExample(new ContentExample());
    }

    public Content getContentById(Integer id) {
        return contentMapper.selectByPrimaryKey(id);
    }
    public List<Content> getContentsByCidLimitPage(Integer offset, Integer limit, Integer cid) {
        ContentExample contentExample = new ContentExample();
        contentExample.setOffset(max(0,offset-1));
        contentExample.setLimit(max(1,limit));
        ContentExample.Criteria criteria = contentExample.createCriteria();
        if (cid != null) criteria.andCidEqualTo(cid);
        return contentMapper.selectByExample(contentExample);
    }

    public void insertContent(Content content) {
        contentMapper.insertSelective(content);
    }

    public void deleteContentById(Integer id) {
        contentMapper.deleteByPrimaryKey(id);
    }

    public void updateContent(Content content) {
        contentMapper.updateByPrimaryKeySelective(content);
    }

}
