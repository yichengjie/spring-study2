package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.type.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 页面渲染器
 * ClassName: ViewResultRender
 * Description: TODO(描述)
 * Date: 2020/5/30 21:17
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class ViewResultRender implements ResultRender {

    public static final String VIEW_PATH = "/templates/";
    private ModelAndView modelAndView ;

    public ViewResultRender(Object mv) {
        if (mv instanceof ModelAndView){
            //1. 如果入参类型是ModelAndView，则直接赋值给成员变量
            this.modelAndView = (ModelAndView) mv ;
        }else if (mv instanceof String){
            //2. 如果入参类型是String，则为视图，需要包装后才赋值给成员变量
            this.modelAndView = new ModelAndView().setView((String)mv) ;
        }else {
            //3. 针对其他情况，则直接抛出异常
            throw new RuntimeException("Illegal request result type") ;
        }
    }

    /**
     * 将请求处理结果按照视图路径转发到对应视图进行展示
     * @title: render
     * @description: TODO(描述)
     * @params [requestProcessorChain]
     * @author yicj
     * @date 2020/5/31 20:37
     * @return void
     **/  
    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Throwable {
        HttpServletRequest request = requestProcessorChain.getRequest();
        HttpServletResponse response = requestProcessorChain.getResponse();
        String path = this.modelAndView.getView() ;
        Map<String, Object> model = modelAndView.getModel();
        for (Map.Entry<String,Object> entry :model.entrySet()){
            request.setAttribute(entry.getKey(),entry.getValue());
        }
        // jsp
        request.getRequestDispatcher(VIEW_PATH + path).forward(request,response);
    }
}
