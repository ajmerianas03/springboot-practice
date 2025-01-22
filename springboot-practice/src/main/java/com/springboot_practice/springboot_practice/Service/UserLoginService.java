package com.springboot_practice.springboot_practice.Service;

import com.springboot_practice.springboot_practice.Model.User;

import java.util.Map;

public interface UserLoginService {
    User register(User user);

    Map<String, Object> verify(User user);
}
