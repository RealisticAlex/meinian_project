package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mcs.meinian.mapper.OrderInfoMapper;
import com.mcs.meinian.pojo.Member;
import com.mcs.meinian.service.MemberService;
import com.mcs.meinian.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Alex
 * @Date 2021/11/3 20:54
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public Member findMemberByTelephone(String telephone) throws Exception {
        return orderInfoMapper.findMemberByTelephone(telephone);
    }

    @Override
    public void addMember(Member member) throws Exception {
        if (member.getPassword() != null) {
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        orderInfoMapper.addMember(member);
    }
}
