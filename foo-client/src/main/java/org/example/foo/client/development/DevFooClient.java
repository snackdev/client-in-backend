package org.example.foo.client.development;

import org.example.foo.client.FooClient;

public class DevFooClient implements FooClient {
    @Override
    public String actionA() {
        return "OK";
    }
}
