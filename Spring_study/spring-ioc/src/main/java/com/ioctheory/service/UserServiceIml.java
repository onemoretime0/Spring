package com.ioctheory.service;

import com.ioctheory.dao.UserDao;

public class UserServiceIml implements UserService {

    private UserDao userDao;
    //利用setter实现值的动态注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        //业务层调用Dao层，获获取用户信息
        userDao.getUser();
    }
}
