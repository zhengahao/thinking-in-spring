package org.example.thinking.dependency.lookup.test;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @Author：zzh
 * @Description: 通过 {@link ObjectProvider} 进行依赖查找
 * @Date: 2021/3/1 5:13 下午
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建ApplicationContext容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Class
        applicationContext.register(ObjectProviderDemo.class);

        //启动Spring应用上下文
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        //关闭应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider =  applicationContext.getBeanProvider(String.class);
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(objectProvider.getIfAvailable());
    }

    private static void lookupByObjectProvider(ApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    @Bean
    public String s1() {
        return "hello world";
    }

    @Bean
    @Primary
    public String message() {
        return "hello message";
    }


}
