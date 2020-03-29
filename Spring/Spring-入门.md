# 1.Spring

## 1.1基本介绍

### 1.1.1 简介



Spring是一个轻量级控制反转(IoC)和面向切面(AOP)的容器框架。

- 2002，首次推出了Spring框架的雏形：interface21框架！
- Spring框架即以interface21框架为基础，经过重新设计，并不断丰富内涵，于2004年3月24日，发布了1.0正式版。
- **Rod Johnson**，Spring Framework创始人，著名作者。很难想象其学历，真的让好多人大吃一惊，他是悉尼大学的博士，然而他的专业不是计算机，而是音乐学。
- spring理念：使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架。
- spring目的：解决企业应用开发的复杂性
- spring功能：使用基本的JavaBean代替EJB，并提供了更多的企业应用功能
- **SSH：Struct2+Spring+Hibernate!**
- **SSM：SpringMVC+Spring+Mybatis!**




官网：https://spring.io/projects/spring-framework#overview

官方下载地址：https://repo.spring.io/release/org/springframework/spring/

Github：https://github.com/spring-projects/spring-framework



### 1.1.2 Maven仓库

Maven仓库：导入webmvc包会自动导入相关依赖；jdbc用于和Mybatis整合。

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>
```

创建spring项目的时候导包``org.springframework``会将其他包一起导入：

![](\image\spring基本介绍\导包.png)

### 1.1.3 Spring的优点

- Spring是一个开源的免费的框架（容器）！
- Spring是一个轻量级的、非入侵式的框架！
- 控制反转（IOC）、面向切面编程（AOP）！
- 支持事务的处理，对框架整合的支持！

==总结一句话：Spring就是一个轻量级的控制反转（IOC）和面向切面编程的框架==

## 1.2 Spring组成

![](\image\spring基本介绍\spring组成.png)

功能：

- **核心容器（Spring Core）**
	- 核心容器提供Spring框架的基本功能。Spring以bean的方式组织和管理Java应用中的各个组件及其关系。Spring使用BeanFactory来产生和管理Bean，它是工厂模式的实现。BeanFactory使用控制反转(IoC)模式将应用的配置和依赖性规范与实际的应用程序代码分开

- **应用上下文（Spring Context）**
	- Spring上下文是一个配置文件，向Spring框架提供上下文信息。Spring上下文包括企业服务，如JNDI、EJB、电子邮件、国际化、校验和调度功能

- **Spring面向切面编程（Spring AOP）**
	- 通过配置管理特性，Spring AOP 模块直接将面向方面的编程功能集成到了 Spring框架中。
	- 所以，可以很容易地使 Spring框架管理的任何对象支持 AOP。Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。
	- 通过使用 Spring AOP，不用依赖 EJB 组件，就可以将声明性事务管理集成到应用程序中

- **JDBC和DAO模块（Spring DAO）**
	- JDBC、DAO的抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理，和不同数据库供应商所抛出的错误信息。异常层次结构简化了错误处理，并且极大的降低了需要编写的代码数量，比如打开和关闭链接。

- **对象实体映射（Spring ORM）**
	- 　Spring框架插入了若干个ORM框架，从而提供了ORM对象的关系工具，其中包括了Hibernate、JDO和 IBatis SQL Map等，所有这些都遵从Spring的通用事物和DAO异常层次结构

- **Web模块（Spring Web）**
	- 　Web上下文模块建立在应用程序上下文模块之上，为基于web的应用程序提供了上下文。所以Spring框架支持与Struts集成，web模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。

- **MVC模块（Spring Web MVC）**
	- MVC框架是一个全功能的构建Web应用程序的MVC实现。
	- 通过策略接口，MVC框架变成为高度可配置的。
	- MVC容纳了大量视图技术，其中包括JSP、POI等，模型来有JavaBean来构成，存放于m当中，而视图是一个街口，负责实现模型，控制器表示逻辑代码，由c的事情。
	- Spring框架的功能可以用在任何J2EE服务器当中，大多数功能也适用于不受管理的环境。Spring的核心要点就是支持不绑定到特定J2EE服务的可重用业务和数据的访问的对象，毫无疑问这样的对象可以在不同的J2EE环境，独立应用程序和测试环境之间重用。

## 1.3扩展

spring官网介绍：现代化的Java开发！说白了就是基于Spring的开发

![](\image\spring基本介绍\spring家族.png)

- Spring Boot
	- 一个快速开发的脚手架。
	- 基于Spring Boot可以快速的开发单个微服务。
	- 约定大于配置！
- Spring Cloud
	- SpringCloud是基于SpringBoot实现的。

因为现在大多数公司都在使用SpringBoot进行快速开发，学习SpringBoot的前提，需要完全掌握Spring以及SpringMVC！承上启下的作用。

**弊端：发展了太久之后，违背了原来的理念！配置十分繁琐，人称：“配置地狱”。**

# 2.IOC



## 2.1 理论推导IOC

### 2.1.1传统方式实现业务层调用DAO层

传统方式：

1.UserDao接口

2.UserDaoImpl实现类

3.UserService业务接口

4.UserServiceImpl业务实现类

代码实现:D

```java
//DAO层
public interface UserDao {
    void getUser();
}
public class UserDaoIml implements UserDao {
    public void getUser() {
        System.out.println("获取用户数据");
    }
}
//Service层
public interface UserService {
    void getUser();
}
public class UserServiceIml implements UserService {
    private UserDao userDao =new UserDaoIml();

