package com.mcs.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.pojo.TravelItem;
import com.mcs.meinian.service.TravelItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Alex
 * @DATE 2021/10/27 14:49
 **/
@RestController
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference
    private TravelItemService travelItemService;

    /**
     * 新增自由行
     *
     * @param travelItem
     * @return
     */
    @RequestMapping("/addTravelItem")
    @PreAuthorize("hasAnyAuthority('TRAVELITEM_ADD')")
    public Result travelItem(@RequestBody TravelItem travelItem) {

        try {
            travelItemService.addTravelItem(travelItem);
            return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }

    }
    /**
     * 自由行分页查询
     * @param queryPageBean
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPage")
    @PreAuthorize("hasAnyAuthority('TRAVELITEM_QUERY')")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) throws Exception {
        return travelItemService.findPage(queryPageBean);
    }

    /**
     * 根据id删除自由行
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteTravelItem")
    @PreAuthorize("hasAnyAuthority('TRAVELGROUP_DELETE')")
    public Result deleteTravelItem(Integer id) {
        try {
            travelItemService.deleteTravelItem(id);
            return new Result(true, MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
    }

    /**
     * 根据id编辑自由行
     *
     * @param travelItem
     * @return
     */
    @RequestMapping("/editTravelItem")
    @PreAuthorize("hasAnyAuthority('SETMEAL_EDIT')")
    public Result editTravelItem(@RequestBody TravelItem travelItem) {

        try {
            travelItemService.editTravelItem(travelItem);
            return new Result(true, MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELITEM_FAIL);
        }

    }
}
