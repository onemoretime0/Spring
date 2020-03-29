package diy;

import org.aspectj.lang.annotation.Before;

//自定义切入点类
public class MyPointCut {
    @Before("execution(* service.UserService.*(..))")
    public void before(){
        System.out.println("===方法执行前======");
    }
    public void after(){
        System.out.println("====方法执行后=======");
    }
}
