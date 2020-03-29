import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;

public class IocCreateObjectTest {

    @Test
    public void indexTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) context.getBean("user");
        //User user = (User) context.getBean("user_alias");
        user.show();
    }
}
