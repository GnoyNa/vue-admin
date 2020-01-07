package com.yangk.news.login.dao;

import com.yangk.news.login.entity.UmsAdmin;
import com.yangk.news.login.entity.UmsMember;
import com.yangk.news.login.pojo.AdminPojo;

/**
 *      登录数据访问
 */
public interface UmsAdminDao {
    public UmsAdmin queryAdminByName(AdminPojo adminPojo);

    public UmsMember queryMemberByName(AdminPojo adminPojo);

}
