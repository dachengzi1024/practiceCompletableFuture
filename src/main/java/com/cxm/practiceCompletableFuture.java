package com.cxm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cxm.dao")
public class practiceCompletableFuture {

    public static void main(String[] args) {
        SpringApplication.run(practiceCompletableFuture.class, args);
    }

}
