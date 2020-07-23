package pers.xp.service;

import pers.xp.bean.UmsMember;

public interface UmsMemberService {
    public UmsMember getUmsMemberByNameAndPwd(UmsMember umsMember) throws Exception;

    public UmsMember getUmsMemberByName(String username);

    public boolean updateUserInfo(UmsMember umsMember);
}
