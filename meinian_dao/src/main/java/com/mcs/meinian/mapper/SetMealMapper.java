package com.mcs.meinian.mapper;

import com.github.pagehelper.Page;
import com.mcs.meinian.pojo.Setmeal;
import com.mcs.meinian.pojo.TravelGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetMealMapper {

    /**
     * 查询所有抱团游
     * @return
     * @throws Exception
     */
    List<TravelGroup> findAll() throws Exception;

    /**
     * 新增套餐
     * @param setmeal
     */
    void addSetMeal(Setmeal setmeal) throws Exception;

    /**
     * 新增套餐--中间表
     * @param id
     * @param travelGroupIds
     */
    void addSetmealAndTravelGroupId(@Param("id") Integer id, @Param("travelGroupIds") Integer[] travelGroupIds) throws Exception;

    /**
     * 模糊分页查询查询
     * @param queryString
     * @return
     */
    Page findPage(@Param("queryString") String queryString) throws Exception;

    /**
     * 删除中间表数据
     * @param id
     */
    void deleteSetMealAndTravelGroup(@Param("id") Integer id) throws Exception;

    /**
     * 删除套餐
     * @param id
     */
    void deleteSetMeal(@Param("id") Integer id) throws Exception;

    /**
     * 编辑套餐数据回显
     * @param id
     * @return
     */
    List<Integer> getTravelGroupIdBySetMeal(@Param("id") Integer id) throws Exception;

    /**
     * 更新套餐数据
     * @param setmeal
     */
    void updateSetMeal(Setmeal setmeal) throws Exception;
}
