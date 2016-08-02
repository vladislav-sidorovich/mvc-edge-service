package org.demo.edge.mvc;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.demo.akka.cluster.seed.ZookeeperClusterSeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = "org.demo.edge.mvc")
@EnableResourceServer
@EnableOAuth2Client
@EnableDiscoveryClient
public class DemoApplication {
    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Config akkaConfiguration() {
        Config config = ConfigFactory.load();
        log.info("Creating akka node with config:\n{}", config.root().render());
        return config;
    }

    @Bean
    public ActorSystem actorSystem(Config config) throws UnknownHostException {
        ActorSystem system = ActorSystem.create("DemoProcessingCluster", config);
        ZookeeperClusterSeed.get(system).join();
        return system;
    }

    @Bean(name = "helloRouter")
    public ActorRef rouletteEngineRouter(ActorSystem actorSystem) {
        return actorSystem.actorOf(FromConfig.getInstance().props(Props.empty()), "helloRouter");
    }

    @Bean
    @LoadBalanced
    public OAuth2RestOperations restTemplate(OAuth2RestTemplate template) {
        return template;
    }
}
