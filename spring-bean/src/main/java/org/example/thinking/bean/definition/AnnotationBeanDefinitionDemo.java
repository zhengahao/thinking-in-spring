package org.example.thinking.bean.definition;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * @Author：zzh
 * @Description: 注解的Bean示例
 * @Date: 2021/1/5 9:58 上午
 */
//3.通过import来进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class配置类
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        //1.命名Bean的方式注册
        registerBeanDefinition(applicationContext, "mercybliz-user", User.class);
        //2.非命名Bean的方式注册
        registerBeanDefinition(applicationContext);

        //启动应用上下文
        applicationContext.refresh();


        System.out.println("config类型的所有Beans：" + applicationContext.getBeansOfType(Config.class));
        System.out.println("user类型的所有Beans：" + applicationContext.getBeansOfType(User.class));

        //显示的关闭Spring的应用上下文
        applicationContext.close();

    }

    /**
     * 命名Bean的注册方式
     *
     * @param registry
     * @param beanName
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(beanClass);

        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "小马哥");

        //判断beanName是否存在
        if (StringUtils.hasText(beanName)) {
            //注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }

    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry) {
        registerBeanDefinition(registry, null, User.class);
    }


    //2。通过@Component的方式
    @Component
    public static class Config {

        //1。通过@Bean的方式

        /**
         * 通过Java注解的方式，定义了一个bean
         *
         * @return
         */
        @Bean
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("小马哥");
            return user;
        }
    }
}
