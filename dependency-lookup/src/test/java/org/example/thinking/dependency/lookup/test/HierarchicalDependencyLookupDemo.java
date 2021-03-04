package org.example.thinking.dependency.lookup.test;

import org.example.thinking.dependency.lookup.ObjectProviderDemo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author：zzh
 * @Description:
 *
 * Hierarchical
 * 英 [ˌhaɪəˈrɑːkɪkl]   美 [ˌhaɪəˈrɑːrkɪkl]
 * 分等级的;等级的;分层;层次;分层的
 *
 * @Date: 2021/3/3 11:41 上午
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ObjectProviderDemo.class);

        applicationContext.refresh();

        //1.获取 HierarchicalBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("beanFactory ParentBeanFactory :" + beanFactory.getParentBeanFactory());

        //2.设置Parent BeanFactory
        ConfigurableListableBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("beanFactory ParentBeanFactory :" + beanFactory.getParentBeanFactory());

        dispalyContainLocalBean(beanFactory, "user");
        dispalyContainLocalBean(parentBeanFactory, "user");

        createParentBeanFactory();

        applicationContext.close();

    }

    public static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return false;
    }

    private static void dispalyContainLocalBean(ConfigurableListableBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory【%s】是否包含Local bean[name:%s]:%s\n", beanFactory, beanName, containsBean(beanFactory, beanName));

    }

    public static ConfigurableListableBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:META-INF/dependency-look-up.xml");
        return beanFactory;
    }

}
