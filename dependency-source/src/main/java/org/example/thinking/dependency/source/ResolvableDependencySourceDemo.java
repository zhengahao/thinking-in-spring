package org.example.thinking.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {

        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();


        // 注册Configuration Class 配置类
        applicationContext.register(ResolvableDependencySourceDemo.class);

        //PostProcessor回调
        applicationContext.addBeanFactoryPostProcessor(configurableListableBeanFactory -> {
            configurableListableBeanFactory.registerResolvableDependency(String.class, "hello,world");
        });

        applicationContext.refresh();

        applicationContext.close();
    }
}
