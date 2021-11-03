package com.mcs.meinian.service;

import com.mcs.meinian.pojo.Member;

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
}
