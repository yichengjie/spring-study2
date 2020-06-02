package org.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存储http请求路径和请求方法
 * ClassName: RequestPathInfo
 * Description: TODO(描述)
 * Date: 2020/5/31 11:22
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPathInfo {
    // http请求方法
    private String httpMethod ;
    // http请求路径
    private String httpPath ;
}
