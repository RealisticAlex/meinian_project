package com.mcs.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.pojo.Address;
import com.mcs.meinian.service.AddressService;
import com.qiniu.util.Json;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author Alex
 * @Date 2021/11/5 16:03
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;


    /**
     * 查询地址
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllMaps")
    public List<Address> findAllMaps() throws Exception {
        return addressService.findAllMaps();
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) throws Exception {
        return addressService.findPage(queryPageBean);
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {
        try {
            addressService.deleteById(id);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 添加地址
     * @param address
     * @return
     */
    @RequestMapping("/addAddress")
    public Result addAddress(@RequestBody Address address) {
        try {
            addressService.addAddress(address);
            return new Result(true, "地址新建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "地址新建失败");
        }
    }
}
