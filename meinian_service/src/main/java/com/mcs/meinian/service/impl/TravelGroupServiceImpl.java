package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.mapper.TravelGroupMapper;
import com.mcs.meinian.pojo.TravelGroup;
import com.mcs.meinian.pojo.TravelItem;
import com.mcs.meinian.service.TravelGroupService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Alex
 * @DATE 2021/10/29 14:49
 **/
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {

    @Autowired
    private TravelGroupMapper travelGroupMapper;


    /**
     * 查询所有自由行业务
     * @return
     * @throws Exception
     */
    @Override
    public List<TravelItem> findAll() throws Exception {
        return travelGroupMapper.findAll();
    }

    /**
     * 新增抱团游
     * @param travelGroup
     * @param travelItemIds
     */
    @Override
    public void addTravelGroup(TravelGroup travelGroup, Integer[] travelItemIds) throws Exception {
        //添加抱团游信息
        travelGroupMapper.addTravelGroup(travelGroup);
        //添加自由行和抱团游的关系
        travelGroupMapper.addTravelGroupAndTravelItem(travelGroup.getId(),travelItemIds);
    }

    /**
     * 抱团游分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) throws Exception {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page page = travelGroupMapper.findPageTravelItemByQueryString(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据id删除抱团游
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteTravelGroup(Integer id) throws Exception {
        travelGroupMapper.deleteTravelGroupAndTravelItemById(id);
        travelGroupMapper.deleteTravelGroupById(id);
    }

    /**
     * 编辑抱团行获取自由行信息
     * @param id
     * @return
     */
    @Override
    public List<Integer> getTravelItemIdsByTravelGroupId(Integer id) throws Exception {
        return travelGroupMapper.getTravelItemIdsByTravelGroupId(id);
    }

    /**
     * 编辑抱团游
     * @param travelGroup
     * @param travelItemIds
     */
    @Override
    public void updateTravelGroup(TravelGroup travelGroup, Integer[] travelItemIds) throws Exception {
        //更新抱团游表的信息
        travelGroupMapper.updateTravelGroup(travelGroup);
        //删除抱团游和自由行中间表的信息
        travelGroupMapper.deleteTravelGroupAndTravelItemById(travelGroup.getId());
        //重新添加信息
        travelGroupMapper.addTravelGroupAndTravelItem(travelGroup.getId(), travelItemIds);
    }
}
