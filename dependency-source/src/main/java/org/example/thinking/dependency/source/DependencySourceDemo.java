package org.example.thinking.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 */
public class DependencySourceDemo {


    // 注入在postProcessProperties方法执行，早于setter注入，也早于@PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void initByInjection() {
        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory() " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));

        System.out.println("resourceLoader == applicationContext " + (applicationContext == resourceLoader));
        System.out.println("applicationEventMulticaster == applicationContext " + (applicationContext == applicationEventPublisher));

    }

    @PostConstruct
    public void initByLookUp() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);

    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType + "无法在 BeanFactory 中查找!");
        }
        return null;
    }

    public static void main(String[] args) {

        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Class 配置类
        applicationContext.register(DependencySourceDemo.class);

        applicationContext.refresh();

        DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);

        applicationContext.close();


    }
}
