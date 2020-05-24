package com.yicj.study.proxy.component;

import com.yicj.study.proxy.HelloInvocationHandler;

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

    private Object target ;

    public MyHelloInvocationHandler(Object target){
        this.target = target ;
    }

    @Override
    public Object invoke(Method method, Object[] params) throws Throwable{
        System.out.println("----before -------");
        Object ret = method.invoke(target, params) ;
        System.out.println("----before -------");
        return ret;
    }
}
