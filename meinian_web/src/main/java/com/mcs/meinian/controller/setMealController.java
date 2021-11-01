package com.mcs.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mcs.meinian.constant.MessageConstant;
import com.mcs.meinian.utils.QiniuUtils;
import com.mcs.meinian.constant.RedisConstant;
import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.entity.Result;
import com.mcs.meinian.pojo.Setmeal;
import com.mcs.meinian.pojo.TravelGroup;
import com.mcs.meinian.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.UUID;

/**
 * @Author Alex
 * @DATE 2021/10/30 16:27
 **/
@RestController
@RequestMapping("/setMeal")
public class setMealController {

    @Reference
    private SetMealService setMealService;

    @Autowired
    private JedisPool jedisPool;


    /**
     * 图片上传功能
     *
     * @param imgFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        try {
            //获取文件名
            String fileName = imgFile.getOriginalFilename();
            //截取文件名---.jpg
            String hzName = fileName.substring(fileName.lastIndexOf("."));
            //获取uuid
            String uuid = UUID.randomUUID().toString();
            //拼接完整名字
            fileName = uuid + hzName;
            //上传文件
            QiniuUtils.upload2Qiniu(fileName.getBytes(), fileName);
            //将照片名称保存到redis中
            Jedis jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            //上传成功
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }

    }

    /**
     * 回显抱团游数据
     *
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<TravelGroup> list = setMealService.findAll();
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

    /**
     * 添加套餐
     *
     * @param setmeal
     * @param travelGroupIds
     * @return
     */
    @RequestMapping("/addSetMeal")
    public Result addSetMeal(@RequestBody Setmeal setmeal, Integer[] travelGroupIds) {
        try {
            setMealService.addSetMeal(setmeal, travelGroupIds);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }

    }

    /**
     * 分页查询套餐游
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) throws Exception {
        return setMealService.findPage(queryPageBean);
    }

    /**
     * 删除套餐
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteSetMeal")
    public Result deleteSetMeal(Integer id) {
        try {
            setMealService.deleteSetMeal(id);
            return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }

    /**
     * 编辑套餐数据回显
     *
     * @param id
     * @return
     */
    @RequestMapping("/getTravelGroupIdBySetMeal")
    public Result getTravelGroupIdBySetMeal(Integer id) {
        try {
            List<Integer> list = setMealService.getTravelGroupIdBySetMeal(id);
            return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }

    /**
     * 编辑套餐
     * @param setmeal
     * @param travelGroupIds
     * @return
     */
    @RequestMapping("/updateSetMeal")
    public Result updateSetMeal(@RequestBody Setmeal setmeal,Integer[] travelGroupIds) {

        try {
            setMealService.updateSetMeal(setmeal, travelGroupIds);
            return new Result(true,MessageConstant.UPDATE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.UPDATE_SETMEAL_FAIL);
        }
    }
}
