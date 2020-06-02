package com.yicj.study.test.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;

/**
 * ClassName: BeanContainerTest
 * Description: TODO(描述)
 * Date: 2020/6/2 22:25
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class BeanContainerTest {

    private static BeanContainer beanContainer ;

    @BeforeAll
    static void init(){
        beanContainer = BeanContainer.getInstance() ;
    }

    @Test
    public void loadBeans(){
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("com.yicj.study");
        Assertions.assertEquals(7, beanContainer.size());
        Assertions.assertEquals(true, beanContainer.isLoaded());
    }
}
