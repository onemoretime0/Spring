
import com.ioctheory.dao.UserDaoIml;
import com.ioctheory.dao.UserDaoMySQLIml;
import com.ioctheory.service.UserService;
import com.ioctheory.service.UserServiceIml;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    //传统方式应用层调用Dao层测试
    @Test
    public void Test1() {
        //用户实际调用的是业务层，dao层它，恶魔不需要接触
        UserServiceIml userService = new UserServiceIml();
        userService.setUserDao(new UserDaoIml());
        userService.getUser();
        userService.setUserDao(new UserDaoMySQLIml());
        userService.getUser();
    }

    //使用spring的方式
    @Test
    public void testSpring() {
        ApplicationContext contxt = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceIml userServiceIml = (UserServiceIml) contxt.getBean("UserServiceIml");
        userServiceIml.getUser();
    }
}
