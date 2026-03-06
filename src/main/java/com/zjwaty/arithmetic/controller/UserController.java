package com.zjwaty.arithmetic.controller;

import com.zjwaty.arithmetic.entity.User;
import com.zjwaty.arithmetic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(User user, Model model) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            model.addAttribute("error", "用户名不能为空");
            return "register";
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            model.addAttribute("error", "密码长度不能少于6位");
            return "register";
        }

        if (userService.userExists(user.getUsername())) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }

        if (userService.register(user)) {
            model.addAttribute("message", "注册成功，请登录");
            return "login";
        } else {
            model.addAttribute("error", "注册失败，请重试");
            return "register";
        }
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(User user, Model model, HttpSession session) {
        if (userService.login(user)) {
            session.setAttribute("currentUser", user.getUsername());
            return "redirect:/exercise/main";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "UserController is working!";
    }

    @RestController
    @RequestMapping("/api")
    public class TestController {

        @GetMapping("/hello")
        public String hello() {
            return "Hello World";
        }
    }
}