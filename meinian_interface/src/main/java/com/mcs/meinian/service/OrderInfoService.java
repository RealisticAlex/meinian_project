package com.mcs.meinian.service;

import com.mcs.meinian.entity.Result;

import java.util.Map;

public interface OrderInfoService {

    /**
     * 提交预约
     * @param map
     * @throws Exception
     */
    Result submitOrder(Map<String, String> map) throws Exception;

    /**
     * 回显预约套餐信息
     * @param id
     * @return
     */
    Map findById(Integer id) throws Exception;
}
