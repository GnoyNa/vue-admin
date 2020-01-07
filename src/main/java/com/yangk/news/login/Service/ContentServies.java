package com.yangk.news.login.Service;


import com.yangk.news.login.entity.Content;

import java.util.ArrayList;

public interface ContentServies {
    //获取文章列表
    public ArrayList<Content> findAllContent();
    //获取文章详情
    public Content findOneContentById(Content content);
    //新建文章
    public Content AddContent(Content content);
}
