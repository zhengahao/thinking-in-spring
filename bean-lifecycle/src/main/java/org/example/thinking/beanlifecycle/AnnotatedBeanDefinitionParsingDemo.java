package org.example.thinking.beanlifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解BeanDefinition配置示例
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于Java 注解 AnnotatedBeanDefinitionReader的实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanBeforeCount = beanFactory.getBeanDefinitionCount();
        // 注册当前类（非 @Component Class）
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);

        int beanAfterCount = beanFactory.getBeanDefinitionCount();

        int beanCount = beanAfterCount - beanBeforeCount;
        System.out.println("已加载BeanDefinition 数量：" + beanCount);
        // 普通class作为Component注册到Spring IoC容器之后，通常Bean名称为 annotatedBeanDefinitionParsingDemo
        // Bean 名称生成来自于 BeanNameGenerator，注解实现 AnnotationBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo definitionParsingDemo = beanFactory.getBean(AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(definitionParsingDemo);
    }
}
