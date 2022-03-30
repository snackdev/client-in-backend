package org.example.bar;

import lombok.extern.slf4j.Slf4j;
import org.example.foo.client.FooClient;
import org.example.foo.client.config.EnableFooClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Slf4j
@EnableFooClient
@SpringBootApplication
public class BarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarApplication.class, args);
    }

    @Bean
    @Profile("dev-client")
    public String testDevClient(FooClient fooClient) {
        log.info("fooClient.actionA() : {} ", fooClient.actionA());
        return "testDevClient";
    }

    @Bean
    @Profile("prod-client")
    public String testProdClient(FooClient fooClient) {
        log.info("fooClient.actionA() : {} ", fooClient.actionA());
        return "testProdClient";
    }
}
