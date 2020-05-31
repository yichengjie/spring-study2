package org.simpleframework.mvc.type;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储处理后的结果数据，以显示该数据的视图
 * ClassName: ModelAndView
 * Description: TODO(描述)
 * Date: 2020/5/31 20:26
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class ModelAndView {
    // 页面所在的路径
    @Getter
    private String view ;
    // 页面的data数据
    @Getter
    private Map<String,Object> model = new HashMap<>() ;

    public ModelAndView setView(String view){
        this.view = view ;
        return this ;
    }

    public ModelAndView addViewData(String attributeName, Object attributeValue){
        model.put(attributeName, attributeValue) ;
        return this ;
    }
}
