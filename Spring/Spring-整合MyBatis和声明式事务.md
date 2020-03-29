# 1.整合MyBatis



## 3.1 Spring整合MyBatis操作步骤

- 导入相关的jar包
	- mybatis
	- MySQL数据库
	- spring相关
	- mybatis-spring
- 编写配置文件

相关的jar包：

```xml
<dependencies>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.18</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.4</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.4.RELEASE</version>
        </dependency>
        <!--spring连接数据库还需要一个spring-jdbc的包-->
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.4.RELEASE</version>
        </dependency>
    <!--spring -aop织入包:-->    
    <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.5</version>
        </dependency>
    <!--mybatis-spring:-->   
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.4</version>
        </dependency>

    </dependencies>
```



## 3.2 整合

 在项目中引入相关的依赖之后，像MyBatis那这样操作就可以了。

# 2.myBatis-spring



## 3.1 概述

**什么是MyBatis-Spring？**

- MyBatis-Spring 会帮助你将 MyBatis 代码无缝地整合到 Spring 中。
- 它将允许 MyBatis 参与到 Spring 的事务管理之中，创建映射器 mapper 和 `SqlSession` 并注入到 bean 中，以及将 Mybatis 的异常转换为 Spring 的 `DataAccessException`。
- 最终，可以做到应用代码不依赖于 MyBatis，Spring 或 MyBatis-Spring。

**版本：**

MyBatis-Spring 需要以下版本：

| MyBatis-Spring | MyBatis | Spring 框架 | Spring Batch | Java    |
| :------------- | :------ | :---------- | :----------- | :------ |
| 2.0            | 3.5+    | 5.0+        | 4.0+         | Java 8+ |
| 1.3            | 3.4+    | 3.2.2+      | 2.1+         | Java 6+ |

## 3.2 SqlSessionTemplate

- `SqlSessionTemplate` 是 MyBatis-Spring 的核心。作为 `SqlSession` 的一个实现，这意味着可以使用它无缝代替你代码中已经在使用的 SqlSession。
- SqlSessionTemplate是线程安全的，可以被多个 DAO 或映射器所共享使用。
- 当调用 SQL 方法时（包括由 `getMapper()` 方法返回的映射器中的方法），`SqlSessionTemplate` 将会保证使用的 `SqlSession` 与当前 Spring 的事务相关。
- 它管理 session 的生命周期，包含必要的关闭、提交或回滚操作。另外，它也负责将 MyBatis 的异常翻译成 Spring 中的 `DataAccessExceptions`。

可以使用 `SqlSessionFactory` 作为构造方法的参数来创建 `SqlSessionTemplate` 对象。

```xml
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
  <constructor-arg index="0" ref="sqlSessionFactory" />
</bean>
```



## 3.3 在Spring中使用myBatis-spring

要使用 MyBatis-Spring 模块，只需要在类路径下包含 `mybatis-spring-2.0.4.jar` 文件和相关依赖即可

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.4</version>
</dependency>
```

==**1.编写数据源和SqlSessionFactory**==

首先在spring配置文件中配置数据源DataSource和SqlSessionFactory，既然在Spring中配置了数据源那么在MyBatis核心配置文件mybatis-config.xml就不需要再配置数据源了，就可以删掉<environments>标签了。

**spring-mybatis.xml配置数据源DataSource和SqlSessionFactory：**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--DataSource：使用Spring的数据源替换MyBatis的配置 （也可以使用共c3p0,dbcp . druid）
       这里使用spring提供的JDBC:org.springframework.jdbc.datasource.DriverManagerDataSource
   -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf8&amp;useSSL=false&amp;serverTimezone=UTC&amp;rewriteBatchedStatements=true"/>
        <property name="username" value="root"/>
        <property name="password" value="MySQLadmin"/>
    </bean>
    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!--绑定MyBatis配置文件:此配置完全可以代替mybatis-config.xml-->
        <property name="mapperLocations" value="classpath*:com/hnl/dao/*.xml"/>
        <!--配置mapper-->
        <property name="mapperLocations" value="classpath:com/hnl/dao/UserMapper.xml"/>
    </bean>
```

配置完这些之后，mybatis-config.xml中需要配置的东西已经所剩无几了,当然也可以完全不使用mybatis-config.xml进行配置。为了方便setting和typeAliases别名需要在mybatis-config.xml配置一下，当然也可以在spring-mybatis.xml中配置：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
    <typeAliases>
        <package name="com.hnl.pojo"/>
    </typeAliases>

