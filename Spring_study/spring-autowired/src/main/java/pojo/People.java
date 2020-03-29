package pojo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class People {
   // @Resource 如果不显式的生命@Resource的name属性，就回去xml中先按照bean_id查找，然后在按照类型查找，不符合条件就会报错
    @Resource(name = "cat2")
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
