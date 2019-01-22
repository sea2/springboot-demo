package com.yijiupi.login.controller;

import com.yijiupi.login.entity.User;
import com.yijiupi.login.service.UserService;
import com.yijiupi.login.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import static com.yijiupi.login.constant.PageConstant.*;

/**
 * @Author:吴宸煊
 * @Date: Created in 13:38 2018/1/17
 * @Description:用户controller
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description:进入登陆页面
     * @param:
     * @return:String
     */
    @GetMapping
    public String loginIndex() {
        return LOGIN;
    }

    /**
     * @Description:用户登陆
     * @param: name
     * @param:password
     * @param:session
     * @param:attributes
     * @return:String
     */
    @RequestMapping("/login")
    public String login(@RequestParam String name,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        // 判断用户名是否为空
        boolean checkName = (null == name || "".equals(name));
        // 判断密码是否为空
        boolean checkPassord = (null == password || "".equals(password));
        if (checkName || checkPassord) {
            attributes.addFlashAttribute("nullNameAndPassword", "用户名或密码不能为空");
            return REDIRECT;
        }
        // 在数据库中查找
        String md5Str = MD5Util.MD5(password);
        User user = userService.getUser(name, md5Str);
        if (null == user) {
            attributes.addFlashAttribute("message", "用户名或者密码错误");
            return REDIRECT;
        }
        session.setAttribute("user", user);
        return INDEX;
    }


    /**
     * @Description:进入注册页面
     * @param:
     * @return:String
     */
    @RequestMapping("/toRegist")
    public String insert() {
        return REGIST;
    }

    /**
     * @Description:用户注册信息
     * @param: userPO
     * @return:String
     */
    @RequestMapping("/regist")
    public String save(User userPO, RedirectAttributes attributes) {
        // 获取用户名
        String name = userPO.getName();
        User user = userService.getName(name);
        if (null != user) {
            attributes.addFlashAttribute("mess", "用户名已被注册");
            return REDIRECTTO;
        }
        String passwordStr = MD5Util.MD5(userPO.getPassword());
        userPO.setPassword(passwordStr);
        try {
            userService.save(userPO);
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("message", ""+ e.getMessage());
            return REDIRECT;
        }
        return SUCCESS;
    }
}

