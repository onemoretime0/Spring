package pojo;

import org.springframework.stereotype.Component;

@Component
public class People {
    private String name;
    private int ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void sayHello(){
        System.out.println(name+"Say:Hello~ ~");
    }
}
