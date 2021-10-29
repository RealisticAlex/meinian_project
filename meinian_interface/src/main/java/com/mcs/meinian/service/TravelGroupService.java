package com.mcs.meinian.service;

import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.pojo.TravelGroup;
import com.mcs.meinian.pojo.TravelItem;

import java.util.List;


public interface TravelGroupService {

    /**
     * 查询所有自由行
     * @return
     * @throws Exception
     */
    List<TravelItem> findAll() throws Exception;

    /**
     * 新增抱团游
     * @param travelGroup
     * @param travelItemIds
     */
    void addTravelGroup(TravelGroup travelGroup, Integer[] travelItemIds) throws Exception;

    /**
     * 抱团游分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    /**
     * 根据id删除抱团游
     * @param id
     * @throws Exception
     */
    void deleteTravelGroup(Integer id) throws Exception;

    /**
     * 编辑抱团行获取自由行信息
     * @param id
     * @return
     */
    List<Integer> getTravelItemIdsByTravelGroupId(Integer id) throws Exception;

    /**
     * 编辑抱团游
     * @param travelGroup
     * @param ids
     */
    void updateTravelGroup(TravelGroup travelGroup, Integer[] ids) throws Exception;
}
