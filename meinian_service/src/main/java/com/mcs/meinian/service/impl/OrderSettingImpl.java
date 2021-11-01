package com.mcs.meinian.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.mcs.meinian.mapper.OrderSettingMapper;
import com.mcs.meinian.pojo.OrderSetting;
import com.mcs.meinian.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Alex
 * @DATE 2021/11/1 19:04
 **/
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;


    /**
     * 根据情况批量导入预约信息
     * @param orderSettings
     * @throws Exception
     */
    @Override
    public void addOrderSettings(ArrayList<OrderSetting> orderSettings) throws Exception {

        for (OrderSetting orderSetting : orderSettings) {
            //根据预约日期查询相应预约设置数据的记录数
            int count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
            if (count > 0) {
                //修改预约日期所对应的可预约人数
                orderSettingMapper.updateOrderSetting(orderSetting);
            }else {
                //添加预约日期所对应的预约设置信息
                orderSettingMapper.addOrderSetting(orderSetting);

            }

        }
    }

    /**
     * 根据日期预约设置
     * @param orderDate
     * @return
     */
    @Override
    public List<Map> getOrderSettingByMonth(String orderDate) throws Exception {
        String startDate = orderDate + "-1";
        String endDate = orderDate + "-31";
        List<OrderSetting> orderSettingList =  orderSettingMapper.getOrderSettingByMonth(startDate, endDate);
        //将orderSetting转换为浏览器所需要的格式
        ArrayList<Map> list = new ArrayList<>();
        for (OrderSetting orderSetting : orderSettingList) {
            Map map = new HashMap();
            map.put("date", orderSetting.getOrderDate().getDate());
            map.put("number", orderSetting.getNumber());
            map.put("reservations", orderSetting.getReservations());
            list.add(map);
        }
        return list;
    }

    /**
     * 根据日期设置可预约人数
     * @param orderSetting
     * @throws Exception
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) throws Exception {
        //根据预约日期查询相应预约设置数据的记录数
        int count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0) {
            //修改预约日期所对应的可预约人数
            orderSettingMapper.updateOrderSetting(orderSetting);
        }else {
            //添加预约日期所对应的预约设置信息
            orderSettingMapper.addOrderSetting(orderSetting);

        }

    }
}
