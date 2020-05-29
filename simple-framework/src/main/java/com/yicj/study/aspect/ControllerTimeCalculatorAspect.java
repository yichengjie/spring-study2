package com.yicj.study.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.annotation.Controller;

import java.lang.reflect.Method;

/**
 * ClassName: ControllerTimeCalculatorAspect
 * Description: TODO(描述)
 * Date: 2020/5/29 22:40
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@Order
@Aspect(Controller.class)
public class ControllerTimeCalculatorAspect extends DefaultAspect {
    private long timestampCache ;

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) {
        log.info("开始计时， 执行的类是[{}], 执行的方法是[{}], 参数是[{}]",
                targetClass.getName(),method.getName(), args);
        timestampCache = System.currentTimeMillis() ;
    }

    @Override
    public Object afterReturn(Class<?> targetClass, Method method, Object[] args, Object retObj) {
        long endTime = System.currentTimeMillis() ;
        long costTime = endTime - timestampCache ;
        log.info("结束计时，执行的类是[{}], 执行的方法是[{}], 返回值是[{}], 时间为[{}]",
                targetClass.getName(), method.getName(), args, costTime);
        return retObj ;
    }
}
