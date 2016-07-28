package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
public class ZooClientApplication {
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discovery;
    @Autowired
    private Environment env;

    @Autowired
    RestTemplate rest;

    @RequestMapping("/")
    public ServiceInstance lb() {
        return this.loadBalancer.choose(appName);
    }

    @RequestMapping("/hi")
    public String hi() {
        return "Hello World! from " + this.discovery.getLocalServiceInstance();
    }

    @RequestMapping("/all")
    public List<String> self() {
        return discovery.getServices();
    }

    @RequestMapping("/balance")
    public String load() {
        String response = rest.getForObject("http://" + appName + "/hi", String.class);
        return response;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZooClientApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }

}
