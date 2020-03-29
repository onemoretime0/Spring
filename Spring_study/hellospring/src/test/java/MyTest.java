import com.hello.Hello;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testHelloSpring() {
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象现在都在spring中管理了，如果要使用，直接去里面取出来即可
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }
}
