package com.library.eroge.erogelib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ErogelibApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErogelibApplication.class, args);
    }

}
