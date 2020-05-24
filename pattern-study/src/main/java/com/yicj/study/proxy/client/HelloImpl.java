package com.yicj.study.proxy.client;

/**
 * ClassName: HelloImpl
 * Description: TODO(描述)
 * Date: 2020/5/24 9:58
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class HelloImpl implements Hello {
    @Override
    public String hello1() {
        return "hello1";
    }

    @Override
    public Integer hello2() {
        return 4;
    }
}
