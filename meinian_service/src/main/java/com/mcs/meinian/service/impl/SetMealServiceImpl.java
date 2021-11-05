package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mcs.meinian.constant.RedisConstant;
import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.mapper.SetMealMapper;
import com.mcs.meinian.pojo.Setmeal;
import com.mcs.meinian.pojo.TravelGroup;
import com.mcs.meinian.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * @Author Alex
 * @DATE 2021/10/30 16:32
 **/
@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealMapper setMealMapper;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 查询所有抱团游
     * @return
     * @throws Exception
     */
    @Override
    public List<TravelGroup> findAll() throws Exception {
        return setMealMapper.findAll();
    }

    /**
     * 新增套餐
     * @param setmeal
     * @param travelGroupIds
     */
    @Override
    public void addSetMeal(Setmeal setmeal, Integer[] travelGroupIds) throws Exception {
        setMealMapper.addSetMeal(setmeal);
        setMealMapper.addSetmealAndTravelGroupId(setmeal.getId(), travelGroupIds);
        //将保存的数据库中的照片名称保存到redis中
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     * @throws Exception
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) throws Exception {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page page = setMealMapper.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());

    }

    /**
     * 删除套餐
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteSetMeal(Integer id) throws Exception {
        //先删除中间表数据
        setMealMapper.deleteSetMealAndTravelGroup(id);
        //在删除套餐
        setMealMapper.deleteSetMeal(id);
    }

    /**
     * 编辑套餐数据回显
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> getTravelGroupIdBySetMeal(Integer id) throws Exception {
        return setMealMapper.getTravelGroupIdBySetMeal(id);
    }

    /**
     * 编辑套餐
     * @param setmeal
     * @param travelGroupIds
     */
    @Override
    public void updateSetMeal(Setmeal setmeal, Integer[] travelGroupIds) throws Exception {
        //通过id编辑套餐原有信息
        setMealMapper.updateSetMeal(setmeal);
        //删除中间表信息
        setMealMapper.deleteSetMealAndTravelGroup(setmeal.getId());
        //添加中间表信息
        setMealMapper.addSetmealAndTravelGroupId(setmeal.getId(),travelGroupIds);
    }

    /**
     * 查询套餐列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Setmeal> findAllSetmeal() throws Exception {
        return setMealMapper.findAllSetmeal();
    }

    /**
     * 通过id查询套餐信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Setmeal findById(Integer id) throws Exception {
        return setMealMapper.findSetmealById(id);
    }

    /**
     * 通过套餐查询预约人数
     * @return
     * @throws Exception
     */
    @Override
    public List<Map> findSetmealCount() throws Exception {
        return setMealMapper.findSetmealCount();
    }


}
