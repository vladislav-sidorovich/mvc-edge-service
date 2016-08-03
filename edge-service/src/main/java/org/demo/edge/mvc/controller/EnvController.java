package org.demo.edge.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("env")
public class EnvController {
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discovery;

    @RequestMapping
    public ServiceInstance lb() {
        return this.loadBalancer.choose(appName);
    }

    @RequestMapping("/local")
    public String hi() {
        return "Hello World! from " + this.discovery.getLocalServiceInstance();
    }

    @RequestMapping("{appname}")
    public ServiceInstance id(@PathVariable("appname")  String app) {
        return this.loadBalancer.choose(app);
    }

    @RequestMapping("/all")
    public List<String> self() {
        return discovery.getServices();
    }
}
