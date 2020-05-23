package com.yicj.study.facade;

/**
 * ClassName: Client
 * Description: TODO(描述)
 * Date: 2020/5/23 11:59
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class Client {

    public static void main(String[] args) {

        LabourContractor labourContractor = new LabourContractor() ;
        labourContractor.buildHouse();

    }
}
