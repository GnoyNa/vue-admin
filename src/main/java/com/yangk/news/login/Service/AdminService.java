package com.yangk.news.login.Service;

import com.yangk.news.login.entity.UmsAdmin;
import com.yangk.news.login.entity.UmsMember;
import com.yangk.news.login.pojo.AdminPojo;

public interface AdminService {
    //获取管理员信息
    public UmsAdmin queryAdminByName(AdminPojo adminPojo);
    //获取会员信息
    public UmsMember queryMemberByName(AdminPojo adminPojo);
}
