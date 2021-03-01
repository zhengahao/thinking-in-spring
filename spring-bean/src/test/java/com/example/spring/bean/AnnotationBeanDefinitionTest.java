package com.example.spring.bean;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @Author：zzh
 * @Description: 注解BeanDefinition
 * Java API配置元信息
 * 1. 命名方式BeanDefinitionRegistry#registerBeanDefinition（String beanName，BeanDefinition beanDefinition）
 * 2. 非命名方式：BeanDefinitionReaderUtils.registerWithGeneratedName（AbstractBeanDefinition definition, BeanDefinitionRegistry registry）
 * 3. 配置方式：AnnotatedBeanDefinitionReaderA#register（Class<?>... componentClasses）
 * @Date: 2021/2/22 11:14 上午
 */
public class AnnotationBeanDefinitionTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotationBeanDefinitionTest.class);
        registerBeanDefinition(applicationContext, "builder-user");
        registerBeanDefinition(applicationContext, null);

        applicationContext.refresh();

        System.out.println(applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        beanDefinitionBuilder
                .addPropertyValue("name", "梓豪")
                .addPropertyValue("id", "1001");

        if (StringUtils.hasText(beanName)) {
            beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), beanDefinitionRegistry);
        }
    }

    //@Bean
    public User user() {
        return User.createUser();
    }


}
