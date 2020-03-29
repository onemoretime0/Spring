  

# 1.代理模式



为什么要学习代理模式？**因为这就是SpringAOP的底层！**==SpringAOP 和 SpringMVC  面试必问==

代理模式的分类：

- 静态代理
- 动态代理

![](.\image\代理模式\代理模式.png)

## 1.1静态代理

### 1.1.1角色分析：

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色（目标对象）：被代理的角色
- 代理角色（代理对象）：代理真是角色，代理真实角色后，我们一般会做一些附属操作。
- 客户：访问代理对象的人！



### 1.1.2 代码实现

1.接口:

```java
//租房
public interface Rent {
    public void rent();
}
```

2.真实角色（目标对象）

```java
//房东
public class Host implements Rent {
    public void rent(){
        System.out.println("房东要出租房子！");
    }
}
```

3.代理对象

```java
public class Proxy implements Rent {
    private Host host;

    public Proxy() {
    }
    public Proxy(Host host) {
        this.host = host;
    }

    public void rent(){
        seeHouse();
        host.rent();
        hetong();
        fee();
    }
    //看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    //签合同
    public void hetong(){
        System.out.println("签合同");
    }
    //收费
    public void fee(){
        System.out.println("收取中介费用");
    }
}
```

Test:

```java
public class Client {
    public static void main(String[] args) {
        //房东要租房子
        Host host = new Host();
        //代理，中介帮房东租房子，但是呢？代理角色一般会有一些附属操作！
        Proxy proxy = new Proxy(host);
        proxy.rent();
    }
}
```



### 1.1.3 静态代理分析

代理模式的好处：

- 可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共也就交给代理角色！实现了业务的分工！
- 公共业务发生扩展的时候，方便集中管理！

缺点：

- 一个真实角色就会产生一个代理角色，代码量会翻倍，开发效率会变低

## 1.2静态代理再理解

源代码：

```java
//接口

public interface UserService {
    void add();
    void delete();
    void  update();
    void query();
}
//实现类
public class UserServiceImp implements UserService {
    @Override
    public void add() {
        System.out.println("添加了一个用户");
    }
    @Override
    public void delete() {
        System.out.println("删除了一个用户");
    }
    @Override
    public void update() {
        System.out.println("更新了一个用户");
    }
    @Override
    public void query() {
        System.out.println("了一个用户");
    }
}

//Test:
public class Client {
    public static void main(String[] args) {
        UserServiceImp userServiceImp = new UserServiceImp();
        userServiceImp.add();
    }
}
```

**新的需求**

现在根据要求，在每个方法调用的时候添加一条日志。

根据一般性要求，在添加新的功能的时候，尽量不要在原有的代码上进行修改。

而且在原有代码的基础上进行修改，会涉及大量的代码，每一个方法都要修改，工作量很大。

那么现在就可以使用代理模式，新增加一个类实现UserService接口，代理原有的UserService类，在代理类中增加新的功能

```java

public class UserServiceImpProxy implements UserService{
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }

    @Override
    public void update() {
        log("update");
        userService.update();
    }

    @Override
    public void query() {
        log("query");
        userService.query();
    }
    //日志方法
    private void log(String msg){
        System.out.println("使用了"+msg+"方法");
    }
}

//调用
public class Client {
    public static void main(String[] args) {
        UserServiceImpProxy userServiceImpProxy = new UserServiceImpProxy();
        userServiceImpProxy.setUserService(new UserServiceImp());
        userServiceImpProxy.add();
    }
}

```

**这也是AOP的实现机制**

![](.\image\代理模式\AOP的实现机制.png)

## 1.3 动态代理

- 动态代理和静态代理角色一样
- 动态代理的代理类是动态生成的，不是我们直接写好的。
- 动态代理分为两大类：基于接口的动态代理，基于类的动态代理
	- 基于接口——JDK动态代理
	- 基于类：cglib
	- java字节码实现：javasisit



需要了解两个类：Proxy：代理；InvocationHandler：调用处理程序



动态代理的好处：

- 可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共也就交给代理角色！实现了业务的分工！
- 公共业务发生扩展的时候，方便集中管理！
- 一个动态代理类代理类代理的是一个接口，一般就是对应的一类业务
- 一个动态代理类可以代理多个类，只要是实现了同一个接口即可！

# 2.AOP



## 2.1 什么是AOP

AOP（Aspect Oriented Programming）意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。

**在不改变原有代码的情况下，实现对原有功能的增强**

==横向开发==

AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生泛型，利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的频率。

![](.\image\AOP\AOP.png)

## 2.2 AOP在Spring中的作用

==提供声明式事务；允许用户自定义切面==

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点，如日志、安全、缓存、事务等等……
- 切面（ASPECT）：横切关注点被模块化的特殊对象，即是一个类。
- 通知（Advice）：切面必须要完成的工作，即是类中的一个方法。
- 目标（Target）：被通知对象。
- 代理（Proxy）：向目标对象应用通知之后创建的对象。
- 切入点（PointCut）：切面通知执行的“地点”的定义。
- 连接点（jointPoint）：与切入点匹配的执行点。

![](.\image\AOP\AOP角色.png)

SpringAOP中，通过Advice定义切面逻辑，Spring中支持5种类型的Advice:

