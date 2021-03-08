package org.example.thinking.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置依赖来源示例
 */
@Configuration
@PropertySource(value = "META-INF/default.properties",encoding = "UTF-8")
// @PropertySource("classpath:META-INF/default.properties") 这样也可以
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${user.username}") // ${user.name}是系统内置变量，优先级更高。
    private String name;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;

    public static void main(String[] args) {

        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Class 配置类
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo dependencySourceDemo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println("demo.id: " + dependencySourceDemo.id);
        System.out.println("demo.name: " + dependencySourceDemo.name);
        System.out.println("demo.resource: " + dependencySourceDemo.resource);


        applicationContext.close();
    }
}
