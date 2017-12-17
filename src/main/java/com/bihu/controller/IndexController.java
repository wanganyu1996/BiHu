package com.bihu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wanganyu on 2017/12/16.
 */
@Controller
public class IndexController {
    private static final Logger logger= LoggerFactory.getLogger(IndexController.class);
    @RequestMapping(path={"/","/index"},method = {RequestMethod.GET, RequestMethod.POST})
    public String index(){
    ///
      //  System.out.println("123");
        return "home";
    }

}
