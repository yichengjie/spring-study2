package org.simpleframework.mvc;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.render.DefaultResultRender;
import org.simpleframework.mvc.render.InternalErrorResultRender;
import org.simpleframework.mvc.render.ResultRender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * 1. 以责任链的模式执行注册的请求处理器
 * 2. 委派给特定的Render实例对处理后的结果进行渲染
 * ClassName: RequestProcessorChain
 * Description: TODO(描述)
 * Date: 2020/5/30 20:46
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@Data
public class RequestProcessorChain {
    // 请求处理迭代器
    private Iterator<RequestProcessor> requestProcessorIterator ;
    // 请求request
    private HttpServletRequest request ;
    // 请求response
    private HttpServletResponse response ;
    // http请求方法
    private String requestMethod ;
    // http请求路径
    private String requestPath ;
    // http响应状态码
    private int responseCode ;
    // 请求结果渲染
    private ResultRender resultRender ;


    public RequestProcessorChain(Iterator<RequestProcessor> iterator, HttpServletRequest req, HttpServletResponse resp) {
        this.requestProcessorIterator = iterator ;
        this.request =req ;
        this.response = resp ;
        //
        this.requestMethod = req.getMethod() ;
        this.requestPath = req.getPathInfo() ;
        this.responseCode = HttpServletResponse.SC_OK ;
        //
    }

    // 以责任链的模式执行请求链
    public void doRequestProcessorChain()  {
        //1. 通过迭代器遍历注册的请求处理器实现类列表
        try {
            while (requestProcessorIterator.hasNext()){
                //2. 直到某个请求处理器执行后返回false为止
                if (!requestProcessorIterator.next().process(this)){
                    break;
                }
            }
        }catch (Throwable e){
            log.error("doRequestProcessorChain error: ", e);
            //3. 期间如果出现异常，则交由内部异常渲染器处理
            this.resultRender = new InternalErrorResultRender() ;
        }
    }

    public void doRender() {
        // 1. 如果请求处理器实现类均未选择合适的渲染器，则使用默认的
        if (resultRender == null){
            this.resultRender = new DefaultResultRender() ;
        }
        // 2. 调用渲染器的render方法对结果进行渲染
        try {
            this.resultRender.render(this);
        } catch (Throwable throwable) {
            log.error("doRender error: " ,throwable);
            throw new RuntimeException(throwable) ;
        }
    }
}
