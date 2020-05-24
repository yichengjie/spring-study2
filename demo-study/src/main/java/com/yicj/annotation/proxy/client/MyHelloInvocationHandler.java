package com.yicj.annotation.proxy.client;

import com.yicj.annotation.proxy.HelloInvocationHandler;

import java.lang.reflect.Method;

/**
 * ClassName: MyHelloInvocationHandler
 * Description: TODO(描述)
 * Date: 2020/5/24 9:55
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class MyHelloInvocationHandler implements HelloInvocationHandler {
    @Override
    public Object invoke(Object target, Method method, Object[] params) throws Throwable{
        System.out.println("----before -------");
        Object ret = method.invoke(target, params) ;
        System.out.println("----before -------");
        return ret;
    }
}
