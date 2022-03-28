package com.example.orderservice.service.impl;

import com.central.common.model.User;
import com.example.orderservice.mapper.UserMapper;
import com.example.orderservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User get(String nick) {

        log.info("UserServiceImpl2222222222");
        List<User> users = userMapper.get();

        return users.stream().filter(user -> user.getNick().equals(nick)).findFirst().orElse(null);

    }
}
