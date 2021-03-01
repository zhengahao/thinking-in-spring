package org.example.thinking.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于XML资源的依赖Setter方法注入示例
 */
public class XmlDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourePath = "classpath:/META-INF/dependency-setter-injection.xml";
        // 加载xml文件，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourePath);
        // 依赖查找并创建Bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
