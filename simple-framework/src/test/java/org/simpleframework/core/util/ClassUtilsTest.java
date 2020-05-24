package org.simpleframework.core.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * ClassName: ClassUtilsTest
 * Description: TODO(描述)
 * Date: 2020/5/24 14:59
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class ClassUtilsTest {

    @DisplayName("提取目标类方法：extractPackageClass")
    @Test
    public void testExtractPackageClass(){
        String packageName = "com.yicj.study.entity" ;
        Set<Class<?>> classes = ClassUtils.extractPackageClass(packageName);
        System.out.println(classes);
        Assertions.assertEquals(4, classes.size());
    }
}
