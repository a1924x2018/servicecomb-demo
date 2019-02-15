package com.demo;

import com.demo.pojo.User;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestSchema(schemaId = "aController")
@RequestMapping("/")
public class AController
{

    @Autowired
    private  AService aService;

    @GetMapping("/getSomethingFromServiceB")
    public String getSomethingFromServiceB(){
        return aService.getSomethingFromServiceB();
    }

    @GetMapping("/getSomethingFromDataSource")
    public List<User> getSomethingFromDataSource(){
        return aService.getSomethingFromDataSource();
    }

}
