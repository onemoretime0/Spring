package staticproxy;

public class Client {
    public static void main(String[] args) {
        UserServiceImpProxy userServiceImpProxy = new UserServiceImpProxy();
        userServiceImpProxy.setUserService(new UserServiceImp());
        userServiceImpProxy.add();
    }
}
