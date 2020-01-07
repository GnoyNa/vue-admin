package com.yangk.news.login.dao;


import com.yangk.news.login.entity.Content;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 *      文章数据访问
 */
@Repository
public interface ContentDao {

    @Select("select * from content")
    public ArrayList<Content> findAllContent();

    @Select("select * from content where id=#{id}")
    public Content findOneContentById(Content content);

    @Select("insert into content(id,title,url,img,create_date,count,cid) " +
            "values(#{id},#{title},#{url},#{img},#{createDate},#{count},#{cid})")
    public Content AddContent(Content content);
}
