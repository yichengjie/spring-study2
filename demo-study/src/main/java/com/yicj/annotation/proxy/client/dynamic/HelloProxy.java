package com.yicj.annotation.proxy.client.dynamic;

import com.yicj.annotation.proxy.HelloInvocationHandler;
import com.yicj.annotation.proxy.client.Hello;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * ClassName: HelloProxy
 * Description: TODO(描述)
 * Date: 2020/5/24 9:57
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class HelloProxy implements Hello {

    private HelloInvocationHandler invocationHandler ;

    private Hello target ;

    public HelloProxy(Hello target, HelloInvocationHandler invocationHandler){
        this.target = target ;
        this.invocationHandler = invocationHandler ;
    }

    @Override
    public String hello1() {
        try {
            Method hello1 = this.target.getClass().getMethod("hello1", new Class<?>[]{});
            return (String) this.invocationHandler.invoke(target, hello1, new Object[]{} );
        }catch (Throwable var3){
            throw new UndeclaredThrowableException(var3);
        }
    }

    @Override
    public Integer hello2() {
        try {
            Method hello1 = this.target.getClass().getMethod("hello1", new Class<?>[]{});
            return (Integer) this.invocationHandler.invoke(target, hello1, new Object[]{} );
        }catch (Throwable var3){
            throw new UndeclaredThrowableException(var3);
        }
    }
}
