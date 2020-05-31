package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * 渲染请求结果
 * ClassName: ResultRender
 * Description: TODO(描述)
 * Date: 2020/5/30 21:13
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface ResultRender {

    // 执行渲染
    void render(RequestProcessorChain requestProcessorChain) throws Throwable;

}
