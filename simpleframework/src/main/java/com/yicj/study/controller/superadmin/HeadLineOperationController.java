package com.yicj.study.controller.superadmin;

import com.yicj.study.entity.bo.HeadLine;
import com.yicj.study.entity.dto.Result;
import com.yicj.study.service.solo.HeadLineService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: HeadLineOperationController
 * Description: TODO(描述)
 * Date: 2020/5/23 15:54
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Controller
public class HeadLineOperationController {

    @Autowired
    private HeadLineService headLineService;


    public Result<Boolean> addHeadLine(HttpServletRequest req, HttpServletResponse resp){
        // TODO: 参数校验以及请求参数转化
        return headLineService.addHeadLine(new HeadLine())  ;
    }
    public Result<Boolean> removeHeadLine(int headLineId){
        // TODO: 参数校验以及请求参数转化
        return headLineService.removeHeadLine(1) ;
    }
    public Result<Boolean> modifyHeadLine(HeadLine headLine){
        // TODO: 参数校验以及请求参数转化
        return headLineService.modifyHeadLine(new HeadLine()) ;
    }
    public Result<HeadLine> queryHeadLineById(int headLineId){
        // TODO: 参数校验以及请求参数转化
        return headLineService.queryHeadLineById(1);
    }
    public Result<List<HeadLine>>queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize){
        // TODO: 参数校验以及请求参数转化
        return headLineService.queryHeadLine(null, 1, 100) ;
    }
}