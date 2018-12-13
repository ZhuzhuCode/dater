package com.zjr.dater.business.service.Impl;

import com.zjr.dater.business.dao.UserMapper;
import com.zjr.dater.business.domain.UserOption;
import com.zjr.dater.business.model.User;
import com.zjr.dater.business.service.UserService;
import com.zjr.dater.common.utils.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zhujr on 2018/9/21.
 * 用户服务类
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    //缓存key
    public static final String USER_ALL_KEY = "\"user_all\"";
    //value属性标识使用哪个缓存策略，缓存策略在ehcache
    public static final String CACHE_NAME = "department";

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询
     * @param userOption
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<User> queryUserList(UserOption userOption,int pageNum,int pageSize){
        userOption.setCount(pageSize);
        userOption.setStart((pageNum-1)*pageSize);
        userOption.setEndrow(pageNum * pageSize);
        return userMapper.queryUserList(userOption);
    }

    @Override
    public int selectCount() {
        return userMapper.selectCount();
    }

    /**
     * 获取全部用户信息
     * @return
     */
    public List<User> findAllUser(){
        return userMapper.findAllUser();
    }

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    public User getUserById(long id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {
        user.setCreatetime(new Date());
        user.setOperatetime(new Date());
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(User user) {
        user.setOperatetime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public int deleteById(long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 用户密码登录（检验用户身份存在）
     * @param user
     * @return
     */
    public int checkUser(User user) {
        if(StringUtils.isNotBlank(user.getName())){
            return userMapper.checkUser(user);
        }
        return 0;
    }

    /**
     * 通过姓名获取用户信息
     * @return
     */
    public User getUserByName(String name) {
        return userMapper.getUerByName(name);
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @Override
    public User getCurrUser() {
        return SessionUtils.getUser();
    }

}
