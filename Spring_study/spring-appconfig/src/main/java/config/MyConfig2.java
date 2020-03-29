package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pojo.People;
import pojo.User;

@Configuration
@ComponentScan("pojo")
public class MyConfig2 {

    /*@Bean
    public People people(){
      return new People();
  }*/
}
