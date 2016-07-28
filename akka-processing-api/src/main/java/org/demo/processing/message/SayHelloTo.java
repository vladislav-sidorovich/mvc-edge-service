package org.demo.processing.message;

import java.io.Serializable;

public class SayHelloTo implements Serializable {
    private final String to;

    public SayHelloTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "SayHelloTo{" +
                "to='" + to + '\'' +
                '}';
    }
}
