package com.bihu;

import com.bihu.dao.UserDao;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wanganyu on 2017/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=BiHuApplication.class)

public class Test {
    UserDao userDao;

    @org.junit.Test
    public void contextLoads(){

    }
}
