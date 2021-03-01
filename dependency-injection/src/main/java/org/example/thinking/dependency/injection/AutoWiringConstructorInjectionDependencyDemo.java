package org.example.thinking.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Autowiring 依赖 Constructor方法注入示例
 */
public class AutoWiringConstructorInjectionDependencyDemo {


    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourePath = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";
        // 加载xml文件，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourePath);
        // 依赖查找并创建Bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }

}
