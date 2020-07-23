package pers.xp.controller;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.xp.bean.UmsMember;
import pers.xp.dto.*;
import pers.xp.service.UmsMemberService;
import pers.xp.util.EncodeUtil;
import pers.xp.util.FlagUtil;

import javax.rmi.CORBA.Util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;


@CrossOrigin(origins = {"*"}, allowCredentials = "true")
@RestController
public class UmsMemberController {

    @Autowired
    UmsMemberService umsMemberService;

//    @RequestMapping(value = "/*",method = RequestMethod.OPTIONS)
//    public ResponseEntity handleOptions(){
//        return (ResponseEntity) ResponseEntity.noContent();
//    }

    //POST /api/user/login
    @PostMapping("/api/user/login")
    public Result<Map<String,String>> login(String auth_type, @RequestBody UmsMember umsMember,HttpServletRequest request, HttpServletResponse response){
//        UmsMember umsMember = new UmsMember(username,password);
        HttpSession session = FlagUtil.session;
        try {
            umsMember.setPassword(EncodeUtil.sha1Encode(umsMember.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(auth_type != null){
            UmsMember ret;
            try {
                ret = umsMemberService.getUmsMemberByNameAndPwd(umsMember);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result<Map<String,String>>(50001,"失败",null);
            }
            String uuid = UUID.randomUUID().toString();
            Map<String,String> tmp = new HashMap<String, String>();
            if(ret != null){
                ret.setRole(auth_type);
                System.out.println(ret);
                session.setAttribute(uuid,ret);
                FlagUtil.token = uuid;
                Cookie cookie = new Cookie("token",uuid);
//                Cookie sessionId = new Cookie("JSESSIONID",session.getId());
//                sessionId.setMaxAge(9*60*60);
                cookie.setMaxAge(9*60*60);
                response.addCookie(cookie);
                tmp.put("token",uuid);
            }
            return new Result<Map<String,String>>(ret != null? 20000 : 50000,ret != null ? "登录成功" : "失败",ret != null?tmp:null);
        }
        return new Result<Map<String,String>>(50000,"失败",null);
    }

    //GET /api/user/info
    @GetMapping("/api/user/info")
    public Result<Map<String,Object>> info(String token,HttpServletRequest request){
  System.out.println(token);
//        System.out.println(request.getSession().getId());
        HttpSession session = FlagUtil.session;
       if(token != null){

            UmsMember umsMember = (UmsMember) session.getAttribute(token);
            System.out.println(umsMember);
           Map<String,Object> ret = new HashMap<String, Object>();
           ret.put("name",umsMember.getUsername());
           ret.put("avatar",umsMember.getAvatar());
           Vector<String> vec = new Vector<String>();
           vec.add(umsMember.getRole());
           ret.put("roles",vec);
            if(umsMember != null) return new Result<Map<String,Object>>(20000,"获取用户信息",ret);
       }
        return new Result<Map<String,Object>>(50000,"失败",null);
    }
    //api/member/username/{username}?access_token=
    @GetMapping("/api/member/username/{username}")
    public UserInfo userInfo(String access_token,@PathVariable("username") String username){
        UserInfo userInfo = new UserInfo();
        if(access_token != null){
            UmsMember umsMember = umsMemberService.getUmsMemberByName(username);
            Map<String,Object> ret = new HashMap<String, Object>();
            umsMember.setIcon(umsMember.getAvatar());
            ret.put("attributes",umsMember);
            if(umsMember != null) {
                Map<String,String> tmp = new HashMap<String, String>();
                tmp.put("self","self");
                userInfo.setLinks(tmp);
                Data data = new Data();
                data.setAttributes(umsMember);
                data.setId(1);
                data.setType("UmsMember");
                userInfo.setData(data);
            }
        }
        return userInfo;
    }

    //api/profile/info/{username}
    @GetMapping("/api/profile/info/{username}")
    public Result<Map<String,Object>> userInfoMsg(@PathVariable("username") String username){
        if(username != null){
            UmsMember umsMember = umsMemberService.getUmsMemberByName(username);
            Map<String,Object> ret = new HashMap<String, Object>();
            ret.put("id",umsMember.getId());
            ret.put("username",umsMember.getUsername());
            ret.put("icon",umsMember.getIcon());
            ret.put("email",umsMember.getUsername()+"@qq.com");
            ret.put("nickName",umsMember.getNickname());
            ret.put("note",umsMember.getNickname());
            ret.put("createTime",umsMember.getCreateTime());
            ret.put("loginTime",umsMember.getLoginTime());
            ret.put("status",umsMember.getStatus());
            Vector<String> vec = new Vector<String>();
            vec.add(umsMember.getRole());
            ret.put("roles",vec);
            if(umsMember != null) return new Result<Map<String,Object>>(20000,"获取个人信息",ret);
        }
        return new Result<Map<String,Object>>(50000,"失败",null);
    }

    //api/profile/update
    @PostMapping("/api/profile/update")
    public Result<Object> updateUserInfo(@RequestBody UserOther userOther){
        UmsMember umsMember = new UmsMember();
        System.out.println(userOther);
        if(userOther.getId() != null){
            umsMember.setId(userOther.getId());
            umsMember.setIcon(userOther.getIcon());
            if(userOther.getRoles() != null) umsMember.setRole(userOther.getRoles().get(0));
            umsMember.setCreateTime(userOther.getCreateTime());
            umsMember.setLoginTime(userOther.getLoginTime());
            umsMember.setId(userOther.getId());
            umsMember.setNickname(userOther.getNickName());
            umsMember.setUsername(userOther.getUsername());
            umsMember.setStatus(userOther.getStatus());
            umsMember.setAvatar(userOther.getIcon());
            boolean id = umsMemberService.updateUserInfo(umsMember);
            if(id) return new Result<Object>(20000,"更新个人信息成功",null);
        }
        return new Result<Object>(50000,"失败",null);
    }

    //api/profile/modify/password
    @PostMapping("/api/profile/modify/password")
    public Result<Object> updateUserPass(@RequestBody PwdBody pwdBody, HttpServletRequest request) throws Exception {
        /**
         * "username": "admin",
         *  "oldPassword": "123456",
         *  "newPassword": "123456",
         *  "confirmPassword": "123456"
         */
 //       System.out.println(pwdBody);
        if(pwdBody.getConfirmPassword() != null && pwdBody.getNewPassword() != null && pwdBody.getConfirmPassword().equals(pwdBody.getNewPassword())) {
            UmsMember umsMember = new UmsMember(pwdBody.getUsername(),pwdBody.getOldPassword());
            if (pwdBody.getUsername() != null
                    && pwdBody.getUsername().equals(umsMember.getUsername())
                    && pwdBody.getOldPassword().equals(umsMember.getPassword())) {
                umsMember.setPassword(pwdBody.getNewPassword());
                boolean id = umsMemberService.updateUserInfo(umsMember);
                if (id) return new Result<Object>(20000, "修改密码成功", null);
            }
        }
        return new Result<Object>(50000,"失败",null);
    }

    @GetMapping("/api/user/login")
    public Result<Map<String,String>> mLogin(String auth_type,String username, String password, HttpSession session, HttpServletResponse response){
        UmsMember umsMember = null;
        try {
            umsMember = new UmsMember(username, EncodeUtil.sha1Encode(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(auth_type != null){
            UmsMember ret;
            try {
                ret = umsMemberService.getUmsMemberByNameAndPwd(umsMember);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result<Map<String,String>>(50001,"失败",null);
            }
            String uuid = UUID.randomUUID().toString();
            Map<String,String> tmp = new HashMap<String, String>();
            if(ret != null){
                ret.setRole(auth_type);
                session.setAttribute(uuid,ret);
                Cookie cookie = new Cookie("token",uuid);
                cookie.setMaxAge(10*60*60);
                response.addCookie(cookie);
                tmp.put("token",uuid);
            }
            tmp.put("userID",ret.getId().toString());
            return new Result<Map<String,String>>(ret != null? 20000 : 50000,ret != null ? "登录成功" : "失败",ret != null?tmp:null);
        }
        return new Result<Map<String,String>>(50000,"失败",null);
    }
}
