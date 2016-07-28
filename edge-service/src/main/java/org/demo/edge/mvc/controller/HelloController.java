package org.demo.edge.mvc.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.demo.edge.mvc.actor.DeferredResultActor;
import org.demo.processing.message.SayHelloTo;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("api")
@Validated
public class HelloController {
    @Autowired
    @Qualifier("helloRouter")
    private ActorRef helloRouter;

    @Autowired
    private ActorSystem actorSystem;

    @RequestMapping(value = "hello/{name}", method = RequestMethod.POST)
    public DeferredResult<String> sayHello(@PathVariable @NotEmpty String name) {
        DeferredResult<String> result = new DeferredResult<>();
        ActorRef resultActor = actorSystem.actorOf(Props.create(DeferredResultActor.class, result));

        SayHelloTo message = new SayHelloTo(name);
        helloRouter.tell(message, resultActor);

        result.onCompletion(() -> actorSystem.stop(resultActor));
        return result;
    }
}
