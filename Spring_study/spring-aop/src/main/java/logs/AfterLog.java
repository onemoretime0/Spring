package logs;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
//后置通知
public class AfterLog implements AfterReturningAdvice {

    //returnVal :返回值
    @Override
    public void afterReturning(Object returnVal, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println(o1.getClass().getName()+"执行了"+method.getClass().getName()+"方法,返回的结果是："+returnVal);
    }
}
