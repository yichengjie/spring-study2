package org.simpleframework.core;

/**
 * ClassName: BeanContainer
 * Description: TODO(描述)
 * Date: 2020/6/2 21:08
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class BeanContainer {

    public static BeanContainer getInstance(){
        return BeanContainerHolder.HOLDER.instance ;
    }

    private enum BeanContainerHolder{
        HOLDER ;
        private BeanContainer instance ;
        BeanContainerHolder(){
            instance = new BeanContainer() ;
        }
    }


}
