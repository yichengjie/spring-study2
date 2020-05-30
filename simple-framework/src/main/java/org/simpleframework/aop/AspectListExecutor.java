package org.simpleframework.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.util.ValidationUtil;

import java.lang.reflect.Method;
import java.util.*;

/**
 * ClassName: AspectListExecutor
 * Description: TODO(描述)
 * Date: 2020/5/29 20:32
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class AspectListExecutor implements MethodInterceptor {

    private Class<?> targetClass ;
    @Getter
    private List<AspectInfo> sortedAspectInfoList ;

    public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectInfoList){
        this.targetClass = targetClass ;
        this.sortedAspectInfoList = sortAspectInfoList(aspectInfoList) ;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (isEmpty(sortedAspectInfoList )){
            return methodProxy.invokeSuper(proxy, args);
        }

        this.sortedAspectInfoList = collectAccurateMatchedAspectList(method) ;

        Object retObj = null;
        try {
            invokeBeforeAdvices(method, args) ;
            retObj = methodProxy.invokeSuper(proxy, args) ;
            invokeAfterReturnAdvices(method, args, retObj) ;
        }catch (Throwable e){
            invokeAfterThrowingAdvices(method, args, e) ;
        }
        return retObj;
    }

    private List<AspectInfo> collectAccurateMatchedAspectList(Method method) {
        List<AspectInfo> retList = new ArrayList<>() ;
        for (AspectInfo info :this.sortedAspectInfoList){
            if (info.getPointcutLocator().accurateMatches(method)){
                retList.add(info) ;
            }
        }
        return retList ;
    }


    private List<AspectInfo> sortAspectInfoList(List<AspectInfo> aspectInfoList) {
        Collections.sort(aspectInfoList, Comparator.comparingInt(AspectInfo::getOrderIndex));
        return  aspectInfoList ;
    }



    /**
      * @title: 方法执行前执行所有的前置通知
      * @description: TODO(描述)
      * @params 
      * @author yicj
      * @date 2020/5/29 20:56
      * @return 
      */  
    private void invokeBeforeAdvices(Method method, Object [] args) {
        for (AspectInfo info: sortedAspectInfoList){
            info.getDefaultAspect().before(targetClass,method, args);
        }
    }

     /**  
      * @title: 方法执行后执行所有的后置通知
      * @description: TODO(描述)
      * @params 
      * @author yicj
      * @date 2020/5/29 20:57
      * @return 
      */  
    private void invokeAfterReturnAdvices(Method method, Object [] args, Object retObj) {
        for (int i = sortedAspectInfoList.size() -1; i >=0  ; i--){
            retObj = sortedAspectInfoList.get(i).getDefaultAspect().afterReturn(targetClass,method, args, retObj);
        }
    }

     /**  
      * @title: 方法抛出异常执行所有的异常通知
      * @description: TODO(描述)
      * @params 
      * @author yicj
      * @date 2020/5/29 20:57
      * @return 
      */  
    private void invokeAfterThrowingAdvices(Method method, Object [] args, Throwable e) {
        for (int i = sortedAspectInfoList.size() -1; i >=0  ; i--){
            sortedAspectInfoList.get(i).getDefaultAspect().afterThrowing(targetClass,method, args, e);
        }
    }

    private boolean isEmpty(Collection<?> collection){
        if (collection == null || collection.isEmpty()){
            return true ;
        }
        return false ;
    }
}
