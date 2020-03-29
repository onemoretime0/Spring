import config.MyConfig;
import config.MyConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.Car;
import pojo.Driver;
import pojo.People;
import pojo.User;

public class MyTest {
    @Test
    public void Test1() {
        //如果完全使用了配置类的方式去装配Bean，就只能通过AnnotationConfig上下文来获取容器，通过配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        User getUser = context.getBean("getUser", User.class);
        getUser.setName("法外狂徒张三");
        System.out.println(getUser.toString());
    }

    @Test
    public void Test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Car car = context.getBean("car", Car.class);
        Driver driver = context.getBean("driver", Driver.class);
    }
    @Test
    public void Test3(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig2.class);
        People people = context.getBean("people", People.class);
        people.setName("法外狂徒张三");
        people.sayHello();
    }
}
