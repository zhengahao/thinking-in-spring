package org.example.thinking.dependency.injection;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于Java注解的依赖 方法注入 示例
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    private void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    private void initUserHolder2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    /**
     * 1. 构造器方式注入
     *
     * @param user
     * @return
     */
    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration配置类
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        //加载xml资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-look-up.xml");

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        //@Autowired 字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);
        //@Resource 字段关联
        System.out.println(demo.userHolder2);

        System.out.println(demo.userHolder == demo.userHolder2);


        // 关闭 Spring 应用上下文
        applicationContext.close();

    }


}
