package org.simpleframework.util;

import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: ClassUtil
 * Description: TODO(描述)
 * Date: 2020/5/24 11:42
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     *  获取包下类集合
     * @param packageName
     * @return 类集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        // 1. 获取到类的加载器
        ClassLoader classLoader = getClassLoader();
        // 2. 通过类加载器获取到加载的资源
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if (url == null){
            log.warn("unable to retrieve anything from package {}" , packageName);
            return null ;
        }
        // 2. 依据不同的资源类型，采用不同的方式获取资源集合
        Set<Class<?>> classSet = null ;
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            classSet = new HashSet<>() ;
            //获取目录的绝对路径
            File packageDir = new File(url.getPath()) ;
            extractClassFile(classSet, packageDir, packageName) ;
        }
        return classSet ;
    }

    /**
     * 递归获取目标package里面的所有class文件（包括子package里的class文件）
     * @param emptyClassSet   装载目标类的集合
     * @param fileSource 文件按或则目录
     * @param packageName 包名
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory()){
            return;
        }
        //如果是文件夹，则调用其listFiles方法获取文件夹下的文件或则文件
        File[] files = fileSource.listFiles(new FileFilter(){
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {//表示为文件
                    //获取文件的绝对值路径
                    String absolutePath = file.getAbsolutePath();
                    //如果是class文件，则直接加载
                    if (absolutePath.endsWith(".class")) {
                        addToClassSet(absolutePath);
                    }
                }
                return false;
            }

            // //根据class文件的绝对值路径，获取并生成class对象，并放入classSet中
            private void addToClassSet(String absoluteFilePath) {
                //1.从class文件的绝对值路径里提取出包含了package的类名
                //如/Users/baidu/imooc/springframework/sampleframework/target/classes/com/imooc/entity/dto/MainPageInfoDTO.class
                //需要弄成com.imooc.entity.dto.MainPageInfoDTO
                absoluteFilePath = absoluteFilePath.replace(File.separator, ".");
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
                //2.通过反射机制获取对应的Class对象并加入到classSet里
                Class targetClass = loadClass(className);
                emptyClassSet.add(targetClass);
            }
        });


        if (files != null){
            for (File f : files){
                extractClassFile(emptyClassSet, f, packageName) ;
            }
        }
    }


    /**
     * 获取Class对象
     * @param className class全名=package + 类名
     * @return Class
     */
    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error:", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 实例化class
     *
     * @param clazz Class
     * @param <T>   class的类型
     * @param accessible   是否支持创建出私有class对象的实例
     * @return 类的实例化
     */
    public static <T> T newInstance(Class<?> clazz, boolean accessible){
        try {
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T)constructor.newInstance();
        } catch (Exception e) {
            log.error("newInstance error", e);
            throw new RuntimeException(e);
        }
    }

    public static ClassLoader getClassLoader(){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        return contextClassLoader ;
    }

    /**
     * 设置类的属性值
     *
     * @param field      成员变量
     * @param target     类实例
     * @param value      成员变量的值
     * @param accessible 是否允许设置私有属性
     */
    public static void setField(Field field, Object target, Object value, boolean accessible){
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("setField error", e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Set<Class<?>> classes = extractPackageClass("com.yicj.study.entity");
        System.out.println(classes);
    }

}
