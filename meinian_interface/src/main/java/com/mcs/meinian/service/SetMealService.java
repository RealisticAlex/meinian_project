package com.mcs.meinian.service;

import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.pojo.Setmeal;
import com.mcs.meinian.pojo.TravelGroup;

import java.util.List;

public interface SetMealService {


    /**
     * 查询所有抱团游信息
     * @return
     * @throws Exception
     */
    List<TravelGroup> findAll() throws Exception;

    /**
     * 新增套餐
     * @param setmeal
     * @param travelGroupIds
     */
    void addSetMeal(Setmeal setmeal, Integer[] travelGroupIds) throws Exception;

    /**
     * 分页查询套餐游
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    /**
     * 删除套餐
     * @param id
     * @throws Exception
     */
    void deleteSetMeal(Integer id) throws Exception;

    /**
     * 编辑套餐数据回显
     * @param id
     * @return
     * @throws Exception
     */
    List<Integer> getTravelGroupIdBySetMeal(Integer id) throws Exception;

    /**
     * 编辑套餐
     * @param setmeal
     * @param travelGroupIds
     */
    void updateSetMeal(Setmeal setmeal, Integer[] travelGroupIds) throws Exception;

    /**
     * 查询套餐列表
     * @return
     */
    List<Setmeal> findAllSetmeal() throws Exception;

    /**
     * 通过id查询套餐信息
     * @param id
     * @return
     */
    Setmeal findById(Integer id) throws Exception;
}
