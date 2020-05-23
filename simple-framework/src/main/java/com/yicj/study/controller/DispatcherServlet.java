package com.yicj.study.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: DispatcherServlet
 * Description: TODO(描述)
 * Date: 2020/5/23 15:23
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 * 	1. 注意servlet配置 '/'和'/*'是不一样的，
 * 	原因：tomcat配置文件中有默认配置，有对jsp处理的servlet
 * 	   <!-- The mapping for the default servlet -->
 * 	    <servlet-mapping>
 * 	        <servlet-name>default</servlet-name>
 * 	        <url-pattern>/</url-pattern>
 * 	    </servlet-mapping>
 *
 * 	    <!-- The mappings for the JSP servlet -->
 * 	    <servlet-mapping>
 * 	        <servlet-name>jsp</servlet-name>
 * 	        <url-pattern>*.jsp</url-pattern>
 * 	        <url-pattern>*.jspx</url-pattern>
 * 	    </servlet-mapping>
 * 	2. 那为什么jsp这个servlet在我们自定义的servlet路径 '/'下生效，在配置'/*'后不生效呢？原因：'/'虽然会匹配所有的请求，
 * 	但是它是servlet中特殊的匹配模式，优先级最低，不会覆盖其他的url-parrern的servlet配置，
 * 	只会替换servlet容器中内建名为default的servlet，而‘/*’属于路径匹配，并且可以匹配所有的request，
 * 	由于路径匹配的优先级仅此于精确匹配，所以'/*' 会覆盖所有的扩展名匹配，所以默认jsp的servelet就失效了。
 */
@WebServlet("/")
@Slf4j
// 注意这里不要写成‘/*’，否则会造成死循环请求
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String method = req.getMethod();
        log.info("servletPath : {}, method : {}", servletPath, method);
        this.doGet(req,resp);
    }

    //private Logger log = LoggerFactory.getLogger(this.getClass()) ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "我的简易框架" ;
        log.debug(name);
        req.setAttribute("name", name);
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req,resp);
    }
}
