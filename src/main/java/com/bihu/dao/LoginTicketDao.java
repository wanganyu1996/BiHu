package com.bihu.dao;

import com.bihu.model.LoginTicket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wanganyu on 2017/12/20.
 */
@Mapper
public interface LoginTicketDao {
    public int addLoginTicket(LoginTicket loginTicket);
    public LoginTicket selectLoginTicketByTicket(@Param("ticket") String ticket);
    public void updateStatusByTicket(@Param("ticket") String ticket,@Param("status") int status);
}
