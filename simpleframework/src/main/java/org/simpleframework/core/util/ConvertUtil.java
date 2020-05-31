package org.simpleframework.core.util;

import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.type.ControllerMethod;

/**
 * ClassName: ConvertUtil
 * Description: TODO(描述)
 * Date: 2020/5/31 16:00
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class ConvertUtil {


    /**
     * 返回基本数据类型的空值
     * 需要特殊处理的基本类型即int、double、short、long、byte、float、boolean
     * @title: primitiveNull
     * @description: TODO(描述)
     * @params [type] 参数类型
     * @author yicj
     * @date 2020/5/31 16:04
     * @return 对应的空值
     **/  
    public static Object primitiveNull(Class<?> type) {

        if (type == int.class || type == double.class ||
                type == short.class || type == long.class ||
                type == byte.class || type == float.class){
            return 0 ;
        }else if (type == boolean.class){
            return false ;
        }
        return null ;
    }

    /**
     * String 类型转换成对应的参数类型
     * @title: convertValue
     * @description: TODO(描述)
     * @params [type, requestValue] 参数类型 ， value的值
     * @author yicj
     * @date 2020/5/31 16:08
     * @return 转换后的Object
     **/  
    public static Object convertValue(Class<?> type, String requestValue) {
        if (isPrimitiveType(type)){
            if (ValidationUtil.isEmpty(requestValue)){
                return primitiveNull(type) ;
            }
            if (type.equals(int.class) || type.equals(Integer.class)) {
                return Integer.parseInt(requestValue);
            } else if (type.equals(String.class)) {
                return requestValue;
            } else if (type.equals(Double.class) || type.equals(double.class)) {
                return Double.parseDouble(requestValue);
            } else if (type.equals(Float.class) || type.equals(float.class)) {
                return Float.parseFloat(requestValue);
            } else if (type.equals(Long.class) || type.equals(long.class)) {
                return Long.parseLong(requestValue);
            } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
                return Boolean.parseBoolean(requestValue);
            } else if (type.equals(Short.class) || type.equals(short.class)) {
                return Short.parseShort(requestValue);
            } else if (type.equals(Byte.class) || type.equals(byte.class)) {
                return Byte.parseByte(requestValue);
            }
            return requestValue;
        }else {
            throw new RuntimeException("count not support non primitive type conversion yet") ;
        }
    }

    private static boolean isPrimitiveType(Class<?> type) {
        return type == boolean.class
                || type == Boolean.class
                || type == double.class
                || type == Double.class
                || type == float.class
                || type == Float.class
                || type == short.class
                || type == Short.class
                || type == int.class
                || type == Integer.class
                || type == long.class
                || type == Long.class
                || type == String.class
                || type == byte.class
                || type == Byte.class
                || type == char.class
                || type == Character.class;
    }
}
