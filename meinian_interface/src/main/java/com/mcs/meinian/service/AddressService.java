package com.mcs.meinian.service;

import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.pojo.Address;

import java.util.List;
import java.util.Map;

/**
 * @Author Alex
 * @Date 2021/11/5 16:04
 */
public interface AddressService {
    /**
     * 查询地址信息
     * @return
     */
    List<Address> findAllMaps() throws Exception;

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     * @throws Exception
     */
    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    /**
     * 删除数据
     * @param id
     * @throws Exception
     */
    void deleteById(Integer id) throws Exception;

    /**
     * 新建地址
     * @param address
     * @throws Exception
     */
    void addAddress(Address address) throws Exception;
}
