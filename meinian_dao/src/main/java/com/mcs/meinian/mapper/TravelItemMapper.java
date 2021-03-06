package com.mcs.meinian.mapper;

import com.github.pagehelper.Page;
import com.mcs.meinian.pojo.TravelItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelItemMapper {

    /**
     * 新增自由行
     *
     * @param travelItem
     * @return
     * @throws Exception
     */
    int addTravelItem(TravelItem travelItem) throws Exception;

    /**
     *
     * 根据查询条件查询自由行信息
     * @param queryString
     * @return
     */
    Page findPageTravelItemByQueryString(@Param("queryString") String queryString);

    /**
     * 根据id删除自由行
     * @param id
     * @return
     * @throws Exception
     */
    int deleteTravelItemById(@Param("id") Integer id) throws Exception;

    /**
     * 根据id编辑自由行
     * @param travelItem
     * @return
     * @throws Exception
     */
    int editTravelItemById(TravelItem travelItem) throws Exception;


    /**
     * 分布查询第三步
     * @param travelGroupId
     * @return
     * @throws Exception
     */
    List<TravelItem> findTravelItemByTravelGroupId(@Param("travelGroupId") Integer travelGroupId) throws Exception;
}
