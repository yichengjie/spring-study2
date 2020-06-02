package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;

/**
 * 资源找不到时使用的渲染器
 * ClassName: ResourceNotFoundResultRender
 * Description: TODO(描述)
 * Date: 2020/5/30 21:19
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class ResourceNotFoundResultRender implements ResultRender {
    private String httpMethod ;
    private String httpPath ;

    public ResourceNotFoundResultRender(String method, String path) {
        this.httpMethod = method ;
        this.httpPath = path ;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Throwable {
        requestProcessorChain.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND,
                "获取不到对应的请求资源：请求路径["+httpPath+"], 请求方法["+httpMethod+"]");
    }
}
