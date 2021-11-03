package com.mcs.meinian.controller;

import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.constant.RedisMessageConstant;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.utils.SMSUtils;
import com.mcs.meinian.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author Alex
 * @DATE 2021/11/2 19:50
 **/
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 发送验证码功能
     * @param telephone
     * @return
     */
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //生成验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            //发送短信
            SMSUtils.sendShortMessage(telephone,code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码存储到redis中
        jedisPool.getResource().setex(telephone +":"+ RedisMessageConstant.SENDTYPE_ORDER, 60 * 50, code.toString());
        //验证码发送成功
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone) {
        //获取手机号
        //生产验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        //发送短信
        try {
            SMSUtils.sendShortMessage(telephone,validateCode.toString());
            Jedis jedis = jedisPool.getResource();
            jedis.setex(telephone + ":" + RedisMessageConstant.SENDTYPE_LOGIN, 60 * 60 * 100, validateCode.toString());
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
