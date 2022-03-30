package org.example.foo.client.production;

import lombok.RequiredArgsConstructor;
import org.example.foo.client.FooClient;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class ProdFooClient implements FooClient {
    private final WebClient webClient;

    @Override
    public String actionA() {
        return webClient.post()
                .uri(FooClientUrlDictionary.prefix.concat(FooClientUrlDictionary.actionA))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
