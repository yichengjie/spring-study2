package org.simpleframework.aop;

import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.core.util.ValidationUtil;
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
        // 1. 获取所有的Aspect类
        Set<Class<?>> aspectClassSet = this.beanContainer.getClassesByAnnotation(Aspect.class);
        if (ValidationUtil.isEmpty(aspectClassSet)){
            return;
        }
        // 2. 拼装AspectInfoList
        List<AspectInfo> aspectInfoList =  pckAspectInfoList(aspectClassSet) ;
        // 3. 遍历容器里面的类
        Set<Class<?>> classSet = beanContainer.getClasses();
        for (Class<?> targetClass: classSet){
            // 排除AspectClass自身
            if (targetClass.isAnnotationPresent(Aspect.class)){
                continue;
            }
            // 4. 初筛符合条件的Aspect
            List<AspectInfo>  roughMatchedAspectList = collectRoughMatchedAspectListForSpecificClass(aspectInfoList, targetClass) ;
            // 5. 尝试进行Aspect的织入
            wrapIfNecessary(roughMatchedAspectList, targetClass) ;
        }
    }

    private void wrapIfNecessary(List<AspectInfo> roughMatchedAspectList, Class<?> targetClass) {
        if (ValidationUtil.isEmpty(roughMatchedAspectList)){
            return;
        }
        // 创建动态代理对象
        AspectListExecutor aspectListExecutor = new AspectListExecutor(targetClass, roughMatchedAspectList) ;
        Object proxyBean = ProxyCreator.createProxy(targetClass, aspectListExecutor);
        beanContainer.addBean(targetClass,proxyBean) ;
    }



    private List<AspectInfo> collectRoughMatchedAspectListForSpecificClass(List<AspectInfo> aspectInfoList, Class<?> targetClass) {
        List<AspectInfo> roughMatchedAspectList = new ArrayList<>() ;
        for (AspectInfo aspectInfo: aspectInfoList){
            if (aspectInfo.getPointcutLocator().roughMatches(targetClass)){
                roughMatchedAspectList.add(aspectInfo) ;
            }
        }
        return roughMatchedAspectList ;
    }


    private List<AspectInfo> pckAspectInfoList(Set<Class<?>> aspectClassSet) {
        List<AspectInfo> aspectInfos = new ArrayList<>() ;
        for (Class aspectClass:  aspectClassSet){
            if (verifyAspect(aspectClass)){
                Order orderTag = (Order)aspectClass.getAnnotation(Order.class) ;
                Aspect aspectTag = (Aspect)aspectClass.getAnnotation(Aspect.class);
                DefaultAspect defaultAspect = (DefaultAspect)beanContainer.getBean(aspectClass) ;
                // 初始化表达式定位器
                PointcutLocator pointcutLocator = new PointcutLocator(aspectTag.pointcut()) ;
                AspectInfo aspectInfo = new AspectInfo(orderTag.value(), defaultAspect, pointcutLocator) ;
                aspectInfos.add(aspectInfo) ;
            }else {
                throw new RuntimeException("@Aspect and @Order must be added to the Aspect class, " +
                        "and Aspect class must extend from DefaultAspect") ;
            }
        }
        return aspectInfos ;
    }


    private boolean verifyAspect(Class<?> aspectClass) {
        return aspectClass.isAnnotationPresent(Aspect.class)
                && aspectClass.isAnnotationPresent(Order.class)
                && DefaultAspect.class.isAssignableFrom(aspectClass);
    }

}
