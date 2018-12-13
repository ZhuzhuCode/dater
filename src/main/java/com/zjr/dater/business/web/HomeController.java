package com.zjr.dater.business.web;

import com.zjr.dater.business.model.User;
import com.zjr.dater.business.service.UserService;
import com.zjr.dater.common.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhujr on 2018/9/18.
 * 主页--导航
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    private UserService userService;

    /**
     * 登录页
     * @return
     */
    @RequestMapping(value = "/")
    public String login(){
        return "login";
    }

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value = "/home")
    public String toHome(){
        //判断是否登录
        User u = SessionUtils.getUser();
        if(u!=null){
            return "index";
        }else {
            return "login";
        }
    }

    /**
     * 待办事项
     * @return
     */
    @RequestMapping(value = "toDo")
    public String  toDo(Model model){
        List<User> userList = userService.findAllUser();
        model.addAttribute("userList",userList);
        return "business/toDoList";
    }

    /**
     * 用户列表
     * @return
     */
    @RequestMapping(value = "toUser")
    public String toUser(){
        return "business/userList";
    }

    /**
     * 无权限页面
     */
    @RequestMapping(value = "/403")
    public String toUnauthorizePage(){
        return "unauthorize";
    }
}
