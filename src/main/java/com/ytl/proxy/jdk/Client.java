package com.ytl.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//模拟一个消费者
public class Client {


    public static void main(String[] args) {
        final Prducer prducer = new Prducer();

        /**
         * 动态代理：
         *
         *
         * 第一个参数为要代理的对象的类加载器
         * 第二个参数为要代理对象实现的接口
         * 第三个为匿名对象  其中写入增强代码
         */
        IProducer producerProxy = (IProducer) Proxy.newProxyInstance(prducer.getClass().getClassLoader(),
                prducer.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //返回值
                        Object returnValue = null;
                        //获取方法执行需要的参数
                        Float money = (Float) args[0];//只有一个参数 所以是第一个
                        //判断当前方法是不是销售
                        if ("saleProduct".equals(method.getName())){
                            System.out.println("执行了增强");
                            returnValue = method.invoke(prducer,money*0.8f);  //此处为执行要增强的方法
                        }
                        return returnValue;
                    }
                });

        producerProxy.saleProduct(10000f);


    }


}
