package com.ytl.proxy.cglib;

import com.ytl.proxy.jdk.IProducer;
import com.ytl.proxy.jdk.Prducer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.channels.FileLock;

//模拟一个消费者
public class Client {


    public static void main(final String[] args) {
        final Prducer prducer = new Prducer();

        /**
         * 动态代理：
         *
         *
         * 第一个参数为要代理的对象的类字节码
         * 第二个参数为cglib提供的
         *
         */
        Prducer cglibProdecter = (Prducer) Enhancer.create(prducer.getClass(), new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //返回值
                Object returnValue = null;
                //获取方法的参数
                Float money = (Float) objects[0];
                //判断是不是自己要增强的方法
                 if("saleProduct".equals(method.getName())) {
                     returnValue = method.invoke(prducer,money*0.8f);
                }
                 return returnValue;

            }
        });

cglibProdecter.saleProduct(12000f);
    }


}
