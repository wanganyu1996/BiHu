package com.bihu.async;

import com.alibaba.fastjson.JSON;
import com.bihu.util.JedisAdapter;
import com.bihu.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanganyu on 2017/12/18.
 */
public class EventConsumer implements InitializingBean,ApplicationContextAware{
    private static final Logger logger= LoggerFactory.getLogger(EventConsumer.class);

    private ApplicationContext applicationContext;
    private Map<EventType,List<EventHandler>> config=new HashMap<EventType,List<EventHandler>>();

    @Autowired
    JedisAdapter jedisAdapter;
    @Override
    public void afterPropertiesSet() throws Exception {
      Map<String,EventHandler> beans=applicationContext.getBeansOfType(EventHandler.class);
      if(beans!=null){
          for(Map.Entry<String,EventHandler> entry:beans.entrySet()){
              List<EventType> eventTypes=entry.getValue().getSupportEventTypes();
              for(EventType type:eventTypes){
                  if(!config.containsKey(type)){
                      config.put(type,new ArrayList<EventHandler>());
                  }
                  config.get(type).add(entry.getValue());
              }
          }
      }
      Thread thread=new Thread(new Runnable() {
          @Override
          public void run() {
             while(true){
                 String key= RedisKeyUtil.getEventQueue();
                 List<String> events=jedisAdapter.brpop(0,key);
                 for(String message:events){
                   if(message.equals(key)){
                       continue;
                   }
                   EventModel eventModel= JSON.parseObject(message,EventModel.class);
                   if(!config.containsKey(eventModel.getType())){
                        logger.error("不能识别的事件");
                        continue;
                   }
                   for(EventHandler eventHandler:config.get(eventModel.getType())){
                      eventHandler.doHandle(eventModel);
                   }
             }
          }
      }});
        thread.start();
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}