package com.demo;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@RestSchema(schemaId = "bController")
@RequestMapping("/")
public class BController {

    @GetMapping("/getSomething")
    public String getSomething(){
        randomlyRunLong();
        return "Hello";
    }


    private void randomlyRunLong(){
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum==3) sleep();
    }

    private void sleep(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
