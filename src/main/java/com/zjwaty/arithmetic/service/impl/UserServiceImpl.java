package com.zjwaty.arithmetic.service.impl;

import com.zjwaty.arithmetic.entity.User;
import com.zjwaty.arithmetic.service.UserService;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_FILE = "data/users/user.dat";

    @Override
    public boolean register(User user) {
        try {
            // 检查用户是否已存在
            if (userExists(user.getUsername())) {
                return false;
            }

            // 创建目录
            Files.createDirectories(Paths.get("data/users"));

            // 写入用户信息
            try (FileWriter fw = new FileWriter(USER_FILE, true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(user.getUsername() + "," + user.getPassword());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean login(User user) {
        try {
            if (!Files.exists(Paths.get(USER_FILE))) {
                return false;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2 &&
                            parts[0].equals(user.getUsername()) &&
                            parts[1].equals(user.getPassword())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean userExists(String username) {
        try {
            if (!Files.exists(Paths.get(USER_FILE))) {
                return false;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(username)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}