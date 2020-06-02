package org.simpleframework.util;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ValidationUtil {
    /**
     * String是否为null或""
     *
     * @param obj String
     * @return 是否为空
     */
    public static boolean isEmpty(String obj) {
        return (obj == null || "".equals(obj));
    }

    /**
     * Array是否为null或者size为0
     *
     * @param obj Array
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }
    /**
     * Collection是否为null或size为0
     *
     * @param obj Collection
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> obj){
        return obj == null || obj.isEmpty();
    }
    /**
     * Map是否为null或size为0
     *
     * @param obj Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return obj == null || obj.isEmpty();
    }


    /**
     * 判断类上是否有某个注解
     * @title: isAnnotationPresent
     * @description: TODO(描述)
     * @params [clazz, annoList]
     * @author yicj
     * @date 2020/6/2 21:40
     * @return boolean
     **/  
    public static boolean isAnnotationPresent(Class<?> clazz, List<Class<? extends Annotation>> annoList){
        for (Class<? extends Annotation> annoClazz: annoList){
            if (clazz.isAnnotationPresent(annoClazz)){
                return true ;
            }
        }
        return false ;
    }
}
