package org.github.caishijun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

// tag::code[]
@SpringBootApplication
@RestController
public class GatewayPredicateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayPredicateApplication.class, args);
    }

}