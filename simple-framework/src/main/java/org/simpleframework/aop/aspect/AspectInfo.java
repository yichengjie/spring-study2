package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: AspectInfo
 * Description: TODO(描述)
 * Date: 2020/5/29 20:29
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@AllArgsConstructor
@Getter
public class AspectInfo {
    private int orderIndex ;
    private DefaultAspect defaultAspect ;
}
