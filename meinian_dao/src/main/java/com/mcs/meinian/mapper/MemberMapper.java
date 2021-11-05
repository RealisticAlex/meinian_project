package com.mcs.meinian.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author Alex
 * @Date 2021/11/5 12:06
 */
public interface MemberMapper {


    /**
     * 根据日期查询当月会员数量
     * @param regTime
     * @return
     */
    Integer getMemberCountByMonths(@Param("regTime") String regTime) throws Exception;
}
