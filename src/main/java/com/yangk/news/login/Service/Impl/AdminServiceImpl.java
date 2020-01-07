package com.yangk.news.login.Service.Impl;

import com.yangk.news.login.Service.AdminService;
import com.yangk.news.login.dao.UmsAdminDao;
import com.yangk.news.login.entity.UmsAdmin;
import com.yangk.news.login.entity.UmsMember;
import com.yangk.news.login.pojo.AdminPojo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private UmsAdminDao umsdao;

    public UmsAdmin queryAdminByName(AdminPojo adminPojo){
        return umsdao.queryAdminByName(adminPojo);
    }


    public UmsMember queryMemberByName(AdminPojo adminPojo) {
        return umsdao.queryMemberByName(adminPojo);
    }

}
