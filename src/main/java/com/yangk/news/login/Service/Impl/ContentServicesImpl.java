package com.yangk.news.login.Service.Impl;


import com.yangk.news.login.Service.ContentServies;
import com.yangk.news.login.dao.ContentDao;

import com.yangk.news.login.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("ContentServices")
public class ContentServicesImpl implements ContentServies {
    @Autowired
    private ContentDao contentDao;



    public ArrayList<Content> findAllContent() {
        return contentDao.findAllContent();
    }


    public Content findOneContentById(Content content) {
        return contentDao.findOneContentById(content);
    }


    public Content AddContent(Content content) {
        return contentDao.AddContent(content);
    }
}
