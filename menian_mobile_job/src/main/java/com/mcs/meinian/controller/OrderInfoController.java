package com.mcs.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.constant.RedisMessageConstant;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.service.OrderInfoService;
import com.mcs.meinian.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Alex
 * @DATE 2021/11/2 20:10
 **/
@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderInfoService orderInfoService;

    /**
     * orderInfo：
     * name：旅游人的姓名
     * sex：性别
     * telephone：手机号码
     * validateCode：验证码
     * idCard：身份证号
     * orderDate：预约日期
     * setmealId：预约的套餐游的id
     *
     * @param map
     * @return
     */
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map<String, String> map) {
        try {
            //获取手机号
            String telephone = map.get("telephone");
            //获取验证码
            String code = map.get("validateCode");
            String s = telephone + ":" + RedisMessageConstant.SENDTYPE_ORDER;
            //获取redis中的验证码
            String codeInRedis = jedisPool.getResource().get(s);
            //判断用户输入的验证码是否正确
            if (codeInRedis != null && codeInRedis.equals(code)) {
                //校验验证码,业务交给service层处理
                return orderInfoService.submitOrder(map);
            } else {
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) throws Exception {
        Map map = null;
        map = orderInfoService.findById(id);
        //把数据库中date转换为string类型响应回浏览器
        Date orderDate =(Date) map.get("orderDate");
        map.put("orderDate", DateUtils.parseDate2String(orderDate));
        return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, map);
    }
}
