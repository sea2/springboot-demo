package com.yijiupi.login.service;

import com.yijiupi.login.entity.User;

/**
 * @Author:吴宸煊
 * @Date: Created in 13:42 2018/1/17
 * @Description:用户service接口
 */
public interface UserService {
    /**
     * @Description:用户注册
     * @param: userPO
     * @return:
     */
    void save(User user);
    /**
     * @Description:用户登陆
     * @param: name
     * @param:password
     * @return:UserPO
     */
    User getUser(String name, String password);
    /**
     * @Description:通过用户名进行查找
     * @param: name
     * @return:UserPO
     */
    User getName(String name);


}
