package org.example.thinking.bean.definition;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author：zzh
 * @Description: Bean 别名示例
 * @Date: 2021/1/4 10:04 下午
 */
public class BeanAliasDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-definition-context.xml");

        // 通过别名xiaomage-user获取user bean
        User user = beanFactory.getBean(User.class);
        User xiaomageUser = beanFactory.getBean("xiaomage-user", User.class);
        System.out.println("xiaomageUser与user是否相同：" + (user == xiaomageUser));
    }
}
