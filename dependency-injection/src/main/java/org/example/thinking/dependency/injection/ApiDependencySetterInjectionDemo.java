package org.example.thinking.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于Java API方式的依赖Setter方法注入示例
 */
public class ApiDependencySetterInjectionDemo {

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinition  userHolderBeanDefinition = createUserHolderBeanDefinition();

        // 注册UserHolder的BeanDefinition
        applicationContext.registerBeanDefinition("userHolder", userHolderBeanDefinition);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        //加载xml资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-look-up.xml");

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        //依赖查找并创建Bean
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        // 关闭 Spring 应用上下文
        applicationContext.close();

    }


    /**
     * 为 {@link UserHolder} 生成 {@link BeanDefinition}
     *
     * @return
     */
    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        // ref本来就属于reference的缩写
        definitionBuilder.addPropertyReference("user", "superUser");
        return definitionBuilder.getBeanDefinition();

    }
}
