package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mcs.meinian.mapper.MemberMapper;
import com.mcs.meinian.mapper.ReportMapper;
import com.mcs.meinian.service.ReportService;
import com.mcs.meinian.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Alex
 * @Date 2021/11/5 13:41
 */
@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    //reportDate:null, // 日期
    //todayNewMember :0, // 新增会员数
    //totalMember :0,// 总会员数
    //thisWeekNewMember :0,// 本周新增会员数
    //thisMonthNewMember :0,// 本月新增会员数
    //todayOrderNumber :0,// 今日预约数
    //todayVisitsNumber :0,// 今日出游数
    //thisWeekOrderNumber :0,// 本周预约数
    //thisWeekVisitsNumber :0,// 本周出游数
    //thisMonthOrderNumber :0,// 本月预约数
    //thisMonthVisitsNumber :0,// 本月出游数
    //hotSetmeal
    @Override
    public Map<String,Object> getBusinessReportData() throws Exception {
        //将数据封装成map集合
        Map<String,Object> map = new HashMap<>();
        Date date = DateUtils.getToday();
        //获取今日日期
        String reportDate = date + "";
        map.put("reportDate", reportDate);
        //获取新增会员数
        Integer todayNewMember = reportMapper.todayNewMember(reportDate);
        map.put("todayNewMember", todayNewMember);
        //获取总会员数
        Integer totalMember = reportMapper.totalMember();
        map.put("totalMember", totalMember);
        //本周新增会员数
        Date thisWeekMonday0 = DateUtils.getThisWeekMonday();
        //****本周第一天****
        String thisWeekMonday = thisWeekMonday0 + "";
        Integer thisWeekNewMember = reportMapper.thisWeekNewMember(reportDate,thisWeekMonday);
        map.put("thisWeekNewMember", thisWeekNewMember);
        //本月新增会员数
        Date firstDay4ThisMonth = DateUtils.getFirstDay4ThisMonth();
        //****本月第一天****
        String firstDay4ThisMonth1 = firstDay4ThisMonth + "";
        Integer thisMonthNewMember =  reportMapper.thisMonthNewMember(firstDay4ThisMonth1,reportDate);
        map.put("thisMonthNewMember", thisMonthNewMember);
        //获取今日预约数
        Integer todayOrderNumber = reportMapper.todayOrderNumber(reportDate);
        map.put("todayOrderNumber", todayOrderNumber);
        //获取今日出游数
        Integer todayVisitsNumber = reportMapper.todayVisitsNumber(reportDate);
        map.put("todayVisitsNumber", todayVisitsNumber);
        //获取本周预约数
        Integer thisWeekOrderNumber = reportMapper.thisWeekOrderNumber(thisWeekMonday,reportDate);
        map.put("thisWeekOrderNumber",thisWeekOrderNumber);
        //获取本周出游数
        Integer thisWeekVisitsNumber = reportMapper.thisWeekVisitsNumber(thisWeekMonday,reportDate);
        map.put("thisWeekVisitsNumber", thisWeekVisitsNumber);
        //获取本月预约数
        Integer thisMonthOrderNumber = reportMapper.thisMonthOrderNumber(firstDay4ThisMonth1, reportDate);
        map.put("thisMonthOrderNumber",thisMonthOrderNumber);
        //获取本月出游数
        Integer thisMonthVisitsNumber = reportMapper.thisMonthVisitsNumber(firstDay4ThisMonth1, reportDate);
        map.put("thisMonthVisitsNumber", thisMonthVisitsNumber);
        //获取前2火热的套餐数量
        List<Map> hotSetmeal =  reportMapper.findHotSetmeal();
        map.put("hotSetmeal", hotSetmeal);
        return map;
    }
}
