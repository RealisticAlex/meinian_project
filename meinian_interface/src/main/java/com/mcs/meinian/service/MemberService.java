package com.mcs.meinian.service;

import com.mcs.meinian.pojo.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Alex
 * @Date 2021/11/3 20:53
 */
public interface MemberService {

    /**
     * 通过电话查询会员信息
     * @param telephone
     * @return
     */
    Member findMemberByTelephone(String telephone) throws Exception;

    /**
     * 添加会员
     * @throws Exception
     */
    void addMember(Member member) throws Exception;

    /**
     * 根据日期获取所有会员数量
     * @param months
     * @return
     */
    List<Integer> getMemberCountByMonths(ArrayList<String> months) throws Exception;
}
