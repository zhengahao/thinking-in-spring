package org.example.thinking.bean.definition;

import org.example.thinking.bean.factory.DefualtUserFactory;
import org.example.thinking.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author：zzh
 * @Description: 单体Bean注册实例
 * @Date: 2021/1/11 1:00 下午
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        UserFactory userFactory = new DefualtUserFactory();
        //1.方式一
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 注册外部UserFactory单例对象
        beanFactory.registerSingleton("userFactory", userFactory);

        //2.方式二 SingletonBeanRegistry
        //SingletonBeanRegistry beanRegistry = applicationContext.getBeanFactory();
        //beanRegistry.registerSingleton("userFactory", userFactory);

        //启动spring应用上下文
        applicationContext.refresh();
        //1.方式一
        //UserFactory userFactoryByLookUp = beanFactory.getBean(UserFactory.class);
        //2.方式二 SingletonBeanRegistry
        UserFactory userFactoryByLookUp = applicationContext.getBean(UserFactory.class);
        System.out.println("userFactoryByLookUp == userFactory: " + (userFactoryByLookUp == userFactory));

        //关闭spring应用上下文
        applicationContext.close();
    }

}
