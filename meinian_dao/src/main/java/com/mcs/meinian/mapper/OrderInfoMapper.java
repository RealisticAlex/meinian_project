package com.mcs.meinian.mapper;

import com.mcs.meinian.pojo.Member;
import com.mcs.meinian.pojo.Order;
import com.mcs.meinian.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface OrderInfoMapper {

    /**
     * 通过日期查找预约数
     * @param orderDate
     * @return
     * @throws Exception
     */
    OrderSetting findOrderSettingByOrderDate(@Param("orderDate") String orderDate) throws Exception;

    /**
     * 通过电话查找会员
     * @param telephone
     * @return
     * @throws Exception
     */
    Member findMemberByTelephone(@Param("telephone") String telephone) throws Exception;

    /**
     * 添加会员
     * @param member
     * @return
     * @throws Exception
     */
    Member addMember(Member member) throws Exception;

    /**
     * 条件查询是否预约
     * @param order
     * @return
     * @throws Exception
     */
    int findCountByCondition(Order order) throws Exception;

    /**
     * 更改预约数
     * @param orderDate
     * @throws Exception
     */
    void updateReservationsByOrderDate(@Param("orderDate") String orderDate) throws Exception;

    /**
     * 添加预约信息
     * @param order
     */
    void addOrder(Order order) throws Exception;

    /**
     * 回显预约套餐信息
     * @param id
     * @return
     * @throws Exception
     */
    Map findById(@Param("id") Integer id) throws Exception;
}
