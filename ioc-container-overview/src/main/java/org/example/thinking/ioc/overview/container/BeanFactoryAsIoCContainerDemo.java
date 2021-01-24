package org.example.thinking.ioc.overview.container;
import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @Author：zzh
 * @Description: {@link BeanFactory} IoC容器示例
 * @Date: 2021/1/3 11:40 上午
 */
public class BeanFactoryAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:META-INF/dependency-look-up.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("bean加载的数量："+beanDefinitionsCount);

        // 依赖查找
        lookUpCollectionInType(beanFactory);
    }

    private static void lookUpCollectionInType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的对象集合：" + users);
        }
    }
}
