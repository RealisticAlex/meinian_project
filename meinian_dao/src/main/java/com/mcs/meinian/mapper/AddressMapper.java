package com.mcs.meinian.mapper;

import com.github.pagehelper.Page;
import com.mcs.meinian.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Alex
 * @Date 2021/11/5 16:04
 */
public interface AddressMapper {
    /**
     * 查询分公司地址信息
     * @return
     * @throws Exception
     */
    List<Address> findAllMaps() throws Exception;

    /**
     * 分页模糊查询
     * @param queryString
     * @return
     * @throws Exception
     */
    Page findPage(@Param("queryString") String queryString) throws Exception;

    /**
     * 删除数据
     * @param id
     * @throws Exception
     */
    void deleteById(@Param("id") Integer id) throws Exception;

    /**
     * 新建地址
     * @param address
     * @throws Exception
     */
    void addAddress(Address address) throws Exception;
}
