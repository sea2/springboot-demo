package com.yijiupi.login.dao;

import com.yijiupi.login.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @Author:吴宸煊
 * @Date: Created in 13:39 2018/1/17
 * @Description:用户dao接口
 */
@Mapper
@Repository
public interface UserDao {


    /**
     * @Description:用户注册
     * @param: user
     * @return:
     */
    void insert(User user);


    /**
     * @Description:用户登陆
     * @param:name
     * @param:password
     * @return:UserPO
     */
    User getUser(@Param(value = "name") String name, @Param(value = "password") String password);


    /**
     * @Description:通过用户名进行查找
     * @param: name
     * @return:User
     */
    User getName(String name);


}
