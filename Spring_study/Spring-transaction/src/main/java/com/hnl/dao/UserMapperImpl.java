package com.hnl.dao;

import com.hnl.pojo.User;

import org.mybatis.spring.support.SqlSessionDaoSupport;


import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {


    @Override
    public List<User> getUserList() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        mapper.addUser(new User(8,"dwq","ghjghj"));
        mapper.deleteUser(5);
        return mapper.getUserList();
    }

    @Override
    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
