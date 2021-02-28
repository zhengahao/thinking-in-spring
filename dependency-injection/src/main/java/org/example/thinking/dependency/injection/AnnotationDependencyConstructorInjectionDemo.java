package org.example.thinking.dependency.injection;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 注解方式 的依赖 Constructor方法 注入示例
 */
public class AnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration配置类
        applicationContext.register(AnnotationDependencyConstructorInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        //加载xml资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-look-up.xml");

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        //依赖查找并创建Bean
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        // 关闭 Spring 应用上下文
        applicationContext.close();

    }

    /**
     * 1. 构造器方式注入
     * @param user
     * @return
     */
    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

}
