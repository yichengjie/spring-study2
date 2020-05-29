package org.simpleframework.aop;

import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.core.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * ClassName: AspectWeaver
 * Description: TODO(描述)
 * Date: 2020/5/29 21:03
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class AspectWeaver {

    private BeanContainer beanContainer ;

    public AspectWeaver(){
        this.beanContainer = BeanContainer.getInstance() ;
    }

    public void doAop(){
        //1. 获取所有的Aspect类
        Set<Class<?>> aspectClassSet = this.beanContainer.getClassesByAnnotation(Aspect.class);
        //2. 将切面类按照不同的织入目标进行分类
        Map<Class<? extends Annotation>, List<AspectInfo>> categoryMap = new HashMap<>() ;
        if (ValidationUtil.isEmpty(aspectClassSet)){
            return;
        }
        for (Class<?> aspectClass: aspectClassSet){
            if (verifyAspect(aspectClass)){
                categorizeAspect(categoryMap, aspectClass) ;
            }else {
                throw new RuntimeException("@Aspect and @Order have not bean added to the Aspect class," +
                        "or Aspect class does not extend from DefaultAspect, or the value in Aspect Tag equals @Aspect") ;
            }
        }
        if (ValidationUtil.isEmpty(categoryMap)){
            return;
        }
        // 按照不同的织入目标分别去按序织入Aspect的逻辑
        for (Class<? extends Annotation> category: categoryMap.keySet()){
            weaveByCategory(category, categoryMap.get(category)) ;
        }

    }

    private void weaveByCategory(Class<? extends Annotation> category, List<AspectInfo> aspectInfos) {
        // 获取被代理的类的集合
        Set<Class<?>> classSet = beanContainer.getClassesByAnnotation(category);
        // 遍历被代理的类，分别为每个代理类生成动态代理实例
        if(ValidationUtil.isEmpty(classSet)){
            return;
        }
        for (Class<?> targetClass: classSet){
            AspectListExecutor aspectListExecutor = new AspectListExecutor(targetClass,aspectInfos) ;
            Object proxy = ProxyCreator.createProxy(targetClass, aspectListExecutor);
            //将动态代理实例添加到容器中，取代未被代理前的实例
            beanContainer.addBean(targetClass,proxy) ;
        }

    }


    private void categorizeAspect(Map<Class<? extends Annotation>, List<AspectInfo>> categoriedMap, Class<?> aspectClass) {
        Order orderTag = aspectClass.getAnnotation(Order.class) ;
        Aspect aspectTag = aspectClass.getAnnotation(Aspect.class) ;
        DefaultAspect aspect = (DefaultAspect)beanContainer.getBean(aspectClass) ;
        AspectInfo aspectInfo = new AspectInfo(orderTag.value(), aspect) ;
        if (categoriedMap.containsKey(aspectTag.value())){
            categoriedMap.get(aspectTag.value()).add(aspectInfo) ;
        }else {
            List<AspectInfo> aspectInfos = new ArrayList<>() ;
            aspectInfos.add(aspectInfo) ;
            categoriedMap.put(aspectTag.value(),aspectInfos) ;
        }

    }

    private boolean verifyAspect(Class<?> aspectClass) {

        return aspectClass.isAnnotationPresent(Aspect.class)
                && aspectClass.isAnnotationPresent(Order.class)
                && DefaultAspect.class.isAssignableFrom(aspectClass)
                && aspectClass.getAnnotation(Aspect.class).value() != Aspect.class ;
    }

}
