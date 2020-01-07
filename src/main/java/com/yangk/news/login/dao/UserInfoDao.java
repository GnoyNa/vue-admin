package com.yangk.news.login.dao;

import com.yangk.news.login.entity.UmsAdmin;
import com.yangk.news.login.entity.UmsMember;
import com.yangk.news.login.pojo.IconInfo;
import com.yangk.news.login.pojo.PasswordPojo;
import com.yangk.news.login.pojo.UserInfo;

/**
 *      用户信息数据访问
 */
public interface UserInfoDao {

    public UmsAdmin findAdminByName(String username);

    public void UpdateAdmin(UserInfo userInfo);

    public void UpdatePassword(PasswordPojo passwordPojo);

    public void UpdateIcon(IconInfo iconInfo);

    public UmsMember findMemberByName(String username);
}
