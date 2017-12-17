package com.bihu;

import com.bihu.dao.UserDao;
import com.bihu.model.User;
import com.bihu.util.BiHuUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by wanganyu on 2017/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=BiHuApplication.class)
public class TestDataBase {
    @Autowired
    UserDao userDao;

    @Test
    public void contextLoads(){
        Random random=new Random();
       for(int i=0;i<11;i++){
           User user=new User();
           user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
           user.setUsername(String.format("user%d",i+1));
           String salt= UUID.randomUUID().toString().substring(0,6);
           user.setSalt(salt);
           user.setPassword(BiHuUtil.MD5("123"+salt));
           user.setEmail("123@qq.com");
           user.setCreatedTime(new Date().getTime());
           user.setActivationCode(BiHuUtil.getRandomCode());
           userDao.insertUser(user);
       }
    }
}
