package org.simpleframework.aop.aspect;

import java.lang.reflect.Method;

/**
 * ClassName: DefaultAspect
 * Description: TODO(描述)
 * Date: 2020/5/29 20:29
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public abstract class DefaultAspect {

    public void before(Class<?> targetClass, Method method, Object [] args) {

    }

    public Object afterReturn(Class<?> targetClass, Method method, Object [] args, Object retObj) {

        return retObj ;
    }

    public void afterThrowing(Class<?> targetClass, Method method, Object [] args, Throwable e){

    }

}
