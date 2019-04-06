package com.springboot.sbe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RequestMapping("sbc")
public class SpringBootClient {
    public static void main(String[] args){

        SpringApplication.run(SpringBootClient.class, args);
    }

    //必须要加上，它不仅进行负载均衡，默认是轮询调用，还要从注册中心获取服务列表地址
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value="/invokeSayHello", method = RequestMethod.GET)
    @ResponseBody
    public String invokeSayHello(){
        long stTime = System.currentTimeMillis();
        String hs = restTemplate.getForObject("http://SERVICE-HI/sbs/sayHello", String.class);
        long cost = System.currentTimeMillis() - stTime;
        return "invokeSayHello ==> " + hs + " (costTime:" + cost + "ms)";
    }
}