    public void getUser() {
        //业务层调用Dao层，获获取用户信息
        userDao.getUser();
    }
}
```

分析：

1. 业务层中的``private UserDao userDao =new UserDaoIml();``是写死的，不利于扩展

2. 如果想在Dao层增加一个UserDaoMySqlIml类实现UserDao,那么在业务层调用的时候，就必须修改业务层的代码：——非常不利于扩展

3. ```java
	public class UserServiceIml implements UserService {
	    //private UserDao userDao =new UserDaoIml();
	    private UserDao userDaoMySql=new UserDaoMySQLIml();
	    public void getUser() {
	        //业务层调用Dao层，获获取用户信息
	        userDaoMySql.getUser();
	    }
	}
	```



### 2.1.2在Service中使用setter方法对UserDao进行动态注入

```java
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
```

这样做的好处：

- 之前，程序时主动创建对象，在``new UserServiceIml()``的时候，就创建了UserDao对象，已经在写死了。创建UserDao对象的控制权在程序员手上
- 使用setter方法注入UserDao对象后，程序不再具有主动性，而是变成了被动的接收UserDao对象
- 这就实现了控制反转（IOC）
	- ==由UserDao对象的创建的控制权在Service手上==
	- ==变为了控制权掌握在Service的调用者手上==

这种思想，从本质上解决了问题，程序员不用去管理对象的创建了，系统的==耦合性大大降低==。可以更加专注的在业务的实现上。

这是IOC的原型！！

![](\image\IOC\控制反转（IOC）.png)

### 2.1.3 使用spring 的方式完成上面的IOC

首先Java代码都都不需要变，仅创建并编辑beans.xml将对象的创建交给spring,当需要使用对象的时候，从spring容器中取出即可：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="UserDaoIml" class="com.ioctheory.dao.UserDaoIml"></bean>
    <bean id="UserDaoMySQLIml" class="com.ioctheory.dao.UserDaoMySQLIml"></bean>
    
    <bean id="UserServiceIml" class="com.ioctheory.service.UserServiceIml">
        <property name="userDao" ref="UserDaoIml"/>
    </bean>
</beans>
```

Test:

```java
//使用spring的方式
    @Test
    public void testSpring() {
        ApplicationContext contxt = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceIml userServiceIml = (UserServiceIml) contxt.getBean("UserServiceIml");
        userServiceIml.getUser();
    }
```

结果：![](\image\IOC\spring完成IOC.png)

## 2.2 IOC本质

**控制反转IOC（Inversion of Control）**,是一种设计思想，**DI（依赖注入）**是实现IOC的一种方法，也有人认为DI只是IOC的另一种说法。

没有IOC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制。控制反转后，将对象的创建转移给第三方。

**IoC是spring的核心内容**，使用多种方式完美的实现了IOC，可以使用XML配置，也可以使用注解，新版本的spring也可以零配置实现IOC

spring容器在初始化时先读取配置文件，根据配置文件或元数据创建与组织对象存入容器中，程序使用时再从IOC容器中取出需要的对象

![](\image\IOC\springIOC容器.png)

采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方式是依赖注入（Dependency Injection,DI）**。

# 3.HelloSpring

## 3.1从头开始输出HelloSpring

### 3.1.1创建spring 项目并引入相关jar包

````xml
<!--关键jar包；创建spring项目的时候导包``org.springframework``会将其他包一起导入--> 	
<dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.2.4.RELEASE</version>
    </dependency>
        <!-- 测试使用 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
````

### 3.1.2创建一个类Hello

```java
public class Hello {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }
}
```

### 3.1.3配置元数据

xml基本格式

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>
    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>
    <!-- more bean definitions go here -->
</beans>
```

配置Hello类的元数据

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--使用spring来创建对象,在spring中这些都称为Bean-->
    <bean id="hello" class="com.hello.Hello">
        <property name="str" value="HelloSpring"/> 
    </bean>
</beans>
```

