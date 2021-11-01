package com.mcs.meinian.mapper;

import com.mcs.meinian.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingMapper {

    /**
     * 根据预约日期查询相应预约设置数据的记录数
     * @param orderDate
     * @return
     */
    int findCountByOrderDate(Date orderDate) throws Exception;

    /**
     * 修改预约日期所对应的可预约人数
     * @param orderSetting
     */
    void updateOrderSetting(OrderSetting orderSetting) throws Exception;

    /**
     * 添加预约日期所对应的预约设置信息
     * @param orderSetting
     */
    void addOrderSetting(OrderSetting orderSetting) throws Exception;

    /**
     * 根据日期范围设置
     * @param startDate
     * @param endDate
     */
    List<OrderSetting> getOrderSettingByMonth(@Param("startDate") String startDate,@Param("endDate") String endDate) throws Exception;
}
