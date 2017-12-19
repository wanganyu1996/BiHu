package com.bihu.async;

import java.util.List;

/**
 * Created by wanganyu on 2017/12/18.
 */
public interface EventHandler {
   void doHandle(EventModel eventModel);
   List<EventType> getSupportEventTypes();

}
