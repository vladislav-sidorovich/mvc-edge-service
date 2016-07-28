package org.demo.edge.mvc;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = "org.demo.edge.mvc")
//@EnableResourceServer
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
        return ActorSystem.create("DemoProcessingCluster", config);
    }

    @Bean(name = "helloRouter")
    public ActorRef rouletteEngineRouter(ActorSystem actorSystem) {
        return actorSystem.actorOf(FromConfig.getInstance().props(Props.empty()), "helloRouter");
    }
}
