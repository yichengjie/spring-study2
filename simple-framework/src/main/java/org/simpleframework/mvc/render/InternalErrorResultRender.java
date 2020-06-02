package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;

/**
 * 内部异常渲染器
 * ClassName: InternalErrorResultRender
 * Description: TODO(描述)
 * Date: 2020/5/30 21:18
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class InternalErrorResultRender implements ResultRender {

    private String errorMsg ;

    public InternalErrorResultRender(String errorMsg){
        this.errorMsg = errorMsg ;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Throwable {
        requestProcessorChain.getResponse().sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMsg);
    }
}
