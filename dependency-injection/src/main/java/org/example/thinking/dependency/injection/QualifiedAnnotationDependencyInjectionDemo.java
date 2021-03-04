package org.example.thinking.dependency.injection;

import org.example.thinking.dependency.injection.annotation.UserGroup;
import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier} 注解依赖
 *
 * @see Qualifier
 */
public class QualifiedAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;// 如果不指定，bean会是SuperUser对象，因为primary设置为true

    @Autowired
    @Qualifier("user")// 指定Bean名称或者ID
    private User namedUser;

    //整体应用上下文存在4个User类型的Bean
    // SuperUser，User，user1 -> @Qualifier，user2 -> @Qualifier

    @Autowired
    private Collection<User> allUsers;// 2 个Beans：superUser，user

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers;// 2 个Beans：user1，user2 扩大为4个Bean：user1，user2 ，user3，user4

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;// 2 个Beans：user3，user4

    @Bean
    @Qualifier// 进行逻辑分组
    private User user1() {
        User user = new User();
        user.setId(1007L);
        return user;
    }

    @Bean
    @Qualifier// 进行逻辑分组
    private User user2() {
        User user = new User();
        user.setId(1008L);
        return user;
    }

    @Bean
    @UserGroup// 进行逻辑分组
    private User user3() {
        return createUser(1009L);
    }

    @Bean
    @UserGroup// 进行逻辑分组
    private User user4() {
        return createUser(1010L);
    }

    private static User createUser(long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {

        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Class 配置类
        applicationContext.register(QualifiedAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        // 加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-look-up.xml");

        applicationContext.refresh();

        QualifiedAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifiedAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user =" + demo.user);
        System.out.println("demo.namedUser =" + demo.namedUser);
        // 期待输出2个Bean：superUser，user
        System.out.println("demo.allUsers =" + demo.allUsers);
        // 期待输出2个Bean：user1，user2， 扩大为4个Bean：user1，user2 ，user3，user4
        System.out.println("demo.qualifierUsers =" + demo.qualifiedUsers);
        // 期待输出2个Bean：user3，user4
        System.out.println("demo.groupUsers =" + demo.groupUsers);

        applicationContext.close();


    }
}
