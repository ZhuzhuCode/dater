package com.zjr.dater.business.service;

import com.zjr.dater.business.domain.UserOption;
import com.zjr.dater.business.model.User;

import java.util.List;

/**
 * Created by zhujr on 2018/9/21.
 */
public interface UserService {

    public List<User> queryUserList(UserOption userOption, int pageNum, int pageSize);

    public int selectCount();

    public List<User> findAllUser();

    public User getUserById(long id);

    public int addUser(User user);

    public int updateUser(User user);

    public int deleteById(long id);

    public int checkUser(User user);

    public User getUserByName(String name);

    /**
     * 获取当前登录用户
     */
    public User getCurrUser();

}
