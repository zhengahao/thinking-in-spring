package org.example.thinking.dependency.lookup.test;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author：zzh
 * @Description: TODO
 * @Date: 2021/3/4 3:33 下午
 */
public class TypeSafetyDependencyLookUpDemo {

    public static void main(String[] args) {
        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册配置类
        applicationContext.register(TypeSafetyDependencyLookUpDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 演示BeanFactory#getBean方法的安全性
        displayBeanFactoryGetBean(applicationContext);

        // 演示BeanFactory#getBean方法的安全性
        displayObjectFactoryGetObject(applicationContext);

        // 演示ObjectProvider#getIfAvailable安全性
        displayObjectProviderIfAvailable(applicationContext);

        // 演示ObjectProvider Stream操作的安全性
        displayObjectProviderStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> objectProvider.getIfAvailable());

    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> objectProvider.forEach(System.out::println));

    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () -> objectFactory.getObject());

    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));


    }


    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("Source from:" + source);
        System.err.println("================================================");
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
