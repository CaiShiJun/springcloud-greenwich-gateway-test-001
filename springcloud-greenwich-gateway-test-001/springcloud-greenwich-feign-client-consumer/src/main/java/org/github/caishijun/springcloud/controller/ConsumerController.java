package org.github.caishijun.springcloud.controller;

import org.github.caishijun.springcloud.service.FeignExampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private FeignExampleService feignExampleService;

    @GetMapping("/feignClient/{name}")
    public String feignClient(@PathVariable("name") String name) throws Exception {

        Thread.sleep(300);

        return feignExampleService.hello(name);
    }

}