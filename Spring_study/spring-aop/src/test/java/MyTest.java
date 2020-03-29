import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;
import service.UserServiceImp;

public class MyTest {
    @Test
    public void Test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       //动态代理代理的是接口，类型一定是接口
        UserService userServiceImp = context.getBean("userServiceImp", UserService.class);
        userServiceImp.add();
    }
}
