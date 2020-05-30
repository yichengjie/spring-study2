package com.yicj.study.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.annotation.Controller;

import java.lang.reflect.Method;

/**
 * ClassName: ControllerInfoRecordAspect
 * Description: TODO(描述)
 * Date: 2020/5/30 10:01
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@Order(10)
@Aspect(pointcut = "within(com.yicj.study.controller.superadmin.*)")
public class ControllerInfoRecordAspect extends DefaultAspect {

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) {
        log.info("方法开始执行了，执行的类是[{}],执行的方法是[{}],参数是[{}]",
                targetClass.getName(), method.getName(), args);
    }

    @Override
    public Object afterReturn(Class<?> targetClass, Method method, Object[] args, Object retObj) {
        log.info("方法顺利完成，执行的类是[{}],执行的方法是[{}],参数是[{}]",
                targetClass.getName(), method.getName(), args);
        return retObj ;
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) {
        log.info("方法执行失败，执行的类是[{}],执行的方法是[{}],参数是[{}]",
                targetClass.getName(), method.getName(), args);
    }
}
