package org.simpleframework.mvc.processor.impl;

import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;

/**
 *
 * 静态资源请求处理，包括但不限于图片，css，以及js文件等
 * ClassName: StaticResourceRequestProcessor
 * Description: TODO(描述)
 * Date: 2020/5/30 20:49
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class StaticResourceRequestProcessor implements RequestProcessor {
    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Throwable {
        return true;
    }
}
