package com.bihu.service;

import com.bihu.async.EventModel;
import com.bihu.async.EventProducer;
import com.bihu.async.EventType;
import com.bihu.dao.UserDao;
import com.bihu.model.User;
import com.bihu.util.BiHuConstant;
import com.bihu.util.BiHuUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wanganyu on 2017/12/17.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDao userDao;

    @Autowired
    EventProducer eventProducer;
    public Map<String,Object> register(String username,String password,String email){
       Map<String,Object> map=new HashMap<String, Object>();
       //邮箱校验
        Pattern p= Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
        Matcher m=p.matcher(email);
        if(!m.matches()){
            map.put("regi-email-error","邮箱格式不正确！");
            return map;
        }
        if(StringUtils.isBlank(username)){
           map.put("regi-username-error","用户名不能为空！");
           return map;
       }
       if(userDao.selectCountByEmail(email)!=0){
           map.put("regi-email-error","该邮箱已被注册！");
           return map;
       }
       User user=new User();
       String salt= UUID.randomUUID().toString().substring(0,6);
       user.setSalt(salt);
       user.setPassword(BiHuUtil.MD5(password+salt));
       user.setUsername(username);
       user.setEmail(email);
       user.setCreatedTime(new Date().getTime());
       String activationCode= BiHuUtil.getRandomCode();
       user.setActivationCode(activationCode);
       Random random=new Random();
       user.setHeadUrl(String.format(BiHuConstant.HEAD_URL,random.nextInt(1000)));
       userDao.insertUser(user);
       //  将邮件发送添加到消息队列中
        eventProducer.fireEvent(new EventModel(EventType.MAIL).setExt("username",username)
                .setExt("email",email).setExt("code",activationCode));
        logger.info("注册成功！");
        map.put("ok","注册成功！");
        return map;
    }

    public void  activate(String activationCode){
      userDao.updateActivationStatusByActivationCode(activationCode);
    }

}
