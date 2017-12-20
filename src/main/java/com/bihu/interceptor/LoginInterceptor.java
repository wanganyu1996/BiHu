package com.bihu.interceptor;

import com.bihu.dao.LoginTicketDao;
import com.bihu.dao.UserDao;
import com.bihu.model.HostHolder;
import com.bihu.model.LoginTicket;
import com.bihu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by wanganyu on 2017/12/20.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{

    @Autowired
    private LoginTicketDao loginTicketDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String ticket=null;
       if(request.getCookies()!=null){
          for(Cookie cookie:request.getCookies()){
              if(cookie.getName().equals("ticket")){
                  ticket=cookie.getValue();
                  break;
              }
          }
       }
       if(ticket!=null){
           LoginTicket loginTicket=loginTicketDao.selectLoginTicketByTicket(ticket);
           if(loginTicket==null||loginTicket.getExpired()<new Date().getTime()||loginTicket.getStatus()!=0) {
               return true;
           }
           User user=userDao.selectUserById(loginTicket.getUserId());
           hostHolder.setUser(user);
       }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       if(modelAndView!=null&&hostHolder.getUser()!=null){
           modelAndView.addObject("user",hostHolder.getUser());
       }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
      hostHolder.clear();
    }
}
