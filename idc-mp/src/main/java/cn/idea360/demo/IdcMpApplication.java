package cn.idea360.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "cn.idea360.demo.modules.mp.mapper")
public class IdcMpApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdcMpApplication.class, args);
    }

}
