package cn.idea360.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Gateway2App {

    public static void main(String[] args) {
        SpringApplication.run(Gateway2App.class, args);
    }

}
