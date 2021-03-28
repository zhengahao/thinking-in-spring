package org.example.thinking.beanlifecycle;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * User Holder类
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, InitializingBean, SmartInitializingSingleton {

    private final User user;

    private Integer number;

    private String description;

    private String beanName;

    private Environment environment;

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    /**
     * 依赖于注解驱动
     * 当前场景：BeanFactory需要一个相关的BeanPostProcessor
     */
    @PostConstruct
    public void initPostConstruct() {
        //postProcessBeforeInitialization V3 -> initPostConstruct V4
        this.description = "The user Holder V4";
        System.out.println("initPostConstruct() = " + description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //postProcessBeforeInitialization V4 -> initPostConstruct V5
        this.description = "The user Holder V5";
        System.out.println("afterPropertiesSet() = " + description);
    }

    /**
     * 自定义初始化方法
     */
    public void init() {
        //postProcessBeforeInitialization V5 -> initPostConstruct V6
        this.description = "The user Holder V6";
        System.out.println("init() = " + description);
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    @Override
    public void afterSingletonsInstantiated() {
        //postProcessAfterInitialization V7 -> afterSingletonsInstantiated V8
        this.description = "The user Holder V8";
        System.out.println("afterSingletonsInstantiated() = " + description);
    }

}
