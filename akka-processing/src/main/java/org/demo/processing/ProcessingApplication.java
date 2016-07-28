package org.demo.processing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.demo.processing.actor.HelloActor;

public class ProcessingApplication {
    public static void main(String[] args) {
        Config config = ConfigFactory.load("application");

        ActorSystem system = ActorSystem.create("DemoProcessingCluster", config);
        ActorRef hello = system.actorOf(Props.create(HelloActor.class), "hello");

        System.out.println(hello.path());
    }
}
