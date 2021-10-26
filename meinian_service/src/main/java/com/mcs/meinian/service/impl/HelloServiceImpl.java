package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mcs.meinian.service.HelloService;

/**
 * @Author Alex
 * @DATE 2021/10/26 15:21
 **/
@Service(interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return "8281" + name;
    }

}
