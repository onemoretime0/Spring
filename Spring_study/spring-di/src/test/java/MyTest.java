import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Student;
import pojo.User;

public class MyTest {

    @Test
    public void Test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }

    @Test
    public void pTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user1 = (User) context.getBean("user");
        User user2 = (User) context.getBean("user");
        System.out.println(user1==user2);
    }
    @Test
    public void cTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = (User) context.getBean("user2");
        System.out.println(user.toString());
    }
}