### 3.1.4实例化容器

```java
//spring官网给出的示例
ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
```

Test：

```java
   @Test
    public void testHelloSpring() {
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象现在都在spring中管理了，如果要使用，直接去里面取出来即可
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }
```

结果：![](\image\HelloSpring\HelloSpring.png)

## 3.2 分析

思考问题？

- Hello对象是谁创建的？

	hello对象是由Spring创建的。

- Hello对象的属性是怎么设置的？

	hello对象的属性是由Spring容器设置的。

再看beans.xml：

```xml
 <!--使用spring来创建对象,在spring中这些都称为Bean
    使用Java代码创建对象：
        Type 变量名 =new Type();
    在bean标签中:
        id =变量名
        class = new 的对象
        property 相当于对象中的一个属性设置一个值
        
    -->
    <bean id="hello" class="com.hello.Hello">
        <property name="str" value="HelloSpring"/>
    </bean>
```

这个过程就叫做控制反转：

**控制**：谁来控制对象的创建，传统应用程序的对象是由程序本身控制创建的，使用Spring后，对象是由Spring来创建的。

**反转**：程序本身不创建对象，而变成被动的接收对象。

**依赖注入**：就是利用set方法来进行注入。

**IoC是一种编程思想，由主动的编程编程被动的接收。**

可以通过new ClassPathXmlApplicationContext去浏览一下底层源码。

==**OK，到了现在，我们彻底不用在程序中去改动了，要实现不同的操作，只需要在xml配置文件中进行修改，所谓的IoC，一句话搞定：对象由Spring来创建，管理，装配！**==

## 3.3ApplicationContext继承关系



![](.\image\HelloSpring\ApplicationContext.png)

# 4.IOC创建对象方式

spring创建对象的方式：

- **默认使用无参构造器创建对象**
- **有参构造器构建对象的方法有三种**：
	- 下标赋值
	- 类型赋值
	- 参数名赋值

```java
//实体类中的有参构造器，Java中如果显式的声明了有参的构造器，那么编译器就不会默认构建无参构造器 
public User(String name) {
        this.name = name;
}
```

显式的有参构造器（并没有书写无参构造器）导致beans.xml报错

![](.\image\Ioc创建对象方式\有参构造器导致beans,xml报错.png)



## 4.1spring有参构造器创建对象—下标赋值

```xml
 <!--通过属性下标的方式赋值-->
<bean id="user" class="pojo.User" >
        <constructor-arg index="0" value="法外狂徒张三"/>
    </bean>
```

## 4.2 spring有参构造器创建对象—类型赋值

```xml
 <!--类型赋值方式-->
    <bean id="user" class="pojo.User" >
    <constructor-arg type="java.lang.String" value="类型赋值：法外狂徒张三"/>
    </bean>
```

这种凡是不建议使用，因为一个实体类中可能会有同样的类型多个属性，就会产生冲突。

## 4.3spring有参构造器创建对象—参数名赋值

```xml
<!--直接通过参数名来设置--> 
<bean id="user" class="pojo.User">
        <constructor-arg name="name" value="参数名赋值：法外狂徒张三"/>
    </bean>
```

<constructor-arg>中那么属性就是User构造函数中的形参name，value属性就是初始化的值。

## 总结

**在配置文件加载的时候，容器中管理的对象就已经初始化了！**

# 5.Spring配置说明



## 5.1别名

```xml
 <bean id="user" class="pojo.User">
        <constructor-arg name="name" value="参数名赋值：法外狂徒张三"/>
    </bean>
    <alias name="user" alias="user_alias"/>
```

```java
 @Test
    public void indexTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //User user = (User) context.getBean("user");
        User user = (User) context.getBean("user_alias");
        user.show();
    }
```

```tex
#使用//User user = (User) context.getBean("user");运行结果：
name=参数名赋值：法外狂徒张三
#使用User user = (User) context.getBean("user_alias");运行结果：
name=参数名赋值：法外狂徒张三
```

两个名字都能取出对象



## 5.2Bean配置

```xml
<!--
    id:bean的唯一标识符，相当于我们学的对象名；
    class：bean对象所对应的全限定名：包名+类名；
    name：也是别名，可以同时取多个别名，逗号分割（也可以使用空格分隔）
-->
<bean id="userT" class="com.kuang.pojo.UserT" name="user2,u2">
</bean>
```

## 5.3import配置

这个import，一般用于团队开发使用，他可以将多个配置文件，导入合并为一个。

假设，现在项目中有多个人开发，这三个人负责不同的类开发，不同的类需要注册在不同的bean中，我们可以利用import将所有人的beans.xml合并为一个总的！

