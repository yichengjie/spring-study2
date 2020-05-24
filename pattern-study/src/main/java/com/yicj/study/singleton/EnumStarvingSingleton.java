package com.yicj.study.singleton;

import java.lang.reflect.Constructor;

/**
 * ClassName: EnumStarvingSingleton
 * Description: TODO(描述)
 * Date: 2020/5/24 15:34
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class EnumStarvingSingleton {

    private EnumStarvingSingleton(){}

    public static EnumStarvingSingleton getInstance(){
        return ContainerHolder.HOLDER.instance ;
    }

    private enum ContainerHolder{
        HOLDER ;
        private EnumStarvingSingleton instance ;
        ContainerHolder(){
            instance = new EnumStarvingSingleton() ;
        }
    }


//
}
