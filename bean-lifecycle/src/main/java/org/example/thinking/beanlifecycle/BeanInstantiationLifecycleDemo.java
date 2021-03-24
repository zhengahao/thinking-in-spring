package org.example.thinking.beanlifecycle;

import org.example.thinking.ioc.overview.domain.SuperUser;
import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * Bean实例化生命周期示例
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加BeanPostProcessor实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] location = {"classpath:META-INF/dependency-look-up.xml", "META-INF/bean-constrauct-dependency-injection.xml"};
        int count = beanDefinitionReader.loadBeanDefinitions(location);

        System.out.println("已加载 BeanDefinition数量：" + count);

        User user = beanFactory.getBean("user", User.class);
        User superUser = beanFactory.getBean("superUser", User.class);
        // 构造器注入是按照类型的方式进行注入的，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println(user);
        System.out.println(superUser);
        System.out.println(userHolder);

    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                // 把配置完成的SuperUser Bean覆盖
                return new SuperUser();
            }
            return null;// 保持Spring IoC容器的实例化操作
        }
    }
}
