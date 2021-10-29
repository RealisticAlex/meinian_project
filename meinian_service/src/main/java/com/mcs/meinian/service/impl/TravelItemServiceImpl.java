package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.mapper.TravelItemMapper;
import com.mcs.meinian.pojo.TravelItem;
import com.mcs.meinian.service.TravelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Alex
 * @DATE 2021/10/27 16:02
 **/
@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    private TravelItemMapper travelItemMapper;

    /**
     * 新增自由行业务
     * @param travelItem
     * @throws Exception
     */
    @Override
    public void addTravelItem(TravelItem travelItem) throws Exception {
        travelItemMapper.addTravelItem(travelItem);
    }

    /**
     * 分页查询业务
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean){

        //开启分页查询
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page page = travelItemMapper.findPageTravelItemByQueryString(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 根据id删除自由行业务
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteTravelItem(Integer id) throws Exception {

        travelItemMapper.deleteTravelItemById(id);
    }

    /**
     * 根据id编辑自由行
     * @param travelItem
     * @throws Exception
     */
    @Override
    public void editTravelItem(TravelItem travelItem) throws Exception {
        travelItemMapper.editTravelItemById(travelItem);
    }
}
