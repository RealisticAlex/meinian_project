package com.mcs.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.pojo.Setmeal;
import com.mcs.meinian.service.SetMealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Alex
 * @DATE 2021/11/2 15:07
 **/
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {

    @Reference
    private SetMealService setMealService;

    /**
     * 显示所有套餐列表
     * @return
     */
    @RequestMapping("/findAllSetmeal")
    public Result findAllSetmeal(){
        try {
            List<Setmeal> list = setMealService.findAllSetmeal();
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }

    /**
     * 通过id查询套餐信息
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Setmeal setmeal = setMealService.findById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }

    }



}
