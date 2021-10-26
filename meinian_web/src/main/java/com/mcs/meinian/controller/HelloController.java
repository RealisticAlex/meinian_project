package com.mcs.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mcs.meinian.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alex
 * @DATE 2021/10/26 11:49
 **/
@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @RequestMapping("/demo")
    public String hello(String name) {

        return helloService.sayHello(name);
    }
}
