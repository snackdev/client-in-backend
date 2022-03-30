package com.example.foo.domain;

import org.example.foo.client.production.FooClientUrlDictionary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FooClientUrlDictionary.prefix)
public class ClientResource {
    @PostMapping(FooClientUrlDictionary.actionA)
    public String actionA() {
        return "actionA";
    }
}
