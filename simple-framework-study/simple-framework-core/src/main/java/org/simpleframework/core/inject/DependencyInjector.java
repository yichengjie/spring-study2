package org.simpleframework.core.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.core.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * ClassName: DependencyInjector
 * Description: TODO(描述)
 * Date: 2020/6/2 21:07
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class DependencyInjector {

    // Bean容器
    private BeanContainer beanContainer ;

    public DependencyInjector(){
        this.beanContainer = BeanContainer.getInstance() ;
    }

    /** 
     * @title: 执行Ioc
     * @description: TODO(描述)
     * @params []
     * @author yicj
     * @date 2020/6/3 20:32
     * @return void
     **/  
    public void doIoc(){
        Set<Class<?>> classes = beanContainer.getClasses();
        if (ValidationUtil.isEmpty(classes)){
            log.warn("empty class in beanContainer");
            return;
        }
        //1. 遍历Bean容器中所有的Class对象
        for (Class<?> clazz: classes){
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)){
                continue;
            }
            //2. 遍历Class对象的所有成员变量
            for (Field field: fields){
                //3. 找到被Autowire标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    //4. 获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    //5. 获取这些成员变量的类型在容器里对应的实例
                    Object fieldValue = getFieldInstance(fieldClass, autowiredValue) ;
                    if (fieldValue == null){
                        throw new RuntimeException("unable to inject relevant type, target fieldClass is :" + fieldClass.getName()) ;
                    }else{
                        //6. 通过反射将对应的成员变量实例注入到成员变量所在类的实例
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field,targetBean, fieldValue, true);
                    }
                }
            }
        }
    }


    /** 
     * @title: 根据Class在beanContainer里获取其实例或者实现类
     * @description: TODO(描述)
     * @params [fieldClass]
     * @author yicj
     * @date 2020/6/3 20:50
     * @return java.lang.Object
     **/  
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if (fieldValue != null){
            return fieldValue ;
        }else {
            Class<?> implementedClass =  getImplementClass(fieldClass, autowiredValue) ;
            if (implementedClass !=null){
                return beanContainer.getBean(implementedClass) ;
            }
        }
        return null ;
    }


    /** 
     * @title: 获取接口的而实现类
     * @description: TODO(描述)
     * @params [fieldClass]
     * @author yicj
     * @date 2020/6/3 20:54
     * @return java.lang.Class<?>
     **/  
    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (!ValidationUtil.isEmpty(classSet)){
            if (ValidationUtil.isEmpty(autowiredValue)){
                if (classSet.size() == 1){
                    return classSet.iterator().next() ;
                }else {
                    //如果多于两个实现类且用户未指定其中一个实现类，则抛出异常
                    throw new RuntimeException("multiple implemented classes for "
                            + fieldClass.getName() + " please set @Autowired's value to pick one");
                }
            }else {
                for (Class<?> clazz : classSet){
                    if (autowiredValue.equals(clazz.getSimpleName())){
                        return clazz ;
                    }
                }
            }
        }
        return null ;
    }

}