</configuration>
```

==**2.配置SqlSessionTemplate**==

```xml
 <!--配置SqlSessionTemplate ：相当于MyBatis中的SqlSession对象
        org.mybatis.spring.SqlSessionTemplate
    -->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能使用构造器注入sqlSessionFactory ，因为SqlSessionTemplate没有对应的 set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
```

==**3.实现Mapper接口**==

与MyBatis不同的是，Spring为了面向对象思想Mapper接口需要一个实现类，MapperImpl需要维护一个SqlSessionTemplate对象。

```java
public class UserMapperImpl implements UserMapper {

    //我们所有操作，原来都使用SqlSession来执行，现在都使用SqlSessionTemplate来执行
    private SqlSessionTemplate sqlSessionTemplate;

    //使用set方法实现注入
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
```

==**4.将MapperImpl注入到Spring中**==

```xml
    <!--将UserMapperImpl交给Spring来管理-->
    <bean id="userMapper" class="com.hnl.dao.UserMapperImpl">
        <property name="sqlSessionTemplate" ref="sqlSession"/>
    </bean>
```

==**5.Test**==

将spring-mybatis.xml引用到applicationContext.xml中：

```xml
<import resource="spring-dao.xml"/>
```

对于Mapper.xml的编写还是与在MyBatis中相同在Mapper接口相同的包下配置：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.hnl.dao.UserMapper">
    <select id="getUserList" resultType="User">
        select * from user;
    </select>
</mapper>
```



```java
  @Test
    public void getUserList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
/*
结果：
User(id=1, name=法外狂徒张三, pwd=123445)
User(id=2, name=王司徒, pwd=126978)
User(id=3, name=诸葛孔明, pwd=12355)
User(id=4, name=佐仓绫音, pwd=12213)
User(id=6, name=诸葛亮, pwd=23456)
User(id=7, name=凉宫春日, pwd=asdgh2)
*/
```

## 3.4SqlSessionDaoSupport

`SqlSessionDaoSupport` 是一个抽象的支持类，用来为你提供 `SqlSession`。调用 `getSqlSession()` 方法你会得到一个 `SqlSessionTemplate`，之后可以用于执行 SQL 方法：

```java
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
  public User getUser(String userId) {
    return getSqlSession().selectOne("org.mybatis.spring.sample.mapper.UserMapper.getUser", userId);
  }
}
```

`SqlSessionDaoSupport` 需要通过属性设置一个 `sqlSessionFactory` 或 `SqlSessionTemplate`。如果两个属性都被设置了，那么 `SqlSessionFactory` 将被忽略。

假设类 `UserMapperImpl` 是 `SqlSessionDaoSupport` 的子类，可以编写如下的 Spring 配置来执行设置：

```xml
<bean id="userDao" class="org.mybatis.spring.sample.dao.UserDaoImpl">
  <property name="sqlSessionFactory" ref="sqlSessionFactory" />
</bean>
```

**在项目中使用SqlSessionDaoSupport实现与上面相同的操作：**

在配置完数据源和SqlSessionFactory之后，编写一个类实现Mapper接口，继承SqlSessionDaoSupport抽象类：

```java
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<User> getUserList() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        return mapper.getUserList();
    }
}
```

然后注入到Spring中即可：

```xml
<!--此处的property配置的是userMapper2父类 SqlSessionDaoSupport类的属性-->
<bean id="userMapper2" class="com.hnl.dao.userMapper2">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
</bean>
```



这种方式相较于使用SqlSessionTemplate省略了配置SqlSessionTemplate的步骤。更为简单



# 3.声明式事务



## 3.1 回顾事务

- 要么都成功，要么都失败
- 事务在项目开发中十分的重要，设计到数据一致性的问题
- 确保数据的完整和一致性

**事务的ACID原则：**

- 原子性
- 一致性
- 隔离性
	- 多个业务操作同一个资源，防止数据损坏
- 持久性
	- 事务一旦提交，无论系统发生什么问题，结果不会被影响，被持久化的写到存储器中



## 3.2没有事务管理的数据出错问题



首先我们在UserMapperImpl中执行了一下操作，现在数据中插入了一条数据，紧接着有删除了它：

```java
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {


    @Override
    public List<User> getUserList() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        mapper.addUser(new User(5,"大老师","234234"));
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

```

但是在配置Mapper.xml的时候出错了，SQL书写不正确，这是一定会导致程序报错的：

```xml
<mapper namespace="com.hnl.dao.UserMapper">
    <select id="getUserList" resultType="User">
        select * from user;
    </select>

    <insert id="addUser" parameterType="User" >
        insert into user(id ,name, pwd) values (#{id},#{name},#{pwd});
    </insert>

    <!--在这里，delete语句书写错误-->
    <delete id="deleteUser" parameterType="_int">
        deletes from user where id =#{id};
    </delete>
</mapper>
```

那么在执行的时候会发生什么呢？

```java
    @Test
    public void transactionTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper mapper = context.getBean("userMapperImpl", UserMapper.class);

        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```

结果果然报错了：

```tex
### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'deletes from user where id =5' at line 1
```

虽然报错了，但是addUser方法还是成功的插入数据了，这就导致了数据不一致的情况：

```
1	法外狂徒张三	123445
2	王司徒	126978
3	诸葛孔明	12355
4	佐仓绫音	12213
5	大老师	234234
6	诸葛亮	23456
7	凉宫春日	asdgh2
```

这种情况在spring中可以事务管理



## 3.3 spring声明式事务

- 一个使用 MyBatis-Spring 的其中一个主要原因是它允许 MyBatis 参与到 Spring 的事务管理中。而不是给 MyBatis 创建一个新的专用事务管理器，MyBatis-Spring 借助了 Spring 中的 DataSourceTransactionManager 来实现事务管理。
- 一旦配置好了 Spring 的事务管理器，你就可以在 Spring 中按你平时的方式来配置事务。并且支持 @Transactional 注解和 AOP 风格的配置。在事务处理期间，一个单独的 `SqlSession` 对象将会被创建和使用。当事务完成时，这个 session 会以合适的方式提交或回滚。
- 事务配置好了以后，MyBatis-Spring 将会透明地管理事务。这样在你的 DAO 类中就不需要额外的代码了。



spring中的事务管理分为两类：

- 声明式事务：AOP，不会影响代码
- 编程式事务：需要在代码中进行事务的管理



### 3.3.1 官方文档 配置

要开启 Spring 的事务处理功能，在 Spring 的配置文件中创建一个 `DataSourceTransactionManager` 对象：

```xml
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  <constructor-arg ref="dataSource" />
</bean>
```

```java
@Bean
public DataSourceTransactionManager transactionManager() {
  return new DataSourceTransactionManager(dataSource());
}
```

传入的 `DataSource` 可以是任何能够与 Spring 兼容的 JDBC `DataSource`。包括连接池和通过 JNDI 查找获得的 `DataSource`。

**注意：**为事务管理器指定的 `DataSource` **必须**和用来创建 `SqlSessionFactoryBean` 的是同一个数据源，否则事务管理器就无法工作了。

## 3.4 在项目中配置声明式事务

==**以下配置是基于AOP，在不改变源码的情况下开始声明式事务**==

配置声明式事务：

```xml
<!--配置声明式事务-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
```

使用AOP织入：

```xml
  <!--结合 AOP实现事务的织入 前提是加入tx的依赖：
	xmlns:tx="http://www.springframework.org/schema/tx
	http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx.xsd
	-->
    <!--配置事务通知：-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!--给哪些方法配置事务-->
        <!--配置事务的传播特性：propagation ="REQUIRED"是spring的默认选择 -->
        <tx:attributes>
            <!--给所有的add和所有的delete方法配置事务-->
           <!-- <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete"/>
            <tx:method name="update"/>-->
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>

    <!--配置事务切入-->
    <aop:config>
        <aop:pointcut id="txPonitCut" expression="execution(* com.hnl.dao.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPonitCut"/>
    </aop:config>
</beans>
```



然后再次测试：

```java
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
/*
依然会报错，### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'deletes from user where id =5' at line 1
*/
```

**但是在数据库中没有插入新的数据也没有删除数据。**

那么修改一下错误的SQL:

```xml
<delete id="deleteUser" parameterType="_int">
    delete from user where id =#{id};
</delete>
```

再次测试：

```tex
2	王司徒	126978
3	诸葛孔明	12355
4	佐仓绫音	12213
6	诸葛亮	23456
7	凉宫春日	asdgh2
8	dwq	ghjghj
```

发现删除了id=5 的记录，添加了一条id=8的记录

## 3.5 小结

**思考**

为什么需要事务？

- 如果不配置事务，可能会存在数据提交不一致的情况
- 如果我们不在spring中配置声明式事务，就需要在代码中手动配置事务
- 事务在项目的开发中十分重要，涉及到数据的一致性和完整性



