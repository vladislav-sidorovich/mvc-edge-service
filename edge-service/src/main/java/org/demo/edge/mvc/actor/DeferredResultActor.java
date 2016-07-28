package org.demo.edge.mvc.actor;

import akka.actor.UntypedActor;
import org.springframework.web.context.request.async.DeferredResult;

public class DeferredResultActor extends UntypedActor {
    private final DeferredResult<Object> deferredResult;

    public DeferredResultActor(DeferredResult<Object> deferredResult) {
        this.deferredResult = deferredResult;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        deferredResult.setResult(message);
    }
}
