package org.demo.processing.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.demo.processing.message.HelloFor;
import org.demo.processing.message.SayHelloTo;

public class HelloActor extends UntypedActor {
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof SayHelloTo) {
            SayHelloTo m = (SayHelloTo) message;
            log.info("Please say hello to '{}'", m.getTo());

            getSender().tell(new HelloFor("Hello " + m.getTo()), getSelf());
            return;
        }

        unhandled(message);
    }
}
