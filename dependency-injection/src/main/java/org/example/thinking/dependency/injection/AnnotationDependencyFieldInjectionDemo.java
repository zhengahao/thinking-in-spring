package org.example.thinking.dependency.injection;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于Java注解方式的依赖字段方法注入示例
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired// Autowired会忽略掉静态字段（static）,详细参见AutowiredAnnotationBeanPostProcessor类
    private UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration配置类
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        //加载xml资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-look-up.xml");

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        //@Autowired 字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);
        //@Resource 字段关联
        System.out.println(demo.userHolder2);

        System.out.println(demo.userHolder == demo.userHolder2);


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

    /**
     * 2.setter方式注入
     *
     * @param user
     * @return
     */
    /*@Bean
    public UserHolder userHolder2(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }*/
}
