package com.ioctheory.dao;

public class UserDaoMySQLIml implements UserDao {
    @Override
    public void getUser() {
        System.out.println("Mysql中返回的用户数据");
    }
}
