package pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//等价于<bean id="user" class="pojo.User"/>
//Component 组件
@Component
@Scope("singleton")
public class User {
    /*
    相当于：
        <property name="name" value="法外狂徒张三"/>
         */
    @Value("法外狂徒张三")
    public String name ;
    /*
        相当于：
            <property name="name" value="法外狂徒张三"/>
             */
    @Value("法外狂徒张三")
    public void setName(String name) {
        this.name = name;
    }
}
