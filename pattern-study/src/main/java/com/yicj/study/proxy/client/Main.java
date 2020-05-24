package com.yicj.study.proxy.client;


import com.yicj.study.proxy.component.MyHelloInvocationHandler;
import com.yicj.study.proxy.component.MyHelloInvocationHandler2;
import com.yicj.study.proxy.proxy.HelloProxy;

import java.util.HashMap;
import java.util.Map;

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
        //test1() ;
        test2() ;
    }

    public static void test1(){
        Hello hello = new HelloImpl() ;
        Hello helloProxy = new HelloProxy(new MyHelloInvocationHandler(hello)) ;
        String retObj = helloProxy.hello1();
        System.out.println("retObj : " + retObj);
    }

    public static void test2(){
        Map<String,Object> map = new HashMap<>() ;
        map.put("hello1" , "hello1 ret value") ;
        map.put("hello2", 123) ;
        HelloProxy helloProxy = new HelloProxy(new MyHelloInvocationHandler2(map)) ;
        String retObj = helloProxy.hello1();
        Integer retObj2 = helloProxy.hello2();
        System.out.println("retObj : " + retObj);
        System.out.println("retObj2 : " + retObj2);
    }
}
