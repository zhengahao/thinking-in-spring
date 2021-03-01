package org.example.thinking.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanInstantiationException} 示例代码
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);

        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // 启动应用上下文
        applicationContext.refresh();

        applicationContext.close();

    }

}
