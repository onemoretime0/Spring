package logs;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
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
