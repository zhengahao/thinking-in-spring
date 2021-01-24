package org.example.thinking.bean.definition;

import org.example.thinking.bean.factory.DefualtUserFactory;
import org.example.thinking.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @Author：zzh
 * @Description: Bean 初始化Demo
 * @Date: 2021/1/10 11:43 上午
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class配置类
        applicationContext.register(BeanInitializationDemo.class);
        //启动spring应用上下文
        applicationContext.refresh();
        //非延迟初始化它在我们Spring 应用上下文启动时或者启动完成后，被初始化
        System.out.println("Spring 应用上下文已启动...");
        //依赖查找:延迟初始化时依赖查找触发了初始化
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Spring应用上下文准备关闭...");
        //关闭spring应用上下文
        applicationContext.close();
        System.out.println("Spring应用上下文已关闭...");
    }

    //@Bean
    public UserFactory getUserFactory() {
        return new DefualtUserFactory();
    }

    @Lazy(value = false)
    @Bean(initMethod = "initUserFactory",destroyMethod = "doDestroy")
    public UserFactory userFactory() {
        return new DefualtUserFactory();
    }

}
