package com.bihu.async.handler;

import com.bihu.async.EventHandler;
import com.bihu.async.EventModel;
import com.bihu.async.EventType;
import com.bihu.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanganyu on 2017/12/19.
 */
@Component
public class ActivateHandler implements EventHandler {
    @Autowired
    MailSender mailSender;
    @Override
    public void doHandle(EventModel eventModel) {
        mailSender.sendMail(eventModel.getExt("username"),eventModel.getExt("email"),eventModel.getExt("code"),1);

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.MAIL);
    }
}
