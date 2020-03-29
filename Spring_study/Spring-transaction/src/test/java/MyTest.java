import com.hnl.dao.UserMapper;
import com.hnl.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    @Test
    public void getUserList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapperImpl = context.getBean("userMapperImpl", UserMapper.class);
        List<User> userList = userMapperImpl.getUserList();

        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void transactionTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper mapper = context.getBean("userMapperImpl", UserMapper.class);

        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
