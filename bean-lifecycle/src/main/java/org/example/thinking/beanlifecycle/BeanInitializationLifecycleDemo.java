package org.example.thinking.beanlifecycle;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 初始化生命周期示例
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
    }

    public static void executeBeanFactory() {
        // DefaultListableBeanFactory无法进行Bean的注册,因为Bean的注册只能在ApplicationContext里面来进行运用
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一：添加BeanPostProcessor实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加CommonAnnotationBeanPostProcessor 解决 @PostConstruct回调的问题
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] location = {"classpath:META-INF/dependency-look-up.xml", "META-INF/bean-constrauct-dependency-injection.xml"};
        int count = beanDefinitionReader.loadBeanDefinitions(location);

        System.out.println("已加载 BeanDefinition数量：" + count);

        // 显式的执行preInstantiateSingletons
        // SmartInitializingSingleton 通常在Spring ApplicationContext场景使用
        // preInstantiateSingletons 将已注册的BeanDefinition初始化成Spring Bean
        beanFactory.preInstantiateSingletons();

        User user = beanFactory.getBean("user", User.class);
        User superUser = beanFactory.getBean("superUser", User.class);
        // 构造器注入是按照类型的方式进行注入的，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);



        System.out.println(user);
        System.out.println(superUser);
        System.out.println(userHolder);
    }
}
