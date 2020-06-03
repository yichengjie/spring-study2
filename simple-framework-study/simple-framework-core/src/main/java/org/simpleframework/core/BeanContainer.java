package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: BeanContainer
 * Description: TODO(描述)
 * Date: 2020/6/2 21:08
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class BeanContainer {
    // 加载bean的注解列表
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION_LIST =
            Arrays.asList(Component.class, Controller.class, Service.class, Repository.class) ;
    // 存放所有配置标记的目标对象Map
    private final Map<Class<?> , Object> beanMap = new ConcurrentHashMap<>() ;

    // 容器是否已经加载bean
    private boolean loaded = false ;

    // 是否已加载
    public boolean isLoaded() {
        return loaded;
    }

    // 获取bean容器实例
    public static BeanContainer getInstance(){
        return BeanContainerHolder.HOLDER.instance ;
    }


    /**
     * 扫描加载所有bean
     * @title: loadBeans
     * @description: TODO(描述)
     * @params [packageName]  包名
     * @author yicj
     * @date 2020/6/2 21:31
     * @return void
     **/
    public synchronized  void loadBeans(String packageName){
        if (isLoaded()){
            log.warn("BeanContainer has bean loaded");
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName) ;
        if (ValidationUtil.isEmpty(classSet)){
            log.warn("extract nothing from packageName :{}", packageName);
            return;
        }
        for (Class<?> clazz: classSet){
            // 如果类上面标记了定义的注解
            if (ValidationUtil.isAnnotationPresent(clazz, BEAN_ANNOTATION_LIST)){
                //将目标类本身作为键，目标类的实例作为值，放入到beanMap中
                beanMap.put(clazz, ClassUtil.newInstance(clazz, true)) ;
            }
        }
        loaded = true ;
    }


    /**
     * 添加一个class对象及其bean实例
     * @title: addBean
     * @description: TODO(描述)
     * @params [clazz, bean]
     * @author yicj
     * @date 2020/6/3 19:42
     * @return void
     **/
    public Object addBean(Class<?> clazz, Object bean){
        return beanMap.put(clazz, bean) ;
    }

    /**
     * 移除一个Ioc容器管理的对象
     * @title: removeBean
     * @description: TODO(描述)
     * @params [clazz]
     * @author yicj
     * @date 2020/6/3 19:45
     * @return 原有的bean的实例，没有则返回null
     **/  
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz) ;
    }

    /**
     * 根据Class对象获取Bean实例
     * @title: getBean
     * @description: TODO(描述)
     * @params [clazz]
     * @author yicj
     * @date 2020/6/3 19:47
     * @return java.lang.Object
     **/  
    public Object getBean(Class<?> clazz){
        return beanMap.get(clazz) ;
    }


    /**
     * 获取容器管理所有Class对象集合
     * @title: getClasses
     * @description: TODO(描述)
     * @params []
     * @author yicj
     * @date 2020/6/3 19:48
     * @return java.util.Set<java.lang.Class<?>>
     **/  
    public Set<Class<?>> getClasses(){

        return beanMap.keySet() ;
    }

    /**
     * 获取所有Bean集合
     * @title: getBeans
     * @description: TODO(描述)
     * @params []
     * @author yicj
     * @date 2020/6/3 19:49
     * @return java.util.Set<?>
     **/
    public Set<Object> getBeans(){
        return new HashSet<>(beanMap.values()) ;
    }


    /**
     * 根据注解筛选出Bean的Class集合
     * @title: getClassesByAnnotation
     * @description: TODO(描述)
     * @params [annotation]
     * @author yicj
     * @date 2020/6/3 19:56
     * @return java.util.Set<java.lang.Class<?>>
     **/  
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        // 1. 获取beanMap的所有Class对象
        Set<Class<?>> keySet = getClasses() ;
        if (ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null ;
        }
        // 2. 通过注解筛选被注解标记的Class对象，并添加到classSet里
        Set<Class<?>> classSet = new HashSet<>() ;
        for (Class<?> clazz: keySet){
            if (clazz.isAnnotationPresent(annotation)){
                classSet.add(clazz) ;
            }
        }
        return classSet.isEmpty() ? null : classSet ;
    }


    /**
     * 通过接口或者父类获取实现类或者子类的Class集合，不包括其本身
     * @title: getClassesBySuper
     * @description: TODO(描述)
     * @params [interfaceOrClass]
     * @author yicj
     * @date 2020/6/3 20:09
     * @return java.util.Set<java.lang.Class<?>>
     **/
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {
        // 1. 获取beanMap的所有Class对象
        Set<Class<?>> keySet = getClasses() ;
        if (ValidationUtil.isEmpty(keySet)){
            return null ;
        }
        // 2. 判断keySet里的元素是否是传入的接口或者类的子类，并添加到classSet里
        Set<Class<?>> classes = new HashSet<>() ;
        for (Class<?> clazz: keySet){
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)){
                classes.add(clazz) ;
            }
        }
        return classes.isEmpty() ? null : classes ;
    }


    /**
     * bean实例数量
     * @title: size
     * @description: TODO(描述)
     * @params []
     * @author yicj
     * @date 2020/6/2 22:27
     * @return int
     **/  
    public int size() {

        return beanMap.size() ;
    }



    private enum BeanContainerHolder{
        HOLDER ;
        private BeanContainer instance ;
        BeanContainerHolder(){
            instance = new BeanContainer() ;
        }
    }
}
