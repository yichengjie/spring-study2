package org.simpleframework.mvc.processor.impl;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * jsp 资源请求处理
 * ClassName: JspRequestProcessor
 * Description: TODO(描述)
 * Date: 2020/5/30 20:51
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class JspRequestProcessor implements RequestProcessor {

    // jsp servlet的名称
    private static final String JSP_SERVLET_NAME = "jsp" ;
    // jsp请求资源路径前缀
    private static final String JSP_RESOURCE_PREFIX = "/templates/" ;

    // jsp的RequestDispatcher，处理jsp资源
    private RequestDispatcher jspServlet ;


    public JspRequestProcessor(ServletContext context) {
        this.jspServlet = context.getNamedDispatcher(JSP_SERVLET_NAME) ;
        if (jspServlet == null){
            throw new RuntimeException("there is no jsp servlet") ;
        }
        log.info("The default servlet for jsp  is {}", JSP_SERVLET_NAME);
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Throwable {
        if (isJspResource(requestProcessorChain.getRequestPath())){
            jspServlet.forward(requestProcessorChain.getRequest(),requestProcessorChain.getResponse());
            return false ;
        }
        return true;
    }

    /**
     * 是否请求的是jsp资源
     * @title: isJspResource
     * @description: TODO(描述)
     * @params [requestPath]
     * @author yicj
     * @date 2020/5/31 10:56
     * @return boolean
     **/  
    private boolean isJspResource(String requestPath) {
        return requestPath.startsWith(JSP_RESOURCE_PREFIX) ;
    }
}
