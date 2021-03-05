package org.example.thinking.dependency.injection;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 注解驱动的依赖注入处理过程
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Inject
    private User injectUser;


    @Lazy
    @Autowired
    private User lazyUser; // 依赖处理 + 延迟

    /**
     * 依赖查找处理：
     * DependencyDescriptor ->
     * 必须（required=true）
     * 实时注入（eager=true）
     * 通过类型依赖查找
     * 字段名称（"user"）
     */
    @Autowired
    private User user;

    @Autowired
    private Map<String, User> users;// user,superUser


    @Autowired
    private Optional<User> userOptional;

    public static void main(String[] args) {

        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Class 配置类
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        // 加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-look-up.xml");

        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        // 期待输出：superUser
        System.out.println("demo.lazyUser = " + demo.lazyUser);
        // 期待输出：superUser
        System.out.println("demo.user = " + demo.user);
        // 期待输出：user, superUser
        System.out.println("demo.users = " + demo.users);
        // 期待输出：superUser
        System.out.println("demo.userOptional = " + demo.userOptional);

        applicationContext.close();


    }
}
