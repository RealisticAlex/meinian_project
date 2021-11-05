package com.mcs.meinian.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author Alex
 * @Date 2021/11/5 13:42
 */
public interface ReportMapper {


    Integer todayNewMember(@Param("reportDate") String reportDate) throws Exception;

    Integer totalMember() throws Exception;

    Integer thisWeekNewMember(@Param("reportDate") String reportDate, @Param("thisWeekMonday") String thisWeekMonday) throws Exception;

    Integer thisMonthNewMember(@Param("firstDay4ThisMonth1") String firstDay4ThisMonth1, @Param("reportDate") String reportDate) throws Exception;

    Integer todayOrderNumber(@Param("reportDate") String reportDate) throws Exception;

    Integer todayVisitsNumber(@Param("reportDate") String reportDate) throws Exception;

    Integer thisWeekOrderNumber(@Param("thisWeekMonday") String thisWeekMonday, @Param("reportDate") String reportDate) throws Exception;

    Integer thisWeekVisitsNumber(@Param("thisWeekMonday") String thisWeekMonday, @Param("reportDate") String reportDate) throws Exception;

    Integer thisMonthOrderNumber(@Param("firstDay4ThisMonth1") String firstDay4ThisMonth1, @Param("reportDate") String reportDate) throws Exception;

    Integer thisMonthVisitsNumber(@Param("firstDay4ThisMonth1") String firstDay4ThisMonth1, @Param("reportDate") String reportDate) throws Exception;

    List<Map> findHotSetmeal() throws Exception;
}
