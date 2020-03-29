package pojo;

import org.springframework.stereotype.Component;

@Component
public class Driver {
    private String Name;
    private int ID;
    private Car car;

    @Override
    public String toString() {
        return "Driver{" +
                "Name='" + Name + '\'' +
                ", ID=" + ID +
                ", car=" + car +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
