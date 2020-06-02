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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
