package org.example.thinking.bean.definition;

import org.example.thinking.bean.factory.DefualtUserFactory;
import org.example.thinking.bean.factory.UserFactory;
import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author：zzh
 * @Description: Bean 实例化 构建示例
 * @Date: 2021/1/3 5:04 下午
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/special-bean-instantiation-context.xml");
        //通过ApplicationContext 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        //BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/special-bean-instantiation-context.xml");

        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader",ServiceLoader.class);
        //demoServiceLoader();

        displayServiceLoader(serviceLoader);

        //创建 UserFactory 对象通过 AutowiredCapataleBeanFactory
        UserFactory userFactory = beanFactory.createBean(DefualtUserFactory.class);
        System.out.println(userFactory.createUser());

    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {

        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }

    private static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());

        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }


}
