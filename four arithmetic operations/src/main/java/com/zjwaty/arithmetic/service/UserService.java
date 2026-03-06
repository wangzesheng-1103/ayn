package com.zjwaty.arithmetic.service;

import com.zjwaty.arithmetic.entity.User;

public interface UserService {
    boolean register(User user);
    boolean login(User user);
    boolean userExists(String username);
}