package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mcs.meinian.entity.PageResult;
import com.mcs.meinian.entity.QueryPageBean;
import com.mcs.meinian.mapper.AddressMapper;
import com.mcs.meinian.pojo.Address;
import com.mcs.meinian.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author Alex
 * @Date 2021/11/5 16:04
 */
@Service(interfaceClass = AddressService.class)
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询分公司地址信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Address> findAllMaps() throws Exception {
        return addressMapper.findAllMaps();
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) throws Exception {
        //开启分页查询
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //模糊查询
        Page page = addressMapper.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 删除地址
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteById(Integer id) throws Exception {
        addressMapper.deleteById(id);
    }

    /**
     * 新建地址
     * @param address
     * @throws Exception
     */
    @Override
    public void addAddress(Address address) throws Exception {
        addressMapper.addAddress(address);
    }
}
