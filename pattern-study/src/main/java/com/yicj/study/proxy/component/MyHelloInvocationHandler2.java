package com.yicj.study.proxy.component;

import com.yicj.study.proxy.HelloInvocationHandler;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * ClassName: MyHelloInvocationHandler
 * Description: TODO(描述)
 * Date: 2020/5/24 9:55
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class MyHelloInvocationHandler2 implements HelloInvocationHandler {
    private final Map<String, Object> memberValues;

    public MyHelloInvocationHandler2(Map<String, Object> memberValues){
        this.memberValues = memberValues ;
    }

    @Override
    public Object invoke(Method method, Object[] params) throws Throwable{
        String methodName = method.getName();
        Object ret = memberValues.get(methodName) ;
        return ret;
    }
}
