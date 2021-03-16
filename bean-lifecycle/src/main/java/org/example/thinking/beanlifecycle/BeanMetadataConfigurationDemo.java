package org.example.thinking.beanlifecycle;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置示例
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化基于Properties 资源 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        String location = "META-INF/user.properties";
        // 基于ClassPath 指定字符编码 UTF-8
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanAfterCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        int beanCount = beanAfterCount - beanDefinitionCountBefore;
        System.out.println("已加载的 BeanDefinition 数量：" + beanCount);
        // 普通的class作为Component注册到Spring IoC容器后，通常Bean名称为 annotationBeanDefinitionPasingDemo
        // 通过 Bean Id或者类型进行依赖查找
        User user = beanFactory.getBean(User.class);
        System.out.println(user);

    }
}
