package com.demo;


import com.demo.dao.UserRepository;
import com.demo.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AService {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;


    @HystrixCommand(
            fallbackMethod = "errorHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            }
    )
    public String getSomethingFromServiceB() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service_b");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/getSomething";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }


    @HystrixCommand(fallbackMethod = "defaultStores",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
            }
    )
    public List<User> getSomethingFromDataSource() {

        randomlyRunLong();
        List<User> userList = userRepository.findAll();
        userList.forEach(System.out::println);
        return userList;
    }

    public List<User> defaultStores() {
        System.out.println("defaultStores");
        return new ArrayList<>();
    }

    public String errorHandler(Throwable throwable){
        return throwable.toString();
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
