package com.zjr.dater.business.web;

import com.zjr.dater.business.model.User;
import com.zjr.dater.business.service.UserService;
import com.zjr.dater.common.ResultObj;
import com.zjr.dater.common.utils.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * Created by zhujr on 2018/10/15.
 * 登录模块
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    private static final String LOGIN_INDEX = "/dater/home";

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "loginIn")
    public ResultObj loginIn(String name,String password,Boolean rememberMe){
        ResultObj re = new ResultObj();
        if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)){
//            UsernamePasswordToken token = new UsernamePasswordToken(name,password,rememberMe);
//            SecurityUtils.getSubject().login(token);
            User user = new User();
            user.setName(name);user.setPassword(password);
            logger.info("校验用户身份存在");
            int result = userService.checkUser(user);
            if(result>0){
                re.setCode(ResultObj.CodeC.SUCCESS);
                re.setData(LOGIN_INDEX);
                re.setMsg("登录成功！");
                //将当前登录用户写入session
                user = userService.getUserByName(name);
                logger.info("将当前用户写入session");
                SessionUtils.setUser(user);
                //休息用户最新登录时间
                user.setOperatetime(new Date());
                userService.updateUser(user);
            }else {
                re.setCode(ResultObj.CodeC.FAIL);
                re.setMsg("登陆名或是密码错误");
            }
        }else {
            re.setCode(ResultObj.CodeC.FAIL);
            re.setMsg("未获取到姓名或是密码");
        }
        return re;
    }

    /**
     * 注销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginOut")
    public ResultObj loginOut(){
        ResultObj re = new ResultObj();
        logger.info("用户[{}]登出",SessionUtils.getUser().getName());
        SessionUtils.removeUser();
        re.setCode(ResultObj.CodeC.SUCCESS);
        re.setData("/dater/");
        return re;
    }
}
