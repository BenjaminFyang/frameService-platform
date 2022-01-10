package com.example.nacosConfig.controller;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class JasyptTest {

    @Resource
    private StringEncryptor encryptor;

    @Test
    public void encode() {
        String password = "joyowo";
        System.out.println(encryptor.encrypt(password));
    }
}