将多个xml文件汇总在一个总的applicationContext.xml文件中：

![](.\image\Ioc创建对象方式\import使用.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="beans.xml"/>
    <import resource="beans2.xml"/>
    <import resource="beans3.xml"/>
</beans>
```

这样在获取对象的时候：

``ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");``

只需要创一个参数即可

# 6.依赖注入（DI）

## 6.1构造器注入

就是前面的依赖有参或无参（默认）的构造器初始化的问题

- 无参的构造器是默认的方式
- 有参的构造器需要通过``<constructor-arg>``标签完成注入



## 6.2Set方式注入（重点）

依赖注入：Set注入！

- 依赖：bean对象的创建依赖于容器！
- 注入：bean对象中的所有属性，由容器来注入！

### 6.2.1环境搭建

复杂类型：

```java
public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
```

真是测试对象：

```java
public class Student {

    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbies;
    private Map<String, String> card;
    private Set<String> games;
    private String wife;
    private Properties info;
}
```

applicationContext.xml:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="student" class="pojo.Student">
      <!--第一种普通值注入：直接使用value-->
      <property name="name" value="法外狂徒张三"/>
  </bean>
</beans>
```

Test:

```java
 @Test
    public void Test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.getName());
    }
```

### 6.2.2完善配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="address" class="pojo.Address">
        <property name="address" value="临沂市"/>
    </bean>

    <bean id="student" class="pojo.Student">
        <!--第一种普通值注入：直接使用value-->
        <property name="name" value="法外狂徒张三"/>
        <!--第二种：Bean注入:ref-->
        <property name="address" ref="address"/>
        <!--第三种种：数组注入:<array><value>...</value></array>-->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>不能承受的生命之轻</value>
                <value>黄金时代</value>
            </array>
        </property>
        <!--List注入: <list> <value> ...</value> </list> -->
        <property name="hobbies">
            <list>
                <value>play game</value>
                <value>Anime</value>
            </list>
        </property>
        <!--Map 注入：<map><entry key="" value=""/> </map>-->
        <property name="card">
            <map>
                <entry key="IDcard" value="123456"/>
                <entry key="银行卡" value="65432120"/>
            </map>
        </property>
        <!--Set 注入：<set> <value>...</value>
        <ref bean="myDataSource" /></set>-->
        <property name="games">
            <set>
                <value>刺客信条：黑旗</value>
                <value>刺客信条：大革命</value>
                <value>刺客信条：起源</value>
                <value>Witch dog</value>
            </set>
        </property>
        <!--null 注入：
        空串注入和null注入不同，空串注入：<property name="wife" value=""/>
        null注入： <property name="wife">
                    <null/>
                 </property>
        -->
        <property name="wife">
            <null/>
        </property>
        <!--Properties注入:-->
        <property name="info">
            <props>
                <!--prop也是key:value格式的，但是value必须写到标签外面-->
                <prop key="学号">123455665</prop>
                <prop key="准考证号">1248080</prop>
            </props>
        </property>
    </bean>

</beans>
```

测试结果：

```tex
Student{	
	name='法外狂徒张三', 
	address=Address{address='临沂市'}, 
	books=[红楼梦, 不能承受的生命之轻, 黄金时代], 
	hobbies=[play game, Anime], 
	card={IDcard=123456, 银行卡=65432120}, 
	games=[刺客信条：黑旗, 刺客信条：大革命, 刺客信条：起源, Witch dog], 
	wife='null', 
	info={学号=123455665, 准考证号=1248080}
	}
```

## 6.3 p命名空间和c命名空间注入

### 6.3.1 p命名空间注入

使用p命名空间执行与 基于Set的依赖注入相同的操作

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--p命名空间：<property>可以直接注入属性的值-->
    <bean id="user" class="pojo.User" p:name="法外狂徒张三" p:age="17"/>

    </beans>
```

在applicationContext.xml中引入官方约束： ``xmlns:p="http://www.springframework.org/schema/p" ``

可以简化配置：p命名空间就相当于<proerty>标签的作用

```java
  @Test
    public void pTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = (User) context.getBean("user");
        System.out.println(user.toString());

    }
/*
User{name='法外狂徒张三', age=17}
*/
```

### 6.3.2 c命名空间注入

使用c命名空间执行与 基于构造函数的依赖注入相同的操作

==使用c命名空间必须显式的生命有参构造器==

官网依赖：`` xmlns:c="http://www.springframework.org/schema/c"``

```java
public User() {
}
public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--p命名空间：<property>可以直接注入属性的值-->
    <bean id="user" class="pojo.User" p:name="法外狂徒张三" p:age="17"/>

    <!--c命名空间注入：依赖于显式的有参构造器-->
    <bean id="user2" class="pojo.User" c:name="王司徒" c:age="76"/>

    </beans>
```

Test:

```java
@Test
    public void cTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = (User) context.getBean("user2");
        System.out.println(user.toString());
    }
/*
User{name='王司徒', age=76}
*/
```

## 6.4 bean的作用域

==request、session、application、这些个只能在web开发中使用。==

![](.\image\di\bean的作用域.png)

### 6.4.1 singleton单例（Spring默认是单例的）

![](.\image\di\bean作用域——单例.png)

```xml
<!--scope="singleton" singleton可以显式的声明也可以不声明，如果不声明，则默认时singleton-->
<bean id="user" class="pojo.User" p:name="法外狂徒张三" p:age="17" scope="singleton"/>
```

对于User类来说，在xml文件中只注入了一个，``id=”user“``有且仅有一个

```java
@Test
    public void pTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user1 = (User) context.getBean("user");
        User user2 = (User) context.getBean("user");
        System.out.println(user1==user2);
    }

/*
结果：true
以getBean("user")获取的对象不管获取多少个，在内存中永远只有一个（单例模式）
*/
```

### 6.4.2 prototype（原型模式）

**每次从容器中get的时候，都会产生一个新的对象**

![](.\image\di\bean作用域——原型模式.png)

```xml
<!--scope="prototype"声明为prototype原型模式-->
<bean id="user" class="pojo.User" p:name="法外狂徒张三" p:age="17" scope="prototype"/>
```

```java
@Test
    public void pTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user1 = (User) context.getBean("user");
        User user2 = (User) context.getBean("user");
        System.out.println(user1==user2);
    }
/*
结果：false
user1 与 user2不是同一个对象
*/
```

# 7.Bean的自动装配



## 7.1什么是Bean的自动装配？

到目前为止，在xml中配置bean都是手动配置的。spring还可以实现bean的自动装配。

- 自动装配是spring满足bean依赖的一种方式！
- Spring会在上下文中自动寻找，并自动给bean装配属性！

在spring中bean的三种装配方式：

1. 在xml中显式装配
2. 在Java中显式装配
3. ==隐式的自动装配Bean（重要）==



## 7.2实现自动装配

### 7.2.1环境

创建三个类，People ,Cat,Dog。一个人有两个宠物

```xml
<!--手动的在applicationContext.xml中显式的装配-->  
<bean id="cat" class="pojo.Cat"/>
    <bean id="dog" class="pojo.Dog"/>
    <bean id="people" class="pojo.People">
        <property name="name" value="法外狂徒张三"/>
        <property name="cat" ref="cat"/>
        <property name="dog" ref="dog"/>
    </bean>
```

### 7.2.2. byName自动装配

```xml
<bean id="cat" class="pojo.Cat"/>
<bean id="dog" class="pojo.Dog"/>
    <!--byName :会自动在容器上下文中查找，和自己对象set方法后面的值对象的bean_id-->
	<!--<bean id="dog222" class="pojo.Dog"/>
		如果将此标签改成这样，就会无法装配 因为People中的setDog方法中的Dog与dog222不相符
	-->
<bean id="people" class="pojo.People" autowire="byName">
    <property name="name" value="法外狂徒张三" />
</bean>
```

### 7.2.3 byType自动装配

```xml
<bean id="cat" class="pojo.Cat"/>
    <bean id="dog11111" class="pojo.Dog"/>
    <!--<bean id="dog" class="pojo.Dog"/>
        如果再写一个class属性相同的标签，并且使用byType自动自动装配就会直接报错
    -->

    <!--
    使用BbyType自动装配甚至可以不用写id属性
    <bean class="pojo.Cat"/>
    <bean class="pojo.Dog"/>
    -->

<!--byType :会自动在容器上下文中查找，和自己对象属性类型相同的的bean_id-->
    <bean id="people" class="pojo.People" autowire="byType">
        <property name="name" value="法外狂徒张三" />
    </bean>
```

### 7.2.4 注解实现自动装配

jdk1.5支持注解，Spring2.5开始支持注解。

#### 7.2.4.1 使用注解须知

1. 导入约束：context约束
2. 配置注解的支持：<context:annotation-config/>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

#### @Autowired

直接在属性上使用即可！也可以在set方式上使用

```java
  	@Autowired
    private Cat cat;
    @Autowired
    private Dog dog;
```

```xml
<!--使用@Autowired注解配置，达到在xml中最简单那的配置-->
<bean id="cat" class="pojo.Cat"/>
<bean id="dog" class="pojo.Dog"/>
<bean id="people" class="pojo.People"/>
```

**甚至可以不用书写set方法了：**

使用Autowired我们可以不用编写Set方法了，前提是你这个自动装配的属性在IoC（Spring）容器中存在，且符合名字byName！

**科普：**

```java
@Nullable	字段标记了这个注解，说明这个字段可以为null
    public People(@Nullable String name){
    this.name = name;
}
```

```java
public class People {
    //如果显式定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为空
    @Autowired(required = false)
    private Dog dog;
    @Autowired
    private Cat cat;
    private String name;
}
/*
Autowired中的源码：
public @interface Autowired {
    boolean required() default true;
}*/
```

如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解@Autowired完成的时候，我们可以使用@Qualifier(value="xml中bean_id属性")去配置@Autowired的使用，指定一个唯一的bean对象注入！

```java
public class People {
    @Autowired
    @Qualifier(value="dog11")
    private Dog dog;
    @Autowired
	@Qualifier(value="cat11")
    private Cat cat;
    private String name;
}
```

#### @Resource注解

@Resource注解不是spring的注解，注解Java的注解，使用@Resource完成自动装配的时候，先根据bean_id查找，然后会根据bean_class（也就是类型）查找

```java
 //如果不显式的生命@Resource的name属性，就回去xml中先按照bean_id查找，然后在按照类型查找，不符合条件就会报错
	// @Resource 	
    @Resource(name = "cat2")
    private Cat cat;
```



## 7.3小结

**byName与byType**

- byName的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致！
- byType的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致！

**@Autowired与@Resource**

- 都是用来自动装配的，都可以放在属性字段上；
- @Autowired通过byType的方式实现，而且必须要求这个对象存在！【常用】
- @Resource默认通过byName的方式实现，如果找不到名字，则通过byType实现！如果两个都找不到的情况下，就报错！

- 执行顺序不同：@Autowired通过byType的方式实现，@Resource默认通过byName的方式实现。

# 8.Spring注解开发

## 8.1spring注解使用的前提

在spring4之后，要使用注解开发，必须要保证aop的包导入了

![](.\image\注解\aop包.png)

使用注解需要导入context约束，增加注解的支持！

```xml
 <!--开启注解的支持-->
    <context:annotation-config/>
    <!--指定要扫描的包，这个包下的注解就会生效，默认是所有包都会扫描，可以不用加-->
    <context:component-scan base-package="pojo"/>
```

## 8.2bean

### @Component

@Component注解放在类上，说明这个类被Spring管理了，就是bean

```java
//等价于<bean id="user" class="pojo.User"/>
//Component :组件
@Component
public class User {
    public String name = "法外狂徒张三";
}
```

Test:

```java
@Test
    public void Test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user.name);
    }
/*
法外狂徒张三
*/
```

## 8.3属性的注入

@Value

声明在属性上，或者set方法上

```java
@Component
public class User {
      /*
    	相当于：
        <property name="name" value="法外狂徒张三"/>
         */
    @Value("法外狂徒张三")
    public String name ;
}
//or
 @Value("法外狂徒张三")
    public void setName(String name) {
        this.name = name;
    }
```

## 8.4 衍生注解

@Component有几个衍生注解，我们在web开发中，会按照mvc三层架构分层！

- dao【@Repository】
- service【@Service】
- controller【@Controller】

这四个注解功能都是一样的，都是代表将某个类注册到Spring中，装配Bean！

## 8.5自动装配注解

前面有详细的介绍

```tex
-@Autowired:自动装配通过类型，名字
	如果Autowired不能唯一自动装配上属性，则需要通过@Qualifier(value="xxx")
-@Nullable:字段标记了这个注解，说明这个字段可以为null
-@Resource:自动装配通过名字，类型
```

## 8.6作用域

@Scope注解,声明在类上。

```java

@Component
//<bean id="user" class="pojo.User" p:name="法外狂徒张三" scope="singleton"/>
@Scope("singleton")
public class User {
   
    @Value("法外狂徒张三")
    public String name ;
}
```



源码：

```java
public @interface Scope {
    @AliasFor("scopeName")
    String value() default "";

    @AliasFor("value")
    String scopeName() default "";

    ScopedProxyMode proxyMode() default ScopedProxyMode.DEFAULT;
}
```

## 8.7小结

xml与注解：

- xml更加万能，适用于任何场合！维护简单方便。
- 注解，不是自己的类使用不了，维护相对复杂！

xml与注解最佳实践：

- xml用来管理bean；
- 注解只负责完成属性的注入；
- 我们在使用的过程中，只需要注意一个问题：必须让注解生效，就需要开启注解的支持。

# 9.使用JavaConfig方式配置spring



## 9.1JavaConfig

我们现在要完全不适用Spring的xml配置了，全权交给java来做！

javaConfig是Spring的一个子项目，在Spring4之后，它成为了一个核心功能。

## 9.2使用JavaConfig用到的注解

- ==@Configuration== ：

	- ```tex
		Configuration这个注解标注的类，因也会被spring托管，注册到容器中，因为它本来就是一个@Component
		@Configuration代表这个是个配置类，等同于applicationContext.xml
		既然等同于applicationContext.xml，就可以做applicationContext.xml的所有东西
		```

	- 代表这个类是一个配置类，可理解为用spring的时候xml里面的<beans>标签，

- ==@Bean：==

	- 用来定义一个bean，可以指定初始、销毁方法，及bean范围等，可理解为用spring的时候xml里面的<bean>标签

	- ```
		@Bean注解就相当于<bean>标签，可以将
		函数名就等同于<bean>标签中的id属性
		函数返回值就等同于<bean>标签中的class属性
		```

- ==@ComponentScan==

	- 用来扫描指定包下面的注解类，相当于``<context:component-scan base-package="com.xxx.xxx" />``，普通的spring项目好多注解都需要扫包才有用

- ==@Scope==

	- 调整作用域,来指定bean生成的对象是什么类型的。prototype：多实例的。singleton：单实例的（默认值）。request：同一次请求创建一个实例。session：同一个session创建一个实例。

- ==@Lazy==

	- 懒加载，只有在创建单实例bean时才会生效，默认在容器启动的时候创建对象，添加了此注解后，容器启动不创建对象，而是在第一次使用(获取)Bean创建对象，并初始化。

- ==@Import==

	- 用来导入其他的@Configuration配置类
	- 注意：导入的是全类名

- ==@ImportResource==

	- 用来导入xml配置文件，比如某些配置一定要xml配置。

- ==@Component==

	- 加到类路径自动扫描 。@Controller：一个web的控制层，在Spring MVC中使用 。@Repository：数据管理/存储,企业级应用使用(Dao, DDD) 。@Service 提供一个商业逻辑： 一个无状态的切面。

## 9.3实体类环境

首先搭建一下环境：

````java
package pojo;
public class User {
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

````

## 9.4 @Configuration和@Bean

在JavaConfig中，@Configuration其实就是告诉spring，spring容器要怎么配置（怎么去注册bean，怎么去处理bean之间的关系（装配））。

@Bean的意思就是，我要获取这个bean的时候，你spring要按照这种方式去帮我获取到这个bean。

```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

等同于：

```xml
<beans>
    <bean id="myService" class="com.acme.services.MyServiceImpl"/>
</beans>
```

## 9.5 从IOC容器中获取对象

```java
@Test
    public void Test1() {
        //如果完全使用了配置类的方式去装配Bean，就只能通过AnnotationConfig上下文来获取容器，通过配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        //getBean函数通过@Bean注解标注的函数的函数名获取对象
        User getUser = context.getBean("getUser", User.class);
        getUser.setName("法外狂徒张三");
        System.out.println(getUser.toString());
    }
```



## 9.6@ComponentScan

用来扫描指定包下面的注解类，相当于``<context:component-scan base-package="com.xxx.xxx" />``，普通的spring项目好多注解都需要扫包才有用

```java
@Configuration
@ComponentScan(basePackages = "com.acme") 
public class AppConfig  {
    ...
}
```

## 9.7@Scope

调整作用域,来指定bean生成的对象是什么类型的。

- prototype：多实例的。singleton：
- 单实例的（默认值）。
- request：同一次请求创建一个实例。
- session：同一个session创建一个实例。

**声明在@Bean标注的函数上：**

```java
@Configuration
public class MyConfiguration {

    @Bean
    @Scope("prototype")
    public Encryptor encryptor() {
        // ...
    }
}
```

## 9.8@Bean

用来定义一个bean，可以指定初始、销毁方法，及bean范围等，可理解为用spring的时候xml里面的<bean>标签

```tex
@Bean注解就相当于<bean>标签，可以将
函数名就等同于<bean>标签中的id属性
函数返回值就等同于<bean>标签中的class属性
```

- @Bean注解在返回实例的方法上，如果未通过@Bean指定bean的名称，则默认与标注的方法名相同； 
- @Bean注解默认作用域为单例singleton作用域，可通过@Scope(“prototype”)设置为原型作用域； 
- 既然@Bean的作用是注册bean对象，那么完全可以使用@Component、@Controller、@Service、@Ripository等注解注册bean，当然需要配置@ComponentScan注解进行自动扫描。

### 9.8.1@Bean管理下的bean的生命周期

可以使用基于 Java 的配置来管理 bean 的生命周期。

`@Bean` 支持两种属性，即 `initMethod` 和`destroyMethod`，这些属性可用于定义生命周期方法。在实例化 bean 或即将销毁它时，容器便可调用生命周期方法。

生命周期方法也称为回调方法，因为它将由容器调用。使用 `@Bean` 注释注册的 bean 也支持 JSR-250 规定的标准 `@PostConstruct` 和 `@PreDestroy` 注释。如果您正在使用 XML 方法来定义 bean，那么就应该使用 bean 元素来定义生命周期回调方法。以下代码显示了在 XML 配置中通常使用 bean 元素定义回调的方法

实体类：

```java

package com.dxz.demo.configuration;

public class TestBean {

    private String username;
    private String url;
    private String password;

    public void sayHello() {
        System.out.println("TestBean sayHello...");
    }

    public String toString() {
        return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    }

    public void start() {
        System.out.println("TestBean 初始化。。。");
    }

    public void cleanUp() {
        System.out.println("TestBean 销毁。。。");
    }
}
```



```java
@Configuration
@ComponentScan(basePackages = "com.dxz.demo.configuration")
public class TestConfiguration {
    public TestConfiguration() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }

    //@Bean注解注册bean,同时可以指定初始化和销毁方法,属性的值传入的对应的方法名
    @Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")
    @Scope("prototype")
    public TestBean testBean() {
        return new TestBean();
    }
}
```

启动类：

```java
public class TestMain {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        TestBean tb = (TestBean) context.getBean("testBean");
        tb.sayHello();
        System.out.println(tb);
        
        TestBean tb2 = (TestBean) context.getBean("testBean");
        tb2.sayHello();
        System.out.println(tb2);
    }
}
```

![](.\image\Configuratuion\@Bean的生命周期.png)

```tex
分析：