| 通知类型     | 连接点               | 实现接口                                        |
| ------------ | -------------------- | ----------------------------------------------- |
| 前置通知     | 方法前               | org.springframework.aop.MethodBeforeAdvice      |
| 后置通知     | 方法后               | org.springframework.aop.AfterReturningAdvice    |
| 环绕通知     | 方法前后             | org.aopalliance.intercept.MethodInterceptor     |
| 异常抛出通知 | 方法抛出异常         | org.springframework.aop.ThrowsAdvice            |
| 引介通知     | 类中增加新的方法属性 | org.springframework.aop.IntroductionInterceptor |

即AOP在不改变原有代码的情况下，去增加新的功能。

## 2.3Sring实现AOP前提

:imp:使用AOP织入，需要导入一个依赖包

```xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.5</version>
</dependency>
```

## 2.4Spring实现AOP方式：Spring API

目标对象：

```java
public class UserServiceImp implements UserService {
    @Override
    public void add() {
        System.out.println("添加了一个用户");
    }
    @Override
    public void delete() {
        System.out.println("删除了一个用户");
    }
    @Override
    public void update() {
        System.out.println("更新了一个用户");
    }
    @Override
    public void query() {
        System.out.println("查询了一个用户");
    }
}
```

新功能：日志

```java
//后置通知
public class AfterLog implements AfterReturningAdvice {

    //returnVal :返回值
    @Override
    public void afterReturning(Object returnVal, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println(o1.getClass().getName()+"执行了"+method.getClass().getName()+"方法,返回的结果是："+returnVal);
    }
}
//-------------------------------------------------------------------------------------
//前置通知
public class BeforeLog implements MethodBeforeAdvice {

    /*
      参数说明：
        1.Method method：要执行的目标对象方法
        2.Object[] objects：参数
        3.Object o：目标对象
     */
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getName()+"的"+method.getClass().getName()+"开始执行。。。");
    }
}
```

**配置实现AOP**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userServiceImp" class="service.UserServiceImp"/>
    <bean id="afterLog" class="logs.AfterLog"/>
    <bean id="beforeLog" class="logs.BeforeLog"/>

    <!--方式一：使用原生的Spring API接口-->
    <!-- 配置AOP:需要导入AOP的约束-->
    <aop:config>
        <!--切入点:expression:表达式，execution(要执行的位置)
        这句代码的意思就是，要在service.UserServiceImp类中所有的方法执行的时候，都要执行新增加的功能
        也就是以service.UserServiceImp类中所有的方法作为新功能的切入点
        -->
        <aop:pointcut id="pointcut" expression="execution(* service.UserServiceImp.*(..))"/>
        <!--执行增强环绕-->
        <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>

    </aop:config>
    
</beans>
```

Test:

```java
 @Test
    public void Test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       //动态代理代理的是接口，类型一定是接口接受变量
        UserService userServiceImp = context.getBean("userServiceImp", UserService.class);
        userServiceImp.add();
    }

```

## 2.5Spring实现AOP方式：自定义来实现AOP

自定义的切面类：

```java
//自定义切面类
public class MyPointCut {
    public void before(){
        System.out.println("===方法执行前======");
    }
    public void after(){
        System.out.println("====方法执行后=======");
    }
}
```

**配置**

```xml
<!--方式二：定义类来实现AOP-->
    <bean id="myPointCut" class="diy.MyPointCut"/>
    <aop:config>
        <!--自定义切面：ref要引用的类-->
        <aop:aspect id="aspect" ref="myPointCut">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* service.UserServiceImp.*(..))"/>
            <!--通知-->
            <!--这句代码的意思就是，在切入点point的位置执行before方法(自定义切面类中的方法)-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>

    </aop:config>

```

Test:

```java
@Test
    public void Test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       //动态代理代理的是接口，类型一定是接口
        UserService userServiceImp = context.getBean("userServiceImp", UserService.class);
        userServiceImp.add();
    }
/*
结果：
===方法执行前======
添加了一个用户
====方法执行后=======
*/
```

## 2.6 Spring实现AOP方式：注解

切面类：

```java
//使用注解方式事项AOP
@Aspect //标注这个类是一个切面
public class AnnotationPointCut {
    @Before("execution(* service.UserServiceImp.*(..))")
    public void before(){
        System.out.println("方法执行前。。。。");
    }
    @AfterReturning("execution(* service.UserServiceImp.*(..))")
    public void after(){
        System.out.println("方法执行后。。。");
    }
     //在环绕增强中，我们可以定义一个参数，代表我们要获取处理切入的点
    @Around("execution(* service.UserServiceImp.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");
        Signature signature = joinPoint.getSignature();//获得签名
        System.out.println(signature);
        //执行方法
        Object proceed = joinPoint.proceed();

        System.out.println("环绕后");
    }
}

```

xml配置：

```xml
 <!--方式三：-->
    <!--bean也可以直接使用@Component注解注册到容器中-->
    <bean id="annotationPointCut" class="diy.AnnotationPointCut"/>
    <!--开启注解支持
    spring实现代理的两种方式：
        1.JDK(接口，默认)：proxy-target-class="false"
        2.cglib(类)： proxy-target-class="true"

    -->
    <aop:aspectj-autoproxy proxy-target-class="true"/>
```

Test:

```java
 @Test
    public void Test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       //动态代理代理的是接口，类型一定是接口
        UserService userServiceImp = context.getBean("userServiceImp", UserService.class);
        userServiceImp.add();
    }

/*
结果：
    环绕前
    void service.UserServiceImp.add()
    方法执行前。。。。
    添加了一个用户
    环绕后
    方法执行后。。。
*/
```



