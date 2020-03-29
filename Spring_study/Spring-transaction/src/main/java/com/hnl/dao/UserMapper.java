package com.hnl.dao;

import com.hnl.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();

    //添加一个用户
    int addUser(User user);
    //删除一个用户
    int deleteUser(int id);
}
