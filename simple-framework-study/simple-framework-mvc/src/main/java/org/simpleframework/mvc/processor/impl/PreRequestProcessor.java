package org.simpleframework.mvc.processor.impl;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;

/**
 *
 * 请求预处理，包括编码以及路径处理
 * ClassName: PreRequestProcessor
 * Description: TODO(描述)
 * Date: 2020/5/30 20:48
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class PreRequestProcessor implements RequestProcessor {
    @Override
    public boolean process(RequestProcessorChain chain) throws Throwable {
        //1. 设置请求编码,将其统一设置为UTF-8
        chain.getRequest().setCharacterEncoding("UTF-8");
        //2. 将请求路径末尾的/剔除，为后续匹配Controller请求路径做准备
        //（一般Controller的处理路径是/aaa/bbb，所以如果传入的路径结尾是/aaa/bbb/，就需要处理成/aaa/bbb）
        String requestPath = chain.getRequestPath();
        if (requestPath.length() > 1 && requestPath.endsWith("/")){
            requestPath = requestPath.substring(0, requestPath.length() -1) ;
            chain.setRequestPath(requestPath);
        }
        log.info("process request {} {}", chain.getRequestMethod(), chain.getRequestPath());
        return true;
    }
}
