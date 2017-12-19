package com.bihu.async;

import com.alibaba.fastjson.JSONObject;
import com.bihu.util.JedisAdapter;
import com.bihu.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanganyu on 2017/12/18.
 */
@Service
public class EventProducer{

    @Autowired
   JedisAdapter jedisAdapter;
   public boolean fireEvent(EventModel eventModel){
       try {
           String json= JSONObject.toJSONString(eventModel);
           String key= RedisKeyUtil.getEventQueue();
           jedisAdapter.lpush(key,json);
           return true;
       }catch (Exception e){
         return false;
       }
   }

}
