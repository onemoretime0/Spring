package com.hnl.dao;

import com.hnl.pojo.User;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void getUserList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void SqlSessionDaoSupportTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper2 = context.getBean("userMapper2", UserMapper.class);
        List<User> userList = userMapper2.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
