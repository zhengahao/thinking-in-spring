package org.example.thinking.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author：zzh
 * @Description: TODO
 * @Date: 2021/1/6 1:19 下午
 */
public class DefualtUserFactory implements UserFactory, InitializingBean, DisposableBean {

    // 1. 基于PostConstruct注解实现
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct UserFactory 初始化中...");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法initUserFactory(): UserFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet(): UserFactory 初始化中...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy: UserFactory 销毁中...");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法doDestroy(): UserFactory 销毁中...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前对象DefaultUserFactory对象正在被回收...");
    }
}
