package com.yangk.news.login.web;

import com.yangk.news.login.Service.UserInfoService;
import com.yangk.news.login.entity.UmsAdmin;
import com.yangk.news.login.entity.UmsMember;
import com.yangk.news.login.pojo.*;
import com.yangk.news.login.util.JsonResponse;
import com.yangk.news.login.util.MemberData;
import com.yangk.news.login.util.MemberJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.xml.registry.infomodel.User;


/**
 *      用户Web层
 */

@Controller
public class UserInfoWeb {
    @Resource
    private UserInfoService userInfoService;

    //获取详情
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/profile/info/{username}",method = RequestMethod.GET)
    public JsonResponse<UserInfo> getUserInfo(@PathVariable String username){
        JsonResponse<UserInfo> jsonResponse=new JsonResponse<>();
        UmsAdmin umsAdmin=userInfoService.FindAdminByName(username);
        UserInfo userInfo=new UserInfo();
        userInfo.setCreate_time(umsAdmin.getCreate_time());
        userInfo.setLogin_time(umsAdmin.getLogin_time());
        userInfo.setEmail(umsAdmin.getEmail());
        userInfo.setIcon(umsAdmin.getIcon());
        userInfo.setId(umsAdmin.getId());
        userInfo.setNick_name(umsAdmin.getNick_name());
        userInfo.setStatus(umsAdmin.getStatus());
        userInfo.setNote(umsAdmin.getNote());
        userInfo.setUsername(umsAdmin.getUsername());
        userInfo.setRoles(new String[] {"admin"});
        jsonResponse.setMessage("获取个人信息");
        jsonResponse.setData(userInfo);
        return jsonResponse;
    }
    //修改信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/profile/update",method = RequestMethod.POST)
    public JsonResponse updateAdmin(@RequestBody UserInfo userInfo){
        userInfoService.UpdateAdmin(userInfo);
        JsonResponse jsonResponse=new JsonResponse();
        jsonResponse.setMessage("更新个人信息成功");
        return jsonResponse;
    }
    //修改密码
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/profile/modify/password",method = RequestMethod.POST)
    public JsonResponse updatePassword(@RequestBody PasswordPojo passwordPojo){
        userInfoService.UpdatePassword(passwordPojo);
        JsonResponse jsonResponse=new JsonResponse();
        jsonResponse.setMessage("修改密码成功");
        return jsonResponse;
    }
    //头像上传
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public JsonResponse uploadIcon(){
        ImgInfo imgInfo = new ImgInfo();
        imgInfo.setPath("http://macro-oss.oss-cnshenzhen.aliyuncs.com/mall/images/" +
                "20190129/170157_yIl3_1767531.jpg");
        JsonResponse<ImgInfo> jsonResponse=new JsonResponse<>();
        jsonResponse.setMessage("文件上传成功");
        jsonResponse.setData(imgInfo);
        return jsonResponse;
    }
    //头像修改
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/profile/modify/icon",method = RequestMethod.POST)
    public JsonResponse updateIcon(){
        IconInfo iconInfo = new IconInfo();
        iconInfo.setUsername("admin");
        iconInfo.setPath("http://macro-oss.oss-cnshenzhen.aliyuncs.com/mall/images/" +
                "20190129/170157_yIl3_1767531.jpg");
        userInfoService.UpdateIcon(iconInfo);
        JsonResponse jsonResponse=new JsonResponse();
        jsonResponse.setMessage("头像修改成功");
        return jsonResponse;
    }
    //会员详情
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/member/username/{username}",method = RequestMethod.GET)
    public MemberJson<MemberData<Attributes>> getMemberInfo(@PathVariable String username){
        JsonResponse<UserInfo> jsonResponse=new JsonResponse<>();
        UmsMember umsMember =userInfoService.FindMemberByName(username);
        Attributes attributes = new Attributes();
        attributes.setCreate_time(umsMember.getCreate_time());
        attributes.setBirthday(umsMember.getBirthday());
        attributes.setCity(umsMember.getCity());
        attributes.setGender(umsMember.getGender());
        attributes.setGrowth(umsMember.getGrowth());
        attributes.setHistory_integration(umsMember.getHistory_integration());
        attributes.setIcon(umsMember.getIcon());
        attributes.setId(umsMember.getId());
        attributes.setIntegration(umsMember.getIntegration());
        attributes.setJob(umsMember.getJob());
        attributes.setLucky_count(umsMember.getLucky_count());
        attributes.setMember_level_id(umsMember.getMember_level_id());
        attributes.setNickname(umsMember.getNickname());
        attributes.setPassword(umsMember.getPassword());
        attributes.setPersonalized_signature(umsMember.getPersonalized_signature());
        attributes.setPhone(umsMember.getPhone());
        attributes.setSource_type(umsMember.getSource_type());
        attributes.setStatus(umsMember.getStatus());
        attributes.setUsername(umsMember.getUsername());
        MemberData<Attributes> memberData = new MemberData<>();
        memberData.setType("UmsMember");
        memberData.setId(1);
        memberData.setAttributes(attributes);
        MemberJson<MemberData<Attributes>> memberJson=new MemberJson<>();
        memberJson.setData(memberData);
        return memberJson;
    }
}

