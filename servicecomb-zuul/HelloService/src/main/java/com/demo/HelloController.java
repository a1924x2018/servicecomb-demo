package com.demo;


import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

//restful服务契约
@RestSchema(schemaId = "helloController")
@RequestMapping(path = "/")
public class HelloController {

    @GetMapping(path = "/hello/{name}")
    public String hello(@PathVariable("name") String name, HttpServletRequest request) {

        //打印出当前微服务实例的IP地址和端口，稍后在控制台可看到哪个微服务实例被调用
        System.out.println("HelloSevice 实例"+request.getLocalAddr()+":"+request.getLocalPort());
        return "Hello, " + name;
    }

}
