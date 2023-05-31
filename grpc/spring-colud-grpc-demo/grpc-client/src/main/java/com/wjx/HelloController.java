package com.wjx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    GrpcClientService grpcClientService;

    @GetMapping("/hello")
    public void hello() {
        grpcClientService.hello();
    }

}