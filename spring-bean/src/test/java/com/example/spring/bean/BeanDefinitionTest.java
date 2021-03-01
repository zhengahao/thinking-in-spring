package com.example.spring.bean;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @Author：zzh
 * @Description: Bean 定义测试
 * @Date: 2021/2/19 5:15 下午
 */
public class BeanDefinitionTest {

    public static void main(String[] args) {

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        beanDefinitionBuilder
                .addPropertyValue("name", "自豪")
                .addPropertyValue("id", 1);

        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues
                .add("name", "自豪")
                .add("id", 1);

        genericBeanDefinition.setPropertyValues(propertyValues);

    }
}
