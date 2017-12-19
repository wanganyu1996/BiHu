package com.bihu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by wanganyu on 2017/12/18.
 */
@Service
public class JedisAdapter implements InitializingBean {
    private static final Logger logger= LoggerFactory.getLogger(JedisAdapter.class);
    private JedisPool pool;
    @Override
    public void afterPropertiesSet() throws Exception {
      pool=new JedisPool("redis://localhost:6379:9");
    }

    /**
     * 用队列实现生产者消费者模式，在队列头部插入节点
     * @param key
     * @param value
     * @return
     */
    public long lpush(String key,String value){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            return jedis.lpush(key,value);
        } catch (Exception e) {
            logger.error("redis获取连接池异常："+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    return 0;
    }

    /**
     * 从队列尾部拿节点
     * @param timeout
     * @param key
     * @return
     */
    public List<String> brpop(int timeout, String key){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            return jedis.brpop(timeout,key);
        } catch (Exception e) {
            logger.error("redis获取连接池异常："+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }
}
