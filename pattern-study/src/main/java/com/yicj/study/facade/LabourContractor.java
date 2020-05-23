package com.yicj.study.facade;

import com.yicj.study.facade.subclasses.BrickLayer;
import com.yicj.study.facade.subclasses.BrickWorker;
import com.yicj.study.facade.subclasses.Mason;

/**
 * ClassName: LabourContractor
 * Description: TODO(描述)
 * Date: 2020/5/23 12:00
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class LabourContractor {

    private Mason worker1 = new Mason() ;
    private BrickWorker worker2 = new BrickWorker() ;
    private BrickLayer worker3 = new BrickLayer() ;

    public void buildHouse(){
        worker1.mix();
        worker2.carry();
        worker3.neat();
    }
}
