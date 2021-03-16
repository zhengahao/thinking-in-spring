package org.example.thinking.beanlifecycle;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * BeanDefinition 合并示例
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/dependency-look-up.xml";
        int count = beanDefinitionReader.loadBeanDefinitions(location);

        System.out.println("已加载 BeanDefinition数量：" + count);

        User user = beanFactory.getBean("user", User.class);
        User superUser = beanFactory.getBean("superUser", User.class);

        System.out.println(user);
        System.out.println(superUser);


    }
}
