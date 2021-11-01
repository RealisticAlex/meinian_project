package com.mcs.meinian.service;

import com.mcs.meinian.pojo.OrderSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    /**
     * 插入预约信息数据
     * @param orderSettings
     */
    void addOrderSettings(ArrayList<OrderSetting> orderSettings) throws Exception;

    /**
     * 预约设置
     * @param orderDate
     * @return
     */
    List<Map> getOrderSettingByMonth(String orderDate) throws Exception;

    /**
     * 预约设置
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting) throws Exception;
}
