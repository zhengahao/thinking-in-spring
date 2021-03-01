package org.example.thinking.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException} 示例代码
 */
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 BeanDefinition Bean Class 是一个POJO普通类   ，不过初始化方法回调时抛出异常
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);

        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // 启动应用上下文
        applicationContext.refresh();

        applicationContext.close();

    }


    static class POJO implements InitializingBean {

        @PostConstruct
        public void init() throws Throwable {
            throw new Exception("init: For purposes...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet: For purposes...");
        }
    }
}
