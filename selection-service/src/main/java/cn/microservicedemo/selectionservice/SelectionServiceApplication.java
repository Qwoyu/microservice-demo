package cn.microservicedemo.selectionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients

public class SelectionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelectionServiceApplication.class, args);
    }

}
