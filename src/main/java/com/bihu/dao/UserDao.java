package com.bihu.dao;
import org.apache.ibatis.annotations.*;
import com.bihu.model.User;

/**
 * Created by wanganyu on 2017/12/17.
 */
@Mapper
public interface UserDao {
   public int insertUser(User user);
   public int selectCountByEmail(String email);
}
