package org.demo.processing.message;

import java.io.Serializable;

public class HelloFor implements Serializable {
    private final String hello;

    public HelloFor(String hello) {
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }

    @Override
    public String toString() {
        return "HelloFor{" +
                "hello='" + hello + '\'' +
                '}';
    }
}
