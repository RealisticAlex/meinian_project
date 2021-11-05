package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.mapper.OrderInfoMapper;
import com.mcs.meinian.pojo.Member;
import com.mcs.meinian.pojo.Order;
import com.mcs.meinian.pojo.OrderSetting;
import com.mcs.meinian.service.OrderInfoService;
import com.mcs.meinian.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

/**
 * @Author Alex
 * @DATE 2021/11/2 20:40
 **/
@Service(interfaceClass = OrderInfoService.class)
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 提交预约
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
     * @throws Exception
     */
    @Override
    public Result submitOrder(Map<String, String> map) throws Exception {

        //获取用户输入的日期
        String orderDate = map.get("orderDate");
        //通过日期查询可预约人数
        OrderSetting orderSetting = orderInfoMapper.
                findOrderSettingByOrderDate(orderDate);
        //当前日期不可预约
        if (orderSetting == null) {
            //不可预约
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        } else {
            //预约已满
            if (orderSetting.getNumber() == 0) {
                //不可预约
                return new Result(false, MessageConstant.ORDERSETTING_FAIL);
            }
        }
        //获取手机号
        String telephone = map.get("telephone");
        //通过手机号查询该用户是否为会员
        //查询数据库比对手机号是否存在,存在则创建为会员,不存在则直接预约
        Member member = orderInfoMapper.findMemberByTelephone(telephone);
        //为空创建为会员
        if (member == null) {
            member = new Member();
            member.setName(map.get("name"));
            member.setIdCard(map.get("idCard"));
            member.setPhoneNumber(map.get("telephone"));
            member.setRegTime(DateUtils.parseString2Date(map.get("orderDate")));
            member.setSex(map.get("sex"));
            orderInfoMapper.addMember(member);
        } else {
            //已经是会员直接预约,判断预约日期是否重复
            Order order = new Order();
            order.setMemberId(member.getId());
            order.setOrderDate(DateUtils.parseString2Date(orderDate));
            order.setSetmealId(Integer.parseInt(map.get("setmealId")));
            int count = orderInfoMapper.findCountByCondition(order);
            //判断是否已经预约了
            if (count != 0) {
                //该用户已经预约了
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }
        //可以预约
        //预约数+1
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        orderInfoMapper.updateReservationsByOrderDate(orderDate);
        //添加预约信息
        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(DateUtils.parseString2Date(orderDate));
        order.setOrderStatus(Order.ORDERTYPE_WEIXIN);
        order.setOrderType(Order.ORDERSTATUS_NO);
        order.setSetmealId(Integer.parseInt(map.get("setmealId")));
        orderInfoMapper.addOrder(order);
        //返回一个id,预约成功的时候用于id双向绑定回显数据
        return new Result(true, MessageConstant.ORDER_SUCCESS,order.getId());
    }

    /**
     * 回显预约套餐信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Map findById(Integer id) throws Exception {

        return orderInfoMapper.findById(id);
    }
}
