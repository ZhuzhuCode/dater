package com.zjr.dater.business.web;

import com.zjr.dater.business.domain.UserOption;
import com.zjr.dater.business.model.User;
import com.zjr.dater.business.service.UserService;
import com.zjr.dater.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhujr on 2018/9/14.
 * 用户管理
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表
     * @param name
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userList")
    public ResultObj getUserList(@RequestParam(required = false,name = "name")String name, int page, int limit) {
        UserOption userOption = new UserOption();
        userOption.setName(name);
        List<User> userList = userService.queryUserList(userOption, page, limit);
        int count = userService.selectCount();
        ResultObj jo = new ResultObj(count, userList);
        return jo;
    }

    /**
     * 获取全部用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAllUser")
    public ResultObj getAllUser(){
        ResultObj re = new ResultObj();
        List<User> users = userService.findAllUser();
        re.setData(users);
        return re;
    }

    /**
     * 跳转至用户新增/编辑页面
     *
     * @param operate
     * @param id
     * @return
     */
    @RequestMapping(value = "toEditUser")
    public String toEditUser(String operate, String id, Model model) {
        User user = new User();
        if (StringUtils.equals(operate, "1") && StringUtils.isNotBlank(id)) {
            user = userService.getUserById(Long.valueOf(id));
        }
        model.addAttribute("user", user);
        return "business/user-edit";
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editUser")
    public ResultObj editUser(User user) {
        ResultObj re = new ResultObj();
        int result = 0;
        String msg = "";
        if (user.getId() != null) {
            msg += "修改";
            result = userService.updateUser(user);
        } else {
            msg += "新增";
            result = userService.addUser(user);
        }
        if (result > 0) {
            re.setCode(ResultObj.CodeC.SUCCESS);
            re.setMsg(msg + "用户成功");
        } else {
            re.setCode(ResultObj.CodeC.FAIL);
            re.setMsg(msg + "用户失败");
        }
        return re;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteById")
    public ResultObj deleteById(String id) {
        ResultObj re = new ResultObj();
        if (StringUtils.isNotBlank(id)) {
            int result = userService.deleteById(Long.valueOf(id));
            if (result > 0) {
                re.setCode(ResultObj.CodeC.SUCCESS);
                re.setMsg("已删除该用户信息");
            } else {
                re.setCode(ResultObj.CodeC.FAIL);
                re.setMsg("删除用户信息失败");
            }
        } else {
            re.setCode(ResultObj.CodeC.FAIL);
            re.setMsg("未获取到用户ID");
        }
        return re;
    }
}
