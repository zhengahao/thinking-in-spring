package org.example.thinking.dependency.lookup;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过{@link ObjectProvider} 进行依赖查找
 */
public class ObjectProviderDemo { // Configuration是非必须的

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类 ObjectProviderDemo 作为配置类（Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找对象
        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        //关闭应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {

        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);

        // 方式一
        /*Iterable<String> iterable = objectProvider;
        for (String s : iterable) {
            System.out.println(s);
        }*/
        // 方式二
        objectProvider.stream().forEach(System.out::println);
    }

    @Bean// 方法名称就是Bean的名称
    public String helloworld() {
        return "hello,world";
    }

    @Bean// 方法名称就是Bean的名称
    @Primary
    public String message() {
        return "hello,message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {

        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);

        System.out.println("当前User对象：" + user);
    }
}
