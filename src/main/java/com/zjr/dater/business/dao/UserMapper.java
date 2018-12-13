package com.zjr.dater.business.dao;

import com.zjr.dater.business.domain.UserOption;
import com.zjr.dater.business.model.User;
import com.zjr.dater.business.model.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findAllUser();

    List<User> queryUserList(UserOption userOption);

    Integer selectCount();

    int checkUser(User user);

    User getUerByName(@Param("name")String name);
}