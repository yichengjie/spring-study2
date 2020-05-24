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

    private static Method m0;
    private static Method m1;


    public HelloProxy( HelloInvocationHandler invocationHandler){
        this.invocationHandler = invocationHandler ;
    }

    @Override
    public String hello1() {
        try {
            return (String) this.invocationHandler.invoke(m0, new Object[]{} );
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    @Override
    public Integer hello2() {
        try {
            return (Integer) this.invocationHandler.invoke(m1, new Object[]{} );
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }


    static {
        try {
            m0 = Class.forName("com.yicj.annotation.proxy.client.Hello").getMethod("hello1") ;
            m1 = Class.forName("com.yicj.annotation.proxy.client.Hello").getMethod("hello2") ;
        }catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }

}
