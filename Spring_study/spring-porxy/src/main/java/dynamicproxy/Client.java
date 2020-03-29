package dynamicproxy;

public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Host());
        Rent hostProxy = (Rent) proxyFactory.getProxyInstance();
        hostProxy.rent();

    }
}
