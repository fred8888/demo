package com.example.demo;

import com.example.demo.config.UserConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@EnableConfigurationProperties(UserConfig.class)
@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserConfig userConfig;

    @Test
    public void propsTest() {
        System.out.println("user: " + userConfig.getName());
        System.out.println("pass: " + userConfig.getPassword());
    }

}
