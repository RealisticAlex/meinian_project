package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mcs.meinian.mapper.MemberMapper;
import com.mcs.meinian.mapper.OrderInfoMapper;
import com.mcs.meinian.pojo.Member;
import com.mcs.meinian.service.MemberService;
import com.mcs.meinian.utils.DateUtils;
import com.mcs.meinian.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Alex
 * @Date 2021/11/3 20:54
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private MemberMapper memberMapper;

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

    /**
     * 根据日期获取所有会员数量
     * @param months
     * @return
     */
    @Override
    public List<Integer> getMemberCountByMonths(ArrayList<String> months) throws Exception {
        ArrayList<Integer> memberCount = new ArrayList<>();

        for (String month : months) {
            //获取当月最后一天
            String regTime = DateUtils.getLastDayOfMonth(month);
            Integer count = memberMapper.getMemberCountByMonths(regTime);
            memberCount.add(count);
        }
        return memberCount;
    }
}
