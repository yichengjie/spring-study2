package com.yicj.annotation.proxy.client;

/**
 * ClassName: Main
 * Description: TODO(描述)
 * Date: 2020/5/24 10:05
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class Main {

    public static void main(String[] args) {

        Hello hello = new HelloImpl() ;

        HelloProxy helloProxy = new HelloProxy(hello, new MyHelloInvocationHandler()) ;

        String retObj = helloProxy.hello1();

        System.out.println("retObj : " + retObj);

    }
}
