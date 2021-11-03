package com.mcs.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.constant.RedisMessageConstant;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.pojo.Member;
import com.mcs.meinian.service.MemberService;
import com.mcs.meinian.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Map;

/**
 * @Author Alex
 * @Date 2021/11/3 20:41
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Reference
    private MemberService memberService;

    @Reference
    private OrderInfoService orderInfoService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/check")
    public Result check(@RequestBody Map map) throws Exception {
        //获取手机号
        String telephone = (String) map.get("telephone");
        //获取验证码
        String validateCode = (String) map.get("validateCode");
        Jedis jedis = jedisPool.getResource();
        String codeInRedis = jedis.get(telephone + ":" + RedisMessageConstant.SENDTYPE_LOGIN);
        //验证码校验通过
        if (validateCode != null && validateCode.equals(codeInRedis)) {
            //判断是否为会员
            Member member = memberService.findMemberByTelephone(telephone);
            //如果不是会员
            if (member == null) {
                //注册会员
                member = new Member();
                member.setPhoneNumber(telephone);
                member.getRegTime(new Date());
                memberService.addMember(member);
            }else {
                //登录
                return new Result(true, MessageConstant.LOGIN_SUCCESS);
            }
        }
        return new Result(false,MessageConstant.VALIDATECODE_ERROR);
    }
}
