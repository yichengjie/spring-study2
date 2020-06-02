package com.yicj.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GenericDemo
 * Description: TODO(描述)
 * Date: 2020/6/1 20:25
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class GenericDemo {

    public static void main(String[] args) {
        List <String> linkedList = new ArrayList<>() ;
        linkedList.add("words") ;
        //linkedList.add(1) ;
        for (int i =0 ; i < linkedList.size(); i ++){
            String item = linkedList.get(i) ;
            System.out.println(item);
        }
    }
}
