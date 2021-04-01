package org.example.thinking.beanlifecycle;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化生命周期示例
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("==============================");
        executeApplicationContext();
    }

    public static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一：添加BeanPostProcessor实现
        // beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 方法二：将MyInstantiationAwareBeanPostProcessor作为Bean注册
        // 基于XML资源 BeanDefinitionReader 实现，添加<bean class="org.example.thinking.beanlifecycle.MyInstantiationAwareBeanPostProcessor" />
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] location = {"classpath:META-INF/dependency-look-up.xml", "META-INF/bean-constrauct-dependency-injection.xml"};
        int count = beanDefinitionReader.loadBeanDefinitions(location);

        System.out.println("已加载 BeanDefinition数量：" + count);

        User user = beanFactory.getBean("user", User.class);
        User superUser = beanFactory.getBean("superUser", User.class);
        // 构造器注入是按照类型的方式进行注入的，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println(user);
        System.out.println(superUser);
        System.out.println(userHolder);
    }

    public static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        String[] locations = {"classpath:META-INF/dependency-look-up.xml", "META-INF/bean-constrauct-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);

        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        User superUser = applicationContext.getBean("superUser", User.class);
        // 构造器注入是按照类型的方式进行注入的，resolveDependency
        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);

        System.out.println(user);
        System.out.println(superUser);
        System.out.println(userHolder);

        applicationContext.close();

    }


}
