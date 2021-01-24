package org.example.thinking.ioc.overview.dependency.lookup;

import org.example.thinking.ioc.overview.annotation.Super;
import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Author：zzh
 * @Description: 通过名称的方式查找示例
 * @Date: 2020/12/30 1:06 下午
 */
public class DependencyLookUpDemo {

    public static void main(String[] args) {

        //配置XML文件
        //启动Spring上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-look-up.xml");
        //lookUpInRealTime(beanFactory);
        //lookUpInLazy(beanFactory);
        //按照类型查找
        looUpInType(beanFactory);
        //按照类型查找集合对象
        lookUpCollectionInType(beanFactory);
        //按照注解查找集合对象
        lookUpByAnnotation(beanFactory);
    }

    private static void lookUpByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注@Super的用户所有的对象集合：" + users);
        }
    }

    private static void lookUpCollectionInType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的对象集合：" + users);
        }
    }

    private static void looUpInType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找：" + user);
    }

    private static void lookUpInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }

    private static void lookUpInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }


}
