package net.abadguy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.abadguy.dao")
public class DemoOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOneApplication.class, args);
    }

}
