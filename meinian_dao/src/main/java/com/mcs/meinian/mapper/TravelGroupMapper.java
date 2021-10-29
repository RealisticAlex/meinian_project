package com.mcs.meinian.mapper;

import com.github.pagehelper.Page;
import com.mcs.meinian.pojo.TravelGroup;
import com.mcs.meinian.pojo.TravelItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelGroupMapper {


    /**
     * 查询所有自由行
     *
     * @return
     * @throws Exception
     */
    List<TravelItem> findAll() throws Exception;

    /**
     * 添加抱团游
     *
     * @param travelGroup
     * @throws Exception
     */
    void addTravelGroup(TravelGroup travelGroup) throws Exception;

    /**
     * 添加抱团游和自由行之间的关系
     * @param travelGroupId
     * @param travelItemIds
     * @throws Exception
     */
    void addTravelGroupAndTravelItem(@Param("travelGroupId") Integer travelGroupId,@Param("travelItemIds") Integer[] travelItemIds) throws Exception;

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page findPageTravelItemByQueryString(@Param("queryString") String queryString) throws Exception;

    /**
     * 根据id删除抱团游
     * @param id
     */
    void deleteTravelGroupById(@Param("id") Integer id) throws Exception;

    /**
     * 编辑抱团行获取自由行信息
     * @param id
     * @return
     */
    List<Integer> getTravelItemIdsByTravelGroupId(@Param("id") Integer id) throws Exception;

    /**
     * 更新抱团游表的信息
     * @throws Exception
     */
    void updateTravelGroup(TravelGroup travelGroup) throws Exception;

    /**
     * 删除抱团游和自由行中间表的信息
     * @param id
     */
    void deleteTravelGroupAndTravelItemById(@Param("id") Integer id) throws Exception;
}
