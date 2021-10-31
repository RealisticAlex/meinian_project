package com.mcs.meinian.job;

import com.mcs.meinian.constant.QiniuUtils;
import com.mcs.meinian.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @Author Alex
 * @DATE 2021/10/31 18:54
 **/
@Component
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg() {
        //获取redis对象
        Jedis jedis = jedisPool.getResource();
        //取差集
        Set<String> stringSet = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        //删除七牛云中的垃圾照片(差集照片)
        for (String fileName : stringSet) {
            QiniuUtils.deleteFileFromQiniu(fileName);
            jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            System.out.println("删除的照片 " + fileName);
        }
        jedis.close();
    }
}
