package com.myself;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class ProxyTest {
    /*public static void main(String[] args) {
        Object[] elements = new Object[1000];

        for (int i = 0;i<elements.length;i++){
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandle(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }
        Integer key = new Random().nextInt(elements.length) + 1;
        int result = Arrays.binarySearch(elements,key);
        if(result > 0) System.out.println(elements[result]);
    }*/
    public static void main(String[] args) {
        ITestProxyInterface iTestProxyInterface = new TestProxyInterfaceImpl();
        TestProxyHandler handler = new TestProxyHandler(iTestProxyInterface);
        ITestProxyInterface proxy = (ITestProxyInterface)Proxy.newProxyInstance(ITestProxyInterface.class.getClassLoader(), new Class[]{ITestProxyInterface.class}, handler);
        proxy.test("11","22");
    }


}
class TraceHandle implements InvocationHandler{
    Object target ;
    public TraceHandle(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("." + method.getName() + "(");
        if(args != null){
            for (int i = 0 ; i< args.length ;i ++){
                System.out.print(args[i]);
                if(i < args.length -1) System.out.print(", ");
            }
        }
        System.out.println(")");
        return method.invoke(target,args);
    }
}

interface ITestProxyInterface{
    String test(String aa,String bb);
}
class TestProxyInterfaceImpl implements ITestProxyInterface{

    @Override
    public String test(String aa, String bb) {
        return aa +"---"+ bb;
    }
}

class TestProxyHandler implements InvocationHandler{
    ITestProxyInterface iTestProxyInterface;
    public TestProxyHandler(ITestProxyInterface iTestProxyInterface){
        this.iTestProxyInterface = iTestProxyInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method + "." + method.getName() + "===" + Arrays.toString(args));
        return method.invoke(iTestProxyInterface,args);
    }
}