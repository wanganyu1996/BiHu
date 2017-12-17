package com.bihu.service;

import com.bihu.dao.UserDao;
import com.bihu.model.User;
import com.bihu.util.BiHuConstant;
import com.bihu.util.BiHuUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by wanganyu on 2017/12/17.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;
    public Map<String,Object> register(String username,String password,String email){
       Map<String,Object> map=new HashMap<String, Object>();
       if(StringUtils.isBlank(username)){
           map.put("msg","用户名不能为空！");
           return map;
       }
       if(userDao.selectCountByEmail(email)!=0){
           map.put("msg","该邮箱已被注册！");
                   return map;
       }
       User user=new User();
       String salt= BiHuUtil.getRandomCode();
       user.setSalt(salt);
       user.setPassword(BiHuUtil.MD5(password+salt));
       user.setUsername(username);
       String activationCode= BiHuUtil.getRandomCode();
       user.setActivationCode(activationCode);
       Random random=new Random();
       user.setHeadUrl(String.format(BiHuConstant.head_url,random.nextInt(1000)));
       userDao.insertUser(user);
       //发送激活邮件,用线程

      // user.setActivationCode(BiHuConstant.activate_url+"?activationCode="+activationCode+"&email="+email);


       return map;
    }

}
