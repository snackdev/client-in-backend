package org.example.foo.client.config;

import lombok.extern.slf4j.Slf4j;
import org.example.foo.client.FooClient;
import org.example.foo.client.development.DevFooClient;
import org.example.foo.client.production.ProdFooClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@ComponentScan("org.example.foo.client")
public class FooClientConfig {
    @Value("${foo.client.mode:development}")
    private String mode;
    @Value("${foo.client.protocol:http}")
    private String protocol;
    @Value("${foo.client.host:foo}")
    private String host;
    @Value("${foo.client.port:8080}")
    private String port;
    @Value("${foo.client.max-memory-size:104857600}")
    private int maxMemorySize;

    @Bean
    public FooClient getFooClient() {

        if (mode.equals("development")) {
            log.info("Foo Client as Development Mode");
            return new DevFooClient();
        } else {
            String baseUrl = String.format("%s://%s:%s", protocol, host, port);
            log.info("Foo Client as Production Mode with Base Url : {}", baseUrl);
            return new ProdFooClient(getWebClient(baseUrl));
        }
    }

    private WebClient getWebClient(String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(
                        ExchangeStrategies.builder()
                                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(maxMemorySize))
                                .build()
                )
                .build();
    }
}
