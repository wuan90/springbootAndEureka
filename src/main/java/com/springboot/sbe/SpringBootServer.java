package com.springboot.sbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableEurekaClient
@RequestMapping("/sbs")
public class SpringBootServer {
    public static void main(String[] args){
        SpringApplication.run(SpringBootServer.class, args);
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(){
        long time = System.currentTimeMillis()/1000;
        System.out.println("sayHello:" + time);
        return String.valueOf("Hello Spring Boot:" + time);
    }
}
