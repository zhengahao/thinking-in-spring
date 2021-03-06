package com.example.thinking.test.spring.bean;

import com.example.thinking.test.factory.DefaultUserFactory;
import com.example.thinking.test.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @Author：zzh
 * @Description: 37 初始化Spring Bean测试
 * <p>
 * 1. @PostContrsuct 注解标注方法
 * 2. 实现InitializingBean接口的afterPropertiesSet()方法
 * 自定义初始化方法
 * <ul>
 *      <li>XML配置:<bean initMethod="" /></li>
 *      <li>Java注解：@Bean(initMethod="")</li>
 *      <li>Java API：AbstractBeanDefinition#setInitMethodName</li>
 * </ul>
 * @Date: 2021/3/1 2:54 下午
 */
public class BeanInitializationTest {

    public static void main(String[] args) {
        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册配置class -》 BeanInitializationTest
        applicationContext.register(BeanInitializationTest.class);

        // 启动spring上下文
        applicationContext.refresh();
        System.out.println("Spring 应用上下文已启动...");
        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);

        System.out.println("Spring应用上下文准备关闭...");
        // 关闭Spring上下文
        applicationContext.close();
        System.out.println("Spring应用上下文已经关闭...");

    }

    @Lazy(value = false)
    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    private UserFactory userFactory() {
        return new DefaultUserFactory();
    }


}
