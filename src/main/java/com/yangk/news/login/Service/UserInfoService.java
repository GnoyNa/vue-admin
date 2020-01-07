package com.yangk.news.login.Service;

import com.yangk.news.login.entity.UmsAdmin;
import com.yangk.news.login.entity.UmsMember;
import com.yangk.news.login.pojo.IconInfo;
import com.yangk.news.login.pojo.PasswordPojo;
import com.yangk.news.login.pojo.UserInfo;

public interface UserInfoService {
    //获取用户详情
    public UmsAdmin FindAdminByName(String username);
    //更改用户信息
    public  void UpdateAdmin(UserInfo userInfo);
    //修改用户密码
    public void UpdatePassword(PasswordPojo passwordPojo);
    //修改用户头像
    public void UpdateIcon(IconInfo iconInfo);
    //获取会员信息
    public UmsMember FindMemberByName(String username);
}
