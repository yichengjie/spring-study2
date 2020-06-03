package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

/**
 * ClassName: Aspect
 * Description: TODO(描述)
 * Date: 2020/5/29 20:24
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
   String pointcut() ;
}
