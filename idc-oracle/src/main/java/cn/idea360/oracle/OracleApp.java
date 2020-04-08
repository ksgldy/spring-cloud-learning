package cn.idea360.oracle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.idea360.oracle.dao")
@SpringBootApplication
public class OracleApp {

    public static void main(String[] args) {
        SpringApplication.run(OracleApp.class, args);
    }

}
