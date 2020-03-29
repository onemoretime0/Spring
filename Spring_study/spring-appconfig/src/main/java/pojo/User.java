package pojo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//这里的@Component注解说明，这个类被spring接管了。注册到了容器中，这里的Component可以不用加，
//在Config类中用@Bean标注的方法中返回的对象，都会被spring管理
@Component
public class User {
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
