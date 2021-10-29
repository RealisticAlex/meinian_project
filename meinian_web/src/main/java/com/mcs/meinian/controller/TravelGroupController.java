package com.mcs.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.pojo.TravelGroup;
import com.mcs.meinian.pojo.TravelItem;
import com.mcs.meinian.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.PortUnreachableException;
import java.util.List;

/**
 * @Author Alex
 * @DATE 2021/10/29 14:40
 **/
@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {

    @Reference
    private TravelGroupService travelGroupService;

    /**
     * 查询所有自由行
     *
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll() {

        try {
            List<TravelItem> list = travelGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

    /**
     * 新增抱团游
     * @param travelGroup
     * @param travelItemIds
     * @return
     */
    @RequestMapping("/addTravelGroup")
    public Result addTravelGroup(@RequestBody TravelGroup travelGroup, Integer[] travelItemIds){

        try {
            travelGroupService.addTravelGroup(travelGroup,travelItemIds);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }

    /**
     * 抱团游分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) throws Exception {
        return travelGroupService.findPage(queryPageBean);
    }

    /**
     * 删除抱团游信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteTravelGroup")
    public Result deleteTravelGroup(Integer id) {

        try {
            travelGroupService.deleteTravelGroup(id);
            return new Result(true,MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TRAVELGROUP_FAIL);
        }
    }

    /**
     * 编辑抱团游的自由行信息回显
     * @param id
     * @return
     */
    @RequestMapping("/getTravelItemIdsByTravelGroupId")
    public Result getTravelItemIdsByTravelGroupId(Integer id) {
        try {
            List<Integer> list = travelGroupService.getTravelItemIdsByTravelGroupId(id);
            return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

    /**
     * 编辑抱团游
     * @param travelGroup
     * @param travelItemIds
     * @return
     */
    @RequestMapping("/updateTravelGroup")
    public Result updateTravelGroup(@RequestBody TravelGroup travelGroup, Integer[] travelItemIds) {
        try {
            travelGroupService.updateTravelGroup(travelGroup, travelItemIds);
            return new Result(true, MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
    }


}
