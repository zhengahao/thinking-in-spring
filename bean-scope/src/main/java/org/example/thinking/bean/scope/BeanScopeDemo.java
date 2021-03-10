package org.example.thinking.bean.scope;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Collection;
import java.util.Map;

/**
 * Bean作用域示例
 */
public class BeanScopeDemo implements DisposableBean {


    @Bean
    // 默认scope就是singleton
    public static User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return createUser();
    }

    public static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }


    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private Collection<User> userCollection;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;


    public static void main(String[] args) {

        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称：%s 在初始化后回调\n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        // 注册Configuration Class 配置类
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.refresh();

        //  结论一
        //  Singleton Bean无论依赖查找或者依赖注入，均为同一个对象
        //  Prototype Bean无论依赖查找或者依赖注入，均为新生成的对象
        //  结论二
        //  如果是依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 均会存在一个
        //  Prototype Bean 有别于其他地方的依赖注入 Prototype Bean
        // 结论三
        //  无论是Prototype Bean 还是Singleton  Bean都会执行初始化方法回调，不过只有Singleton  Bean执行销毁方法

        scopeBeansByLookUp(applicationContext);

        scopeBeansByInjection(applicationContext);

        applicationContext.close();

    }

    private static void scopeBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("beanScopeDemo.singletonUser: " + beanScopeDemo.singletonUser);
        System.out.println("beanScopeDemo.singletonUser1: " + beanScopeDemo.singletonUser1);
        System.out.println("beanScopeDemo.prototypeUser: " + beanScopeDemo.prototypeUser);
        System.out.println("beanScopeDemo.prototypeUser1: " + beanScopeDemo.prototypeUser1);

        System.out.println("beanScopeDemo.users=" + beanScopeDemo.users);
        System.out.println("beanScopeDemo.userCollection=" + beanScopeDemo.userCollection);


    }

    private static void scopeBeansByLookUp(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            // singletonUser是共享bean对象
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser=" + singletonUser);

            // prototypeUser是每次查找都生成新的对象
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser=" + prototypeUser);
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();

        for (Map.Entry<String, User> entry : users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            // 如果当前Bean是prototype scope
            if (beanDefinition.isPrototype()) {
                User user = entry.getValue();
                user.destroy();
            }
        }
        System.out.println("当前 BeanScopeDemo Bean 销毁完成");
    }
}
