package org.github.caishijun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * 暂时跳过-待完成
 * 来源网址：https://blog.csdn.net/forezp/article/details/85057268
 */
@SpringBootApplication
@RestController
public class GatewayFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayFilterApplication.class, args);
    }

    @Bean
    public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }
}