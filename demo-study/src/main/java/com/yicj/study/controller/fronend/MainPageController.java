package com.yicj.study.controller.fronend;

import com.yicj.study.entity.dto.MainPageInfoDTO;
import com.yicj.study.entity.dto.Result;
import com.yicj.study.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.inject.annotation.Autowired;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.type.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: MainPageController
 * Description: TODO(描述)
 * Date: 2020/5/23 15:52
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Controller
@Getter
@RequestMapping("/main")
public class MainPageController {

    @Autowired("HeadLineShopCategoryCombineServiceImpl")
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService ;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){

        return headLineShopCategoryCombineService.getMainPageInfo() ;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void throwException(){

        throw new RuntimeException("抛出异常测试") ;
    }
}
