package org.simpleframework.mvc.processor.impl;

import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;

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
public class JspRequestProcessor implements RequestProcessor {
    public JspRequestProcessor(ServletContext context) {
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Throwable {
        return false;
    }
}
