package pers.xp.service;

import pers.xp.bean.Content;

import java.util.List;

public interface ContentService {
    public List<Content> getContents();
    public Content getContentById(Integer id);
    public List<Content> getContentsByCidLimitPage(Integer offset,Integer limit, Integer cid);
    public void insertContent(Content content);
    public void deleteContentById(Integer id);
    public void updateContent(Content content);
}
