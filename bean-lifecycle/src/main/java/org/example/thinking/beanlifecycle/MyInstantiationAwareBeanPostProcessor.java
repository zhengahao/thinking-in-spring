package org.example.thinking.beanlifecycle;

import org.example.thinking.ioc.overview.domain.SuperUser;
import org.example.thinking.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
            // 把配置完成的SuperUser Bean覆盖
            return new SuperUser();
        }
        return null;// 保持Spring IoC容器的实例化操作
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            // User对象不允许属性赋值（属性填入）（配置元信息 -> 属性值）
            User user = (User) bean;
            user.setId(2L);
            user.setName("mercybliz");
            return false;
        }
        return true;
    }

    // user是跳过Bean的属性赋值
    // superUser是完全跳过Bean的实例化
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 对userHolder Bean进行拦截
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {

            MutablePropertyValues propertyValues = null;

            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }

            // 假设<property name="number" value="1"/>配置的话，，那么在PropertyValues里面就包含一个PropertyValue（number=1）
            // 等价于 <property name="number" value="1"/>配置
            propertyValues.addPropertyValue("number", "1");

            //如果存在"description"
            if (propertyValues.contains("description")) {
                propertyValues.removePropertyValue("description");
                propertyValues.addPropertyValue("description", "The user holder V2");
            }

            return propertyValues;
        }

        return null;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // userHolder 的description此时等于The user holder V2
            userHolder.setDescription("user holder V3");
        }
        return bean;
    }


}
