package com.mcs.meinian.service;

import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.pojo.TravelItem;

public interface TravelItemService {

    /**
     * 新增自由行业务
     * @param travelItem
     * @throws Exception
     */
    void addTravelItem(TravelItem travelItem) throws Exception;

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    /**
     * 根据id删除自由行业务
     * @param id
     * @throws Exception
     */
    void deleteTravelItem(Integer id) throws Exception;

    /**
     * 根据id编辑自由行
     * @param travelItem
     * @throws Exception
     */
    void editTravelItem(TravelItem travelItem) throws Exception;
}
