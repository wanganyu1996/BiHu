package com.bihu.controller;

import com.bihu.dao.UserDao;
import com.bihu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by wanganyu on 2017/12/17.
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;
    /**
     * 注册功能实现
     *
     * @return
     */
    @RequestMapping(path = {"/register"}, method = RequestMethod.POST)
    public  String register(String username, String password, String email) {

        Map<String,Object> map=userService.register(username,password,email);
        System.out.println(username + " " + email + " " + password);
        return "register";
    }

    /**
     * 显示注册页面
     *
     * @return
     */
    @RequestMapping(path = {"/reg"}, method = RequestMethod.GET)
    public static String registerView() {
        return "register";
    }

}
