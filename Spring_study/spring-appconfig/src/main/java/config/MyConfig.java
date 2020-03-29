package config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import pojo.Car;
import pojo.Driver;
import pojo.User;

/*
Configuration这个注解标注的类，因也会被spring托管，注册到容器中，因为它本来就是一个@Component
@Configuration代表这个是个配置类，等同于applicationContext.xml
既然等同于applicationContext.xml，就可以做applicationContext.xml的所有东西
 */
@Configuration
//@Component
@ComponentScan("pojo")//扫描包class
public class  MyConfig {

   /* @Bean
    public Car car(){
        Car car=new Car();
        car.setName("Ford");
        return car;
    }
    @Bean
    public Driver driver(){
        Driver driver = new Driver();
        driver.setCar(car());
        driver.setID(123);
        driver.setName("法外狂徒张三");
        return driver;
    }*/
}
