package com.mcs.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.pojo.OrderSetting;
import com.mcs.meinian.service.OrderSettingService;
import com.mcs.meinian.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author Alex
 * @DATE 2021/11/1 19:04
 **/
@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    /**
     * 读取excel文件并且批量导入预约信息
     *
     * @param excelFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile) throws Exception {
        try {
            //利用工具类读取文件
            List<String[]> list = POIUtils.readExcel(excelFile);
            //将list集合中每个数组转换为orderSetting
            ArrayList<OrderSetting> orderSettings = new ArrayList<>();
            //遍历list集合
            for (String[] strings : list) {
                //转换格式
                OrderSetting orderSetting = new OrderSetting(new Date(strings[0]), Integer.parseInt(strings[1]));
                //添加到orderSettings集合中
                orderSettings.add(orderSetting);
            }
            //批量导入预约信息
            orderSettingService.addOrderSettings(orderSettings);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

    }

    /**
     * 预约设置
     *
     * @param orderDate
     * @return
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String orderDate) {
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(orderDate);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    /**
     * 编辑可预约的人数
     *
     * @return
     */
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
