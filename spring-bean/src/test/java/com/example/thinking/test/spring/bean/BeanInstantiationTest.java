package com.example.thinking.test.spring.bean;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author：zzh
 * @Description: Bean实例化
 * <p>
 * Bean实例化（Instantiation）
 * <p>
 * 常规方式
 * 通过构造器（配置元信息：XML，Java注解，Java API）
 * 通过静态工厂方法（配置元信息：XML和Java API）
 * 通过Bean工厂方法（配置元信息：XML和Java API）
 * 通过FactoryBean（配置元信息：XML，Java注解，Java API）
 * </p>
 * @Date: 2021/2/23 3:53 下午
 */
public class BeanInstantiationTest {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context-test.xml");

        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userBy = beanFactory.getBean("user-by-instance-method", User.class);
        User userFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);


        System.out.println(user);
        System.out.println(userBy);
        System.out.println(userFactoryBean);

        System.out.println(userBy == user);
        System.out.println(userFactoryBean == user);
    }
}
