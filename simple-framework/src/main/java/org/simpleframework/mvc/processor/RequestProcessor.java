package org.simpleframework.mvc.processor;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * ClassName: RequestProcessor
 * Description: TODO(描述)
 * Date: 2020/5/30 20:45
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface RequestProcessor {

    boolean process(RequestProcessorChain requestProcessorChain) throws Throwable;
}
