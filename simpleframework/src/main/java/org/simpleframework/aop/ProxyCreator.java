package org.simpleframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * ClassName: ProxyCreator
 * Description: TODO(描述)
 * Date: 2020/5/29 20:59
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class ProxyCreator {

    public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor){
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(targetClass);
        //设置回调函数
        enhancer.setCallback(methodInterceptor);
        return enhancer.create() ;
    }
}
