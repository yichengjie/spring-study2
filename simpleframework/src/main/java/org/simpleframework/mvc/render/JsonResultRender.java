package org.simpleframework.mvc.render;

import com.google.gson.Gson;
import org.simpleframework.mvc.RequestProcessorChain;

import java.io.PrintWriter;

/**
 * Json渲染器
 * ClassName: JsonResultRender
 * Description: TODO(描述)
 * Date: 2020/5/30 21:17
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class JsonResultRender implements ResultRender {
    private Object result ;
    public JsonResultRender(Object result) {
        this.result = result ;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Throwable {
        // 设置响应头
        requestProcessorChain.getResponse().setContentType("application/json");
        requestProcessorChain.getResponse().setCharacterEncoding("UTF-8");
        // 响应流写入gson格式化后的处理结果
        try(PrintWriter writer = requestProcessorChain.getResponse().getWriter()){
            Gson gson = new Gson() ;
            String jsonStr = gson.toJson(result);
            writer.write(jsonStr);
            writer.flush();
        }

    }
}
