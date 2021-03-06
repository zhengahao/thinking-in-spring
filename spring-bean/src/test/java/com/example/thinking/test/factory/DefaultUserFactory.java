package com.example.thinking.test.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author：zzh
 * @Description: TODO
 * @Date: 2021/3/1 3:17 下午
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct: UserFactory初始化...");

    }

    private void initUserFactory() {
        System.out.println("自定义初始化方法: UserFactory初始化...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("实现InitializingBean#afterPropertiesSet:UserFactory初始化...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory 销毁中...");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法doDestroy(): UserFactory 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy: UserFactory 销毁中...");
    }
}
