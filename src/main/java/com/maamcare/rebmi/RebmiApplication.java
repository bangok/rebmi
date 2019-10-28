package com.maamcare.rebmi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.maamcare.rebmi.dao")
public class RebmiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RebmiApplication.class, args);
    }

}
