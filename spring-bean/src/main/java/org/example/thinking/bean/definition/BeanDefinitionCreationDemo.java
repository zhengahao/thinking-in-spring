package org.example.thinking.bean.definition;

import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @Author：zzh
 * @Description: {@link org.springframework.beans.factory.config.BeanDefinition } 构建示例
 * @Date: 2021/1/3 5:04 下午
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        // 通过BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        // 设置属性
        beanDefinitionBuilder.addPropertyValue("name","小马哥");
        beanDefinitionBuilder.addPropertyValue("id",1);

        // 获取实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // BeanDefinition并非Bean的终态，可以自定义修改

        // 2.通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();

        // 设置Bean的类型
        genericBeanDefinition.setBeanClass(User.class);
        //通过MutablePropertyValues批量修改

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        //propertyValues.addPropertyValue("name","小马哥");
        //propertyValues.addPropertyValue("id",1);
        // 方式二 Builder模式，可以进行链式调用
        propertyValues
                .add("name","小马哥")
                .add("id",1);

        genericBeanDefinition.setPropertyValues(propertyValues);



    }
}
