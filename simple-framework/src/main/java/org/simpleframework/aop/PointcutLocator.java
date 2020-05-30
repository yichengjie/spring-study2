package org.simpleframework.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

/**
 * ClassName: PointcutLocator
 * Description: TODO(描述) 解析Aspect表达式并且定位被织入的目录
 * Date: 2020/5/30 11:12
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class PointcutLocator {

    // Pointcut解析器，直接给它赋值上Aspect的所有表达式，以便支持对众多表达式的解析
    private PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(
                PointcutParser.getAllSupportedPointcutPrimitives()
            );

    private PointcutExpression pointcutExpression ;

    public PointcutLocator(String expression){
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression) ;
    }

    /**
     *  判断传入的Class对象是否是Aspect的目标代理类，即匹配Pointcut表达式（初筛）
     * @title: roughMatches
     * @description: pand
     * @params [targetClass] 目标类
     * @author yicj
     * @date 2020/5/30 11:23
     * @return boolean  是否匹配
     **/
    public boolean roughMatches(Class<?> targetClass) {
        // couldMatchJoinPointsInType 比较坑，只能校验within
        // 不能校验(execution,call,get,set)，面对无法校验的表达式，直接返回true
        return pointcutExpression.couldMatchJoinPointsInType(targetClass) ;
    }

    /**
     * 判断传入的method 对象是否是Aspect的目标代理方法，即匹配Pointcut表达式（精筛）
     * @title: accurateMatches
     * @description: TODO(描述)
     * @params [method]
     * @author yicj
     * @date 2020/5/30 11:26
     * @return boolean
     **/  
    public boolean accurateMatches(Method method){
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        return shadowMatch.alwaysMatches() ;
    }

}
