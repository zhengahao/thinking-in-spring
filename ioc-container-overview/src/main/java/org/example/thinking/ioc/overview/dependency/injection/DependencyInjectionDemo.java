package org.example.thinking.ioc.overview.dependency.injection;

import org.example.thinking.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Author：zzh
 * @Description: 通过名称的方式查找示例
 * @Date: 2020/12/30 1:06 下午
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {

        //配置XML文件
        //启动Spring上下文
        //BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-injection.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-injection.xml");

        //依赖来源一： 自定义的bean
        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");

        //System.out.println(userRepository.getUsers());

        //依赖来源二： 依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());

        ObjectFactory userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject() == applicationContext);

        // 依赖查找（错误）
        //System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三：容器内建的bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取Environment类型的bean：" + environment);

        whoIsIoCContainer(applicationContext, userRepository);
    }

    private static void whoIsIoCContainer(BeanFactory beanFactory, UserRepository userRepository) {
        //ConfigurableApplicationContext <- ApplicationContext <- BeanFactory

        //ConfigurableApplicationContext#getBeanFactory()

        // 这个表达式为什么不成立
        System.out.println(userRepository.getBeanFactory() == beanFactory);
    }


}
