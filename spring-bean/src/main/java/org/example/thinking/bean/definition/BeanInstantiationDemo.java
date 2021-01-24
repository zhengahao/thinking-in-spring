package org.example.thinking.bean.definition;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author：zzh
 * @Description: Bean 实例化 构建示例
 * @Date: 2021/1/3 5:04 下午
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context.xml");
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
