package com.yicj.generic;

import lombok.Data;

/**
 * ClassName: GenericClassExample
 * Description: TODO(描述)
 * Date: 2020/6/1 20:31
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class GenericClassExample<T> {
    // member这个成员变量的类型为T，T的类型由外部指定
    private T member ;

    // 泛型构造方法形参member也为T，T的类型由外部指定
    public GenericClassExample(T member){
        this.member = member ;
    }

    public T handleSomething(T target){
        return target ;
    }

    public static void main(String[] args) {

        GenericClassExample<String> stringExample = new GenericClassExample<>("abc") ;
        GenericClassExample<Integer> integerExample = new GenericClassExample<>(123) ;

        System.out.println(stringExample.getMember().getClass());
        System.out.println(integerExample.getMember().getClass());
    }

}
