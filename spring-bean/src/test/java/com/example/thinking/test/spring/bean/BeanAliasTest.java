package com.example.thinking.test.spring.bean;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author：zzh
 * @Description: Bean 别名示例
 * @Date: 2021/2/23 3:22 下午
 */
public class BeanAliasTest {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/bean-definition-context.xml");

        User user = applicationContext.getBean(User.class);

        User xiaoUser = applicationContext.getBean("xiaomage-user", User.class);

        System.out.println(user == xiaoUser);

    }
}
