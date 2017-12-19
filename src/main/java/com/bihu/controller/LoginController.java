package com.bihu.controller;

import com.bihu.service.UserService;
import com.bihu.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public  Response register(String username, String password, String email) {
        Map<String,Object> map=userService.register(username,password,email);
        if(map.get("ok")!=null){
            return new Response(0,"注册成功，激活邮件已发送,请尽快激活！");
        }else{
            return new Response(1,"error",map);
        }
    }



    /**
     * 显示注册页面
     *
     * @return
     */
    @RequestMapping(path = {"/reg"}, method = RequestMethod.GET)
    public static String registerView(){
        return "register";
    }
    /**
     * 显示登录页面
     *
     * @return
     */
    @RequestMapping(path = {"/log"}, method = RequestMethod.GET)
    public static String loginView() {
        return "register";
    }

    /**
     * 注册激活功能
     * @param code
     * @return
     */
    @RequestMapping("/activate")
    public String activate(String code){
        userService.activate(code);
        return "redirect:/log#activateSuccess";
    }
}
