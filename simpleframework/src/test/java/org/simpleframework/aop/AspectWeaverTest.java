package org.simpleframework.aop;

import com.yicj.study.controller.superadmin.HeadLineOperationController;
import com.yicj.study.entity.dto.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.core.inject.DependencyInjector;

/**
 * ClassName: AspectWeaverTest
 * Description: TODO(描述)
 * Date: 2020/5/29 22:33
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class AspectWeaverTest {


    @DisplayName("织入通用逻辑测试:doAop")
    @Test
    public void doAopTest() {
        BeanContainer beanContainer = BeanContainer.getInstance() ;
        beanContainer.loadBeans("com.yicj.study");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        HeadLineOperationController controller = (HeadLineOperationController)beanContainer.getBean(HeadLineOperationController.class) ;
        Result<Boolean> result = controller.addHeadLine(null, null);
        System.out.println(result);
    }
}
