package org.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 待执行的Controller及其方法实例和参数的映射
 * ClassName: ControllerMethod
 * Description: TODO(描述)
 * Date: 2020/5/31 11:17
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControllerMethod {
    // Controller对应的Class对象
    private Class<?> controllerClass ;
    // 执行Controller方法实例
    private Method invokeMethod ;
    // 方法参数名称以及对应的参数类型
    private Map<String,Class<?>> methodParameters ;
}
