package com.yangk.news.login.Service.Impl;

import com.yangk.news.login.Service.UserInfoService;
import com.yangk.news.login.dao.UserInfoDao;
import com.yangk.news.login.entity.UmsAdmin;
import com.yangk.news.login.entity.UmsMember;
import com.yangk.news.login.pojo.IconInfo;
import com.yangk.news.login.pojo.PasswordPojo;
import com.yangk.news.login.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserInfoServices")
public class UserInfoServiceImpl implements UserInfoService {
        @Resource
        private UserInfoDao userInfoDao;


        public UmsAdmin FindAdminByName(String username) {
            return userInfoDao.findAdminByName(username);
        }


        public void UpdateAdmin(UserInfo userInfo) {
            userInfoDao.UpdateAdmin(userInfo);
        }


        public void UpdatePassword(PasswordPojo passwordPojo) {
            userInfoDao.UpdatePassword(passwordPojo);
        }


        public void UpdateIcon(IconInfo iconInfo) {
            userInfoDao.UpdateIcon(iconInfo);
        }


        public UmsMember FindMemberByName(String username) {
            return userInfoDao.findMemberByName(username);
        }
}