结果中的1：表明initMethod生效

结果中的2：表明@Scope("prototype")生效
```

## 9.9@Import&@ImportResource

**在@configuration中引入spring的xml配置文件****

```java
@ImportResource("classpath:applicationContext-configuration.xml")
```

**在@configuration中引入其它注解配置**

```java
@Import(TestConfiguration.class)
```

## 9.10使用@Configuration 与使用@Component作为配置类的区别

配置类：

```java
@Configuration
//@Component
@ComponentScan("pojo")//扫描包class
public class  MyConfig {
  
    @Bean
    public Car car(){
        Car car=new Car();
        car.setName("Ford");
        return car;
    }
    @Bean
    public Driver driver(){
        Driver driver = new Driver();
        driver.setCar(car());
        driver.setID(123);
        driver.setName("法外狂徒张三");
        return driver;
    }
}

```

Test:

```java
@Test
    public void Test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Car car = context.getBean("car", Car.class);
        Driver driver = context.getBean("driver", Driver.class);

        boolean result = driver.getCar() == car;
        System.out.println(result ? "是同一个car" : "不是同一个car");
    }
```

结果：

```tex
在使用@Configuration的时候：
	是同一个car
在使用@Component的时候：
	不是同一个car
```

从上面的结果可以发现使用Configuration时在driver和spring容器之中的是同一个对象，而使用Component时是不同的对象。 

虽然Component注解也会当做配置类，但是并不会为其生成CGLIB代理Class，所以在生成Driver对象时和生成Car对象时调用car()方法执行了两次new操作，所以是不同的对象。当时Configuration注解时，生成当前对象的子类Class，并对方法拦截，第二次调用car()方法时直接从BeanFactory之中获取对象，所以得到的是同一个对象

## 9.11bean的两种配置方式

### 9.11.1 使用@Configuration+@Bean的方式

实体类：

```java
public class People {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void sayHello(){
        System.out.println(name+"Say:Hello~ ~");
    }
}
```

配置类：

```java
@Configuration
@ComponentScan("pojo")
public class MyConfig2 {
  @Bean
    public People people(){
      return new People();
  }
}

```

Test:

```java
  @Test
    public void Test3(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig2.class);
        People people = context.getBean("people", People.class);
        people.setName("法外狂徒张三");
        people.sayHello();
    }
/*
结果:
	法外狂徒张三Say:Hello~ ~
*/
```

### 9.11.2使用@Configuration+@Component方式

实体类：

```java
@Component
public class People {
    private String name;
    private int ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void sayHello(){
        System.out.println(name+"Say:Hello~ ~");
    }
}
```

配置类：

```java
@Configuration
@ComponentScan("pojo")
public class MyConfig2 {

    /*@Bean
    public People people(){
      return new People();
  }*/
}

```

Test:

```java
 @Test
    public void Test3(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig2.class);
        People people = context.getBean("people", People.class);
        people.setName("法外狂徒张三");
        people.sayHello();
    }
/*
结果：法外狂徒张三Say:Hello~ ~
*/
```

