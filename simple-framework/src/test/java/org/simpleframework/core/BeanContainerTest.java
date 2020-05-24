package org.simpleframework.core;

import com.yicj.study.controller.DispatcherServlet;
import com.yicj.study.controller.fronend.MainPageController;
import com.yicj.study.service.solo.HeadLineService;
import com.yicj.study.service.solo.impl.HeadLineServiceImpl;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.Controller;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeanContainerTest {

    private static BeanContainer beanContainer ;

    @BeforeAll
    static void init(){
        beanContainer = BeanContainer.getInstance() ;
    }


    @Order(1)
    @DisplayName("加载目标类及其实例化到BeanContainer: loadBeans")
    @Test
    public void loadBeans() {
        Assertions.assertEquals(false,beanContainer.isLoaded());
        beanContainer.loadBeans("com.yicj.study");
        Assertions.assertEquals(7, beanContainer.size());
        Assertions.assertEquals(true, beanContainer.isLoaded());
    }

    @DisplayName("根据类获取其实例: getBean")
    @Order(2)
    @Test
    public void getBean(){
        MainPageController controller = (MainPageController)beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, controller instanceof MainPageController);
        DispatcherServlet dispatcherServlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class) ;
        Assertions.assertEquals(null, dispatcherServlet);
    }


    @DisplayName("根据注解获取对应的实例：getClassesByAnnotationTest")
    @Order(3)
    @Test
    public void getClassesByAnnotationTest(){
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(3, beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @DisplayName("根据接口获取实现类：getClassesBySuperTest")
    @Order(4)
    @Test
    public void getClassesBySuperTest(){
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(true, beanContainer.getClassesBySuper(HeadLineService.class).contains(HeadLineServiceImpl.class));
    }
}