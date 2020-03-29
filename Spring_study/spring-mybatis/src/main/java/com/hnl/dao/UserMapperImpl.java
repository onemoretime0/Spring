package com.hnl.dao;

import com.hnl.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper {

    //我们所有操作，原来都使用SqlSession来执行，现在都使用SqlSessionTemplate来执行
    private SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    //现在所有的操作都移动到实现类里面来做，拿数据只需要调用对应的方法即可
    @Override
    public List<User> getUserList() {
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        return mapper.getUserList();
    }
}
