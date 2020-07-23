package pers.xp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.xp.bean.UmsMember;
import pers.xp.bean.UmsMemberExample;
import pers.xp.dao.UmsMemberMapper;
import pers.xp.util.EncodeUtil;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UmsMemberServiceImpl implements  UmsMemberService {

    @Autowired
    UmsMemberMapper umsMemberMapper;

    public UmsMember getUmsMemberByNameAndPwd(UmsMember umsMember) throws Exception {
        UmsMemberExample umsMemberExample = new UmsMemberExample();
        UmsMemberExample.Criteria criteria = umsMemberExample.createCriteria();
        criteria.andUsernameEqualTo(umsMember.getUsername()).andPasswordEqualTo(umsMember.getPassword());
        List<UmsMember> umsMembers = umsMemberMapper.selectByExample(umsMemberExample);
        return umsMembers.size() > 0 ? umsMembers.get(0) : null;
    }

    public UmsMember getUmsMemberByName(String username) {
        UmsMemberExample umsMemberExample = new UmsMemberExample();
        UmsMemberExample.Criteria criteria = umsMemberExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<UmsMember> umsMembers = umsMemberMapper.selectByExample(umsMemberExample);
        return umsMembers.size() > 0 ? umsMembers.get(0) : null;
    }

    public boolean updateUserInfo(UmsMember umsMember) {
        UmsMemberExample umsMemberExample = new UmsMemberExample();
        UmsMemberExample.Criteria criteria = umsMemberExample.createCriteria();
        criteria.andUsernameEqualTo(umsMember.getUsername());
        List<UmsMember> umsMembers = umsMemberMapper.selectByExample(umsMemberExample);
        if(!umsMembers.isEmpty()) {
            umsMember.setId(umsMembers.get(0).getId());
            umsMemberMapper.updateByPrimaryKeySelective(umsMember);
        }
        return !umsMembers.isEmpty();
    }
}
