package com.yangk.news.login.web;


import com.yangk.news.login.Service.AdminService;
import com.yangk.news.login.entity.UmsAdmin;
import com.yangk.news.login.entity.UmsMember;
import com.yangk.news.login.pojo.*;

import com.yangk.news.login.util.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *      登录Web层
 */

@Controller
public class UmsAdminWeb {
    private static Map<Token,UmsAdmin> map =new HashMap<Token,UmsAdmin>();
    private static Map<Token,UmsMember> map1 =new HashMap<Token,UmsMember>();
    @Resource
    private AdminService service;

    //用户登录
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/user/login")
    public JsonResponse<Token> umsLogin(@RequestBody AdminPojo adminPojo) {
        UmsAdmin umsAdmin = service.queryAdminByName(adminPojo);
        UmsMember umsMember=service.queryMemberByName(adminPojo);
        if(umsAdmin!=null){
            JsonResponse<Token> jsonData =new JsonResponse<Token>();
            jsonData.setMessage("登录成功");
            for(Map.Entry<Token,UmsAdmin> entry:map.entrySet()) {
                if (umsAdmin.equals(entry.getValue())) {
                    jsonData.setData(entry.getKey());
                    return jsonData;
                }
            }
            Token token =new Token();
            jsonData.setData(token);
            map.put(token,umsAdmin);
            return jsonData;
        }
        else if(umsMember!=null){
            JsonResponse<Token> jsonData =new JsonResponse<Token>();
            jsonData.setMessage("登录成功");
            for(Map.Entry<Token,UmsMember> entry:map1.entrySet()) {
                if (umsMember.equals(entry.getValue())) {
                    jsonData.setData(entry.getKey());
                    return jsonData;
                }
            }
            Token token =new Token();
            jsonData.setData(token);
            map1.put(token,umsMember);
            return jsonData;
        }
        return null;
    }

    //管理员权限
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/user/info")
    public JsonResponse<UmsInfo> umsInfo(@RequestHeader("authorization") String account) {
        if(account != null){
            for (Token token:map.keySet()) {
                if (account.contains(token.getToken())) {
                    UmsAdmin admin = map.get(token);
                    JsonResponse<UmsInfo> json = new JsonResponse<UmsInfo>();
                    json.setMessage("获取用户信息");
                    UmsInfo info = new UmsInfo();
                    info.setName(admin.getUsername());
                    info.setAvatar(admin.getIcon());
                    info.setNickName(admin.getNick_name());
                    info.setRoles(new String[]{"admin"});
                    json.setData(info);
                    return json;
                }
            }
        }
        return null;
    }

}
