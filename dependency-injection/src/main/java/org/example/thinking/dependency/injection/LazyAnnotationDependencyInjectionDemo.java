package org.example.thinking.dependency.injection;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} 实现延迟依赖注入
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;// 实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider; // 延迟注入

    @Autowired
    private ObjectFactory<Set<User>>  usersObjectFactory ;


    public static void main(String[] args) {

        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Class 配置类
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        // 加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-look-up.xml");

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        // 期待输出：superUser
        System.out.println("demo.user = " + demo.user);
        // 期待输出：superUser
        System.out.println("demo.userObjectProvider = " + demo.userObjectProvider.getObject());// ObjectProvider继承ObjectFactory
        // 期待输出2个Bean：superUser，user
        System.out.println("demo.usersObjectFactory = " + demo.usersObjectFactory.getObject());


        demo.userObjectProvider.forEach(System.out::println);

        applicationContext.close();


    }
}
