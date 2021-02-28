package org.example.thinking.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML资源 的依赖 Constructor方法 注入示例
 */
public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourePath = "classpath:/META-INF/dependency-constructor-injection.xml";
        // 加载xml文件，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourePath);
        // 依赖查找并创建Bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
