package com.yicj.annotation.proxy;

import java.lang.reflect.Method;

/**
 * ClassName: HelloInvocationHandler
 * Description: TODO(描述)
 * Date: 2020/5/24 9:53
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface HelloInvocationHandler {

    Object invoke(Method method, Object [] params) throws Throwable;
}
